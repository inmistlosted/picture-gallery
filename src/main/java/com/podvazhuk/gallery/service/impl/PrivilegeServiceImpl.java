package com.podvazhuk.gallery.service.impl;

import java.util.List;

import com.podvazhuk.gallery.dao.PrivilegeDao;
import com.podvazhuk.gallery.domain.Function;
import com.podvazhuk.gallery.domain.Role;
import com.podvazhuk.gallery.domain.User;
import com.podvazhuk.gallery.dao.impl.PrivilegeDaoImpl;
import com.podvazhuk.gallery.service.PrivilegeService;

public class PrivilegeServiceImpl implements PrivilegeService {

	private PrivilegeDao dao=new PrivilegeDaoImpl();
	@Override
	public User login(String username, String password) {
		return dao.findUser(username,password);
	}
	@Override
	public List<Role> findUserRoles(User user) {
		
		return dao.findUserRoles(user);
	}
	@Override
	public List<Function> findRoleFunctions(Role role) {
		return dao.findRoleFunctions(role);
	}
}
