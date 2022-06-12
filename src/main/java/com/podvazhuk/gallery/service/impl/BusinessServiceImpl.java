package com.podvazhuk.gallery.service.impl;

import com.podvazhuk.gallery.commons.Page;
import com.podvazhuk.gallery.dao.*;
import com.podvazhuk.gallery.dao.impl.*;
import com.podvazhuk.gallery.domain.*;
import com.podvazhuk.gallery.service.BusinessService;

import java.util.List;
import java.util.UUID;

public class BusinessServiceImpl implements BusinessService {

	private CategoryDao categoryDao=new CategoryDaoImpl();
	private CustomerDao customerDao=new CustomerDaoImpl();
	private OrderDao orderDao=new OrderDaoImpl();
	private ArtistDao artistDao = new ArtistDaoImpl();
	private WayDao wayDao = new WayDaoImpl();
	
	private PaintingDao paintingDao =new PaintingDaoImpl();
	@Override
	public void addCategory(Category category){
			category.setId(UUID.randomUUID().toString());
			categoryDao.save(category);
	}

	@Override
	public void updateCategory(Category category){
		categoryDao.update(category);
	}

	@Override
	public List<Category> findAllCategories() {
		return categoryDao.findAll();
	}

	@Override
	public boolean isCategoryExists(String categoryName) {
		Category category=categoryDao.findByName(categoryName);
		return category != null;
	}

	@Override
	public void  delCategory(String categoryName) {
		categoryDao.deleteByName(categoryName);
		
	}

    @Override
    public void addArtist(Artist artist){
        artist.setId(UUID.randomUUID().toString());
        artistDao.save(artist);
    }

	@Override
	public void updateArtist(Artist artist){// TODO Auto-generated method stub
		artistDao.update(artist);
	}

    @Override
    public List<Artist> findAllArtists() {
        return artistDao.findAll();
    }

    @Override
    public boolean isArtistExists(String artistName) {
        Artist artist=artistDao.findByName(artistName);
        return artist != null;
    }

    @Override
    public void  delArtist(String artistName) {
        artistDao.deleteByName(artistName);

    }

    @Override
    public void addWay(Way way){
        way.setId(UUID.randomUUID().toString());
        wayDao.save(way);
    }

	@Override
	public void updateWay(Way way){// TODO Auto-generated method stub
		wayDao.update(way);
	}

    @Override
    public List<Way> findAllWays() {
        return wayDao.findAll();
    }

    @Override
    public boolean isWayExists(String wayName) {
        Way way=wayDao.findByName(wayName);
        return way != null;
    }

    @Override
    public void  delWay(String wayName) {
        wayDao.deleteByName(wayName);
    }


	@Override
	public void addPainting(Painting painting) {
		if(painting ==null)
			throw new IllegalArgumentException("error");
		if(painting.getCategory()==null)
			throw new IllegalArgumentException("error");
		painting.setId(UUID.randomUUID().toString());
		paintingDao.addPainting(painting);
	}

	@Override
	public void updatePainting(Painting painting){// TODO Auto-generated method stub
		paintingDao.updatePainting(painting);
	}

	@Override
	public void  delPainting(String id) {
		paintingDao.deletePainting(id);
	}

	@Override
	public Category findCategoryById(String categoryId) {
		return categoryDao.findOne(categoryId);
	}

	@Override
	public Way findWayById(String wayId) {
		return wayDao.findOne(wayId);
	}

	@Override
	public Artist findArtistById(String artistId) {

		return artistDao.findOne(artistId);
	}

	@Override
	public Page findPage(String num) {
		int pageNum =1;
		if(num!=null){
			pageNum=Integer.parseInt(num);
		}
		int totalRecordsNum= paintingDao.getTotalRecordsNum();
		Page page=new Page(pageNum,totalRecordsNum);
		List<Painting>  records= paintingDao.findPagePaintings(page.getStartIndex(), page.getPageSize());
		page.setRecords(records);

		return page;
	}

	@Override
	public Page findPage(String num, String categoryId) {
		int pageNum =1;
		if(num!=null){
			pageNum=Integer.parseInt(num);
		}
		int totalRecordsNum= paintingDao.getTotalRecordsNum(categoryId);
		Page page=new Page(pageNum,totalRecordsNum);
		List<Painting>  records= paintingDao.findPagePaintings(page.getStartIndex(), page.getPageSize(),categoryId);
		page.setRecords(records);

		return page;
	}

	@Override
	public Page findPageOfArtist(String num, String artistId) {
		int pageNum =1;
		if(num!=null){
			pageNum=Integer.parseInt(num);
		}
		int totalRecordsNum= paintingDao.getTotalRecordsNumOfArtist(artistId);
		Page page=new Page(pageNum,totalRecordsNum);
		List<Painting>  records= paintingDao.findPagePaintingsOfArtist(page.getStartIndex(), page.getPageSize(),artistId);
		page.setRecords(records);

		return page;
	}

	@Override
	public Page findPageOfWay(String num, String wayId) {
		int pageNum =1;
		if(num!=null){
			pageNum=Integer.parseInt(num);
		}
		int totalRecordsNum= paintingDao.getTotalRecordsNumOfWay(wayId);
		Page page=new Page(pageNum,totalRecordsNum);
		List<Painting>  records= paintingDao.findPagePaintingsOfWay(page.getStartIndex(), page.getPageSize(),wayId);
		page.setRecords(records);

		return page;
	}

	@Override
	public Page findPageSorted(String num, String sortType) {
		int pageNum =1;
		if(num!=null){
			pageNum=Integer.parseInt(num);
		}
		int totalRecordsNum= paintingDao.getTotalRecordsNum();
		Page page=new Page(pageNum,totalRecordsNum);
		List<Painting>  records= paintingDao.findPagePaintingsSorted(page.getStartIndex(), page.getPageSize(),sortType);
		page.setRecords(records);

		return page;
	}

	@Override
	public Page findPageOfName(String num, String name) {
		int pageNum =1;
		if(num!=null){
			pageNum=Integer.parseInt(num);
		}
		int totalRecordsNum= paintingDao.getTotalRecordsNumOfName(name);
		Page page=new Page(pageNum,totalRecordsNum);
		List<Painting>  records= paintingDao.findPagePaintingsOfName(page.getStartIndex(), page.getPageSize(),name);
		if (records.size() == 0) return null;
		page.setRecords(records);

		return page;
	}

	@Override
	public Painting findPaintingById(String paintingId) {
		return paintingDao.findOne(paintingId);
	}

	@Override
	public void registCustomer(Customer customer) {
		customer.setId(UUID.randomUUID().toString());
		customerDao.save(customer);
	}

	@Override
	public Customer findByCode(String code) {
		return customerDao.findByCode(code);
	}

	@Override
	public void activeCustomer(Customer customer) {
		if(customer==null)
			throw new RuntimeException("Поле порожнє");
		if(customer.getId()==null)
			throw new RuntimeException("Ключ не може бути порожнім.");
		
		customerDao.update(customer);
		
	}

	@Override
	public Customer login(String username, String password) {
		
		Customer customer = customerDao.find(username,password);
		if(customer==null)
			return null;
		if(!customer.isActived())
			return null;
		return customer;
	}

	@Override
	public void genOrder(Order order) {
		if(order==null)
				throw new RuntimeException("Замовлення не можуть бути порожніми");
		if(order.getCustomer()==null)
			throw new RuntimeException("Ключ не може бути порожнім");
		orderDao.save(order);
		
	}

	@Override
	public Order findOrderByNum(String ordernum) {
		return orderDao.findByNum(ordernum);
	}

	@Override
	public void updateOrder(Order order) {
		orderDao.update(order);
		
	}

	@Override
	public void changeOrderStatus(int status, String ordernum) {
		Order order=findOrderByNum(ordernum);
		order.setStatus(status);
		updateOrder(order);
	}

	@Override
	public List<Order> findOrdersByCustomerId(String customerId) {
		return orderDao.findByCustomerId(customerId);
	}

	@Override
	public List<OrderItem> findOrderItemByCustomerId(String ordernum) {
		return orderDao.findOrderItem(ordernum);
	}
}
