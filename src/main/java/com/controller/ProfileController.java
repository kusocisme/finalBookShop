package com.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.data.DAOs.*;
import com.model.*;

@WebServlet("/profile")
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDAO orderDAO = null;
	private UserDAO userDao = null;

	public ProfileController() {
		super();
		userDao = new UserDAO();
		orderDAO = new OrderDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			// get current action
			String action = request.getParameter("action");

			if (action == null) {
				action = "View";
			}

			if (action.equals("View")) {
				viewProfile(request, response);
			} 
			else if (action.equals("Update")) {
				updateProfile(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("login");
		}
	}

	@SuppressWarnings("unused")
	private void viewHistory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			List<Order> list_order = null;
			Object userId = request.getSession().getAttribute("userId");
			orderDAO.getListOrder();
			request.setAttribute("list_order", list_order);
		}
		catch (Exception e) {
		
		}
		
	}

	private void updateProfile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int userId = (int) request.getSession().getAttribute("userId");
		User user = new User();
		user.setId(userId);
		user.setEmail(request.getParameter("email"));
		user.setFname(request.getParameter("fname"));
		user.setLname(request.getParameter("lname"));
		user.setGender(request.getParameter("gender"));
		try {
			user.setBdate(Date.valueOf(request.getParameter("bdate")));
		} catch (Exception e) {
			user.setBdate(null);
		}
		userDao.updateUser(user);

		response.sendRedirect("profile");
	}

	private void viewProfile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Item> items = null;
		List<Item> my_product = new ArrayList<Item>();
		Object userId = request.getSession().getAttribute("userId");
		User user = userDao.getUser(userId.toString());
		// get user to profile

		List<Order> list_order = orderDAO.getListOrderByUserId(userId.toString());
		// list the order of user
		for (Order order : list_order) {
			// list item in order by another user
			items = userDao.getMyBook(order.getId());

			// because items is list so we iterate, then add all them in my_product
			for (Item item : items)
				my_product.add(item);

		}

		request.setAttribute("list_item", my_product);
		request.setAttribute("list_order", list_order);
		request.setAttribute("user", user);
		String nextUrl = "WEB-INF/profile.jsp";
		request.getRequestDispatcher(nextUrl).forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

}
