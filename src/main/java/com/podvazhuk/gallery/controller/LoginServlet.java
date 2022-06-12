package com.podvazhuk.gallery.controller;

import com.podvazhuk.gallery.domain.User;
import com.podvazhuk.gallery.service.PrivilegeService;
import com.podvazhuk.gallery.service.impl.PrivilegeServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

	private PrivilegeService s=new PrivilegeServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		String username=request.getParameter("username");
		String password=request.getParameter("password");
		User user=s.login(username,password);
		if(user==null){
			response.getWriter().write("Ім'я користувача або пароль невірні.");
			response.setHeader("Refresh","2;URL="+request.getContextPath()+"/op/login.jsp");
			return;
		}

		request.getSession().setAttribute("user",user);
		response.getWriter().write("Успішний запис");
		response.setHeader("Refresh","1;URL="+request.getContextPath()+"/manage/index.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		doGet(request,response);
	}
}
