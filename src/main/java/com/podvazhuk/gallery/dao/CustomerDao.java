package com.podvazhuk.gallery.dao;

import com.podvazhuk.gallery.domain.Customer;

public interface CustomerDao {

	void save(Customer customer);

	void update(Customer customer);

	Customer findByCode(String code);

	Customer find(String username, String password);

}
