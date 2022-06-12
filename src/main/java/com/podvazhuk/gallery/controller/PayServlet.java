package com.podvazhuk.gallery.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PayServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String orderId=request.getParameter("orderId");
		String price=request.getParameter("price");
		String buyerId=request.getParameter("buyerId");

		String command = "Buy";
		String url = "http://localhost:8080"+request.getContextPath()+"/servlet/ResponsePayServlet";
		String code = "4578409864";
		String type = "paintings";
		String descr = "descrition";
		String delivery = "1";
		String weight = "unknown";
		String priority="6";
		
		request.setAttribute("command",command);
		request.setAttribute("url",url );
		request.setAttribute("code",code );
		request.setAttribute("order", orderId);
		request.setAttribute("check", price);
		request.setAttribute("type",type );
		request.setAttribute("description",descr );
		request.setAttribute("delivery",delivery );
		request.setAttribute("weight",weight );
		request.setAttribute("priority",priority );
		request.setAttribute("buyerId", buyerId);
		
		request.getRequestDispatcher("/pay.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
