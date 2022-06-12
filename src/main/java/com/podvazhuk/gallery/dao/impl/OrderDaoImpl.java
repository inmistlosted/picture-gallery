package com.podvazhuk.gallery.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.podvazhuk.gallery.domain.Customer;
import com.podvazhuk.gallery.domain.Order;
import com.podvazhuk.gallery.domain.OrderItem;
import com.podvazhuk.gallery.domain.Painting;
import com.podvazhuk.gallery.utils.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.podvazhuk.gallery.dao.OrderDao;

public class OrderDaoImpl implements OrderDao {

	private QueryRunner qr=new QueryRunner(C3P0Util.getDataSource());
	
	@Override
	public void save(Order order) {
		
		try {
			qr.update("insert into orders (ordernum,price,number,status,customerId) values (?,?,?,?,?)", 
					order.getOrdernum(),order.getPrice(),order.getNumber(),order.getStatus(),
					order.getCustomer()==null?null:order.getCustomer().getId());
			List<OrderItem> items = order.getItems();
			for(OrderItem item:items){
				qr.update("insert into orderitems (id,number,price,ordernum,paintingid) values (?,?,?,?,?)",
						item.getId(),item.getNumber(),item.getPrice(),order.getOrdernum(),item.getPainting()==null?null:item.getPainting().getId());
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public Order findByNum(String ordernum) {
		try {
			Order order = qr.query("select * from orders where ordernum=?", new BeanHandler<Order>(Order.class), ordernum);
			if(order!=null){
				Customer customer = qr.query("select * from customers where id=(select customerId from orders where ordernum=?)", new BeanHandler<Customer>(Customer.class), ordernum);
				order.setCustomer(customer);
			}
			return order;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void update(Order order) {
		try {
			qr.update("update orders set price=?,number=?,status=? where ordernum=?", order.getPrice(),order.getNumber(),order.getStatus(),order.getOrdernum());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	@Override
	public List<Order> findByCustomerId(String customerId) {
		try {
			List<Order> orders=qr.query("select * from orders where customerId=?  order by ordernum desc ", new BeanListHandler<Order>(Order.class),customerId);
			if(orders!=null){
				Customer customer=qr.query("select * from customers where id=? ",new BeanHandler<Customer>(Customer.class),customerId);
				for (Order order : orders) {
					order.setCustomer(customer);
				}
			}
			return orders;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	@Override
	public List<OrderItem> findOrderItem(String ordernum) {
		
		try {
			List<OrderItem> items = qr.query("select * from orderitems where ordernum=?", new BeanListHandler<OrderItem>(OrderItem.class), ordernum);
			if(items!=null){
				for(OrderItem o:items){
					Painting painting = qr.query("select * from paintings where id=(select paintingid from orderitems where id=?)", new BeanHandler<Painting>(Painting.class), o.getId());
					o.setPainting(painting);
				}
			}
			return items;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
