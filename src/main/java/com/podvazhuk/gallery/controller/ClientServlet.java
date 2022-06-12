package com.podvazhuk.gallery.controller;

import com.podvazhuk.gallery.bean.Cart;
import com.podvazhuk.gallery.bean.CartItem;
import com.podvazhuk.gallery.commons.Page;
import com.podvazhuk.gallery.domain.*;
import com.podvazhuk.gallery.service.BusinessService;
import com.podvazhuk.gallery.service.impl.BusinessServiceImpl;
import com.podvazhuk.gallery.utils.OrderNumUtil;
import com.podvazhuk.gallery.utils.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ClientServlet extends HttpServlet {

	private BusinessService s=new BusinessServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op=request.getParameter("op");
		if("listPaintings".equals(op)){
			listPaintings(request,response);
		}else if("listPaintingByCategory".equals(op)){
			listPaintingByCategory(request,response);
		}else if("listPaintingByArtist".equals(op)){
			listPaintingByArtist(request,response);
		}else if("listPaintingByWay".equals(op)){
			listPaintingByWay(request,response);
		}else if("listPaintingByPrice".equals(op)){
			listPaintingByPrice(request,response);
		}else if("listPaintingByName".equals(op)){
			listPaintingByName(request,response);
		}else if("buyPainting".equals(op)){
			buyPainting(request,response);
		}else if("delOneItem".equals(op)){
			delOneItem(request,response);
		}else if("changeNum".equals(op)){
			changeNum(request,response);
		}else if("customerRegist".equals(op)){
			customerRegist(request,response);
		}else if("login".equals(op)){
			login(request,response);
		}else if("logout".equals(op)){
			logout(request,response);
		}else if("genOrder".equals(op)){
			try {
				genOrder(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void genOrder(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException, SQLException {
		HttpSession session=request.getSession();
		Customer customer=(Customer) session.getAttribute("customer");
		if(customer==null){
			response.getWriter().write("Будь ласка, увійдіть: ...");
			response.setHeader("Refresh", "2;URL="+request.getContextPath());
			return ;
		}

		Cart cart=(Cart) request.getSession().getAttribute("cart");

		Order order=new Order();
		order.setOrdernum(OrderNumUtil.genOrderNum());
		order.setPrice(cart.getPrice());
		order.setNumber(cart.getNumber());
		order.setCustomer(customer);


		List<OrderItem>  oItems=new ArrayList<OrderItem>();
		for(Map.Entry<String, CartItem>  me:cart.getItems().entrySet()){
			OrderItem item=new OrderItem();
			item.setId(UUID.randomUUID().toString());
			item.setNumber(me.getValue().getNumber());
			item.setPrice(me.getValue().getPrice());
			item.setPainting(me.getValue().getPainting());
			oItems.add(item);
		}
		order.setItems(oItems);
		s.genOrder(order);
		request.setAttribute("order", order);
		request.getRequestDispatcher("/shop/pay.jsp").forward(request, response);
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().removeAttribute("customer");
		response.getWriter().write("Ви успішно вийшли ...");
		response.setHeader("Refresh", "1;URL="+request.getContextPath()+"/servlet/ClientServlet?op=listPaintings");
		
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		Customer customer=s.login(username, password);
		if(customer==null){
			response.getWriter().write("Неправильне ім’я користувача або пароль .... Зачекайте 5 секунд.");
			response.setHeader("Refresh", "5;URL="+request.getContextPath()+"/login.jsp");
			return;
		}
		request.getSession().setAttribute("customer", customer);
		response.getWriter().write("Вхід пройшов успішно");
		response.setHeader("Refresh", "2;URL="+request.getContextPath() + "/index.jsp");
		
	}

	private void customerRegist(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Customer customer= WebUtil.fillBean(request,Customer.class);
		customer.setCode(UUID.randomUUID().toString());
		customer.setActived(true);
		s.registCustomer(customer);
		
		request.setAttribute("message", "Реєстрація пройшла успішно");
		request.getRequestDispatcher("/shop/message.jsp").forward(request, response);

	}


	private void changeNum(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String paintId=request.getParameter("paintId");
		Cart cart=(Cart) request.getSession().getAttribute("cart");
		CartItem item=cart.getItems().get(paintId);
		item.setNumber(Integer.parseInt(request.getParameter("num")));
		
		response.sendRedirect(request.getContextPath()+"/shop/cart.jsp");
	}

	private void delOneItem(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String paintId=request.getParameter("paintId");
		Painting painting =s.findPaintingById(paintId);
		
		Cart cart=(Cart) request.getSession().getAttribute("cart");
		cart.getItems().remove(paintId);
		
		response.sendRedirect(request.getContextPath()+"/shop/cart.jsp");
	}

	private void buyPainting(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String paintId=request.getParameter("paintId");
		Painting painting =s.findPaintingById(paintId);
		
		HttpSession session=request.getSession();
		Cart cart=(Cart) session.getAttribute("cart");
		if(cart==null){
			cart=new Cart();
			session.setAttribute("cart", cart);
		}
		cart.addPainting2Items(painting);
		request.setAttribute("message", "Покупка пройшла успішно");

		request.getRequestDispatcher("/shop/cart.jsp").forward(request,response);
	}

	private void listPaintingByCategory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Artist>  as=s.findAllArtists();
		List<Category>  cs=s.findAllCategories();
		List<Way> ws = s.findAllWays();
		request.setAttribute("ws", ws);
		request.setAttribute("cs", cs);
		request.setAttribute("as", as);
		
		String num=request.getParameter("num");
		String categoryId=request.getParameter("categoryId");
		Page page=s.findPage(num,categoryId);
		
		page.setUrl("/servlet/ClientServlet?op=listPaintingByCategory&categoryId="+categoryId);
		request.setAttribute("page", page);

		request.getRequestDispatcher("/shop/index.jsp").forward(request, response);
	}

	private void listPaintingByArtist(HttpServletRequest request,
									HttpServletResponse response) throws ServletException, IOException {
		List<Artist>  as=s.findAllArtists();
		List<Category>  cs=s.findAllCategories();
		List<Way> ws = s.findAllWays();
		request.setAttribute("ws", ws);
		request.setAttribute("cs", cs);
		request.setAttribute("as", as);

		String num=request.getParameter("num");
		String artistId=request.getParameter("artistId");
		Page page=s.findPageOfArtist(num,artistId);

		page.setUrl("/servlet/ClientServlet?op=listPaintingByArtist&artistId="+artistId);
		request.setAttribute("page", page);

		request.getRequestDispatcher("/shop/index.jsp").forward(request, response);
	}

	private void listPaintingByWay(HttpServletRequest request,
									  HttpServletResponse response) throws ServletException, IOException {
		List<Artist>  as=s.findAllArtists();
		List<Category>  cs=s.findAllCategories();
		List<Way> ws = s.findAllWays();
		request.setAttribute("ws", ws);
		request.setAttribute("cs", cs);
		request.setAttribute("as", as);

		String num=request.getParameter("num");
		String wayId=request.getParameter("wayId");
		Page page=s.findPageOfWay(num,wayId);

		page.setUrl("/servlet/ClientServlet?op=listPaintingByWay&wayId="+wayId);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/shop/index.jsp").forward(request, response);
	}

	private void listPaintingByPrice(HttpServletRequest request,
								  HttpServletResponse response) throws ServletException, IOException {
		List<Artist>  as=s.findAllArtists();
		List<Category>  cs=s.findAllCategories();
		List<Way> ws = s.findAllWays();
		request.setAttribute("ws", ws);
		request.setAttribute("cs", cs);
		request.setAttribute("as", as);

		String num=request.getParameter("num");
		String sortType=request.getParameter("sortType");
		Page page=s.findPageSorted(num,sortType);

		page.setUrl("/servlet/ClientServlet?op=listPaintingByPrice&sortType="+sortType);
		request.setAttribute("page", page);

		request.getRequestDispatcher("/shop/index.jsp").forward(request, response);
	}

	private void listPaintingByName(HttpServletRequest request,
								 HttpServletResponse response) throws ServletException, IOException {
		List<Artist>  as=s.findAllArtists();
		List<Category>  cs=s.findAllCategories();
		List<Way> ws = s.findAllWays();
		request.setAttribute("ws", ws);
		request.setAttribute("cs", cs);
		request.setAttribute("as", as);

		String num=request.getParameter("num");
		String name=request.getParameter("name");
		Page page=s.findPageOfName(num,name);

		if (page == null){
			request.setAttribute("message", "Не знайдено жодної картини з назвою <span style='color: #cc7722; font-weight: bold'>" + name + "</span>");
			request.getRequestDispatcher("/shop/message.jsp").forward(request, response);
		} else {
			page.setUrl("/servlet/ClientServlet?op=listPaintingByName&name="+name);
			request.setAttribute("page", page);
			request.getRequestDispatcher("/shop/index.jsp").forward(request, response);
		}
	}

	private void listPaintings(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Category>  cs=s.findAllCategories();
		List<Artist> as = s.findAllArtists();
		List<Way> ws = s.findAllWays();

		request.setAttribute("cs", cs);
		request.setAttribute("as", as);
		request.setAttribute("ws", ws);

		String num=request.getParameter("num");
		Page page=s.findPage(num);
		page.setUrl("/servlet/ClientServlet?op=listPaintings");
		request.setAttribute("page", page);
		request.getRequestDispatcher("/shop/index.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
