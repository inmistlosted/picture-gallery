package com.podvazhuk.gallery.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.podvazhuk.gallery.domain.Category;
import com.podvazhuk.gallery.utils.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.podvazhuk.gallery.dao.CategoryDao;

public class CategoryDaoImpl implements CategoryDao  {

	
	private QueryRunner  qr=new QueryRunner(C3P0Util.getDataSource());
	
	@Override
	public void save(Category category) {
		try {
			qr.update("insert into categorys(id ,name,des) values(?,?,?)",
					category.getId(),category.getName(),category.getDes());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void update(Category category) {
		try {
			qr.update("update categorys set name=?, des=? where id=?",
					category.getName(),category.getDes(),category.getId());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public List<Category> findAll() {
		try {
			return	qr.query("select * from categorys", new BeanListHandler<Category>(Category.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Category findByName(String categoryName) {
		
		try {
			return 	qr.query("select * from categorys  where name=? ", new BeanHandler<Category>(Category.class),categoryName);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public  void deleteByName(String categoryName) {
		
		try {
			  qr.update("delete from categorys where name=? ", categoryName);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public Category findOne(String categoryId) {
		
		try {
			return 	qr.query("select * from categorys  where id=? ", new BeanHandler<Category>(Category.class),categoryId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	

}
