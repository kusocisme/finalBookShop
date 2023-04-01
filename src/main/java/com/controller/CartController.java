package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DTOs.BusinessDtos.CartDTO;
import com.model.*;

@WebServlet("/cart")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CartController() {
	}

	/*
	 * Text payment So the: 9704198526191432198 Ngay phat hanh: 07/15 Ten: NGUYEN
	 * VAN A OTP: 123456
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nextUrl = "WEB-INF/cart.jsp";
		Cart cart = new Cart();

		// get current action
		String action = request.getParameter("action");

		if (action == null) {
			action = "CART";
		}

		int userId;
		try {
			userId = (int) request.getSession().getAttribute("userId");
		} catch (Exception e) {
			String url = request.getParameter("url");
			request.setAttribute("url", url);
			response.sendRedirect("login");
			return;
		}

		if (action.equals("CART")) {
			// update cart in cart page
			CartDTO cartDTO = cart.getUserCart(userId);
			request.setAttribute("cart", cartDTO);
		} else if (action.equals("ADD")) {
			// Add item to cart
			int cartId = (int) request.getSession().getAttribute("cartId");
			int productId = Integer.parseInt(request.getParameter("productId"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			cart.addItem(cartId, productId, quantity);
			// PRG design pattern
			response.sendRedirect("cart");
			return;
		} else if (action.equals("UPDATE")) {
			// Update item quantity
			int newQuantity = Integer.parseInt(request.getParameter("quantity"));
			int itemId = Integer.parseInt(request.getParameter("id"));
			cart.updateQuantity(itemId, newQuantity);
			// PRG design pattern
			response.sendRedirect("cart");
			return;
		} else if (action.equals("REMOVE")) {
			// Remove item from cart
			int itemId = Integer.parseInt(request.getParameter("id"));
			cart.removeItem(itemId);
			// PRG design pattern
			response.sendRedirect("cart");
			return;
		} else if (action.equals("Pay")) {

		}

		request.getRequestDispatcher(nextUrl).forward(request, response);
	}

}
