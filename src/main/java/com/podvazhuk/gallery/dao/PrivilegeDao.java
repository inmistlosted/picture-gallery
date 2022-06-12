package com.podvazhuk.gallery.dao;

import java.util.List;

import com.podvazhuk.gallery.domain.Function;
import com.podvazhuk.gallery.domain.Role;
import com.podvazhuk.gallery.domain.User;

public interface PrivilegeDao {

	User findUser(String username, String password);

	List<Role> findUserRoles(User user);

	List<Function> findRoleFunctions(Role role);

}
