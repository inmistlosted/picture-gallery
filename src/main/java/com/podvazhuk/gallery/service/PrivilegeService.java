package com.podvazhuk.gallery.service;

import java.util.List;

import com.podvazhuk.gallery.domain.Function;
import com.podvazhuk.gallery.domain.Role;
import com.podvazhuk.gallery.domain.User;

public interface PrivilegeService {
	User login(String username, String password);

	List<Role> findUserRoles(User user);

	List<Function> findRoleFunctions(Role role);
}
