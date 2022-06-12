package com.podvazhuk.gallery.service;

import com.podvazhuk.gallery.commons.Page;
import com.podvazhuk.gallery.domain.*;

import java.util.List;

public interface  BusinessService {
	
	void addCategory(Category category);

	void updateCategory(Category category);
	
	List<Category>  findAllCategories();
	
	boolean isCategoryExists(String categoryName);

	void delCategory(String categoryName);

	void addArtist(Artist artist);

	void updateArtist(Artist artist);

	List<Artist>  findAllArtists();

	boolean isArtistExists(String artistName);

	void delArtist(String artistName);

	void addWay(Way way);

	void updateWay(Way way);

	List<Way>  findAllWays();

	boolean isWayExists(String wayName);

	void delWay(String wayName);
	
	void addPainting(Painting painting);

	void updatePainting(Painting painting);

	void delPainting(String id);
	
	Category findCategoryById(String categoryId);

	Way findWayById(String wayId);

	Artist findArtistById(String artistId);

	Page findPage(String num);
	
	Page findPage(String num, String categoryId);

	Page findPageOfArtist(String num, String artistId);

	Page findPageOfWay(String num, String wayId);

	Page findPageSorted(String num, String sortType);

	Page findPageOfName(String num, String name);

	Painting findPaintingById(String paintingId);
	
	void registCustomer(Customer customer);
	
	
	Customer findByCode(String code);
	
	void activeCustomer(Customer customer);
	
	Customer login(String username, String password);

	void genOrder(Order order);

	Order findOrderByNum(String ordernum);

	void updateOrder(Order order);

	void changeOrderStatus(int status, String ordernum);

	List<Order> findOrdersByCustomerId(String customerId);
	
	List<OrderItem> findOrderItemByCustomerId(String ordernum);

}
