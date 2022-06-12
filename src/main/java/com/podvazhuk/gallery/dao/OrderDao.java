package com.podvazhuk.gallery.dao;

import java.util.List;

import com.podvazhuk.gallery.domain.Order;
import com.podvazhuk.gallery.domain.OrderItem;

public interface OrderDao {

	void save(Order order);

	Order findByNum(String ordernum);

	void update(Order order);

	List<Order> findByCustomerId(String customerId);

	
	List<OrderItem> findOrderItem(String ordernum);

}
