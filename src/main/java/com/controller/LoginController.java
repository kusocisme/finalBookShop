package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DTOs.BusinessDtos.CartDTO;
import com.DTOs.BusinessDtos.LoginDTO;
import com.data.DAOs.UserDAO;
import com.model.Cart;
import com.model.User;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO userDao = null;

	public LoginController() {
		super();
		userDao = new UserDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nextUrl = "WEB-INF/login.jsp";

		// get current action
		String action = request.getParameter("action");

		if (action == null) {
			action = "Go to Login.jsp";
		}

		if (action.equals("LOGIN")) {
			String url = request.getParameter("url");
			LoginDTO loginDTO = new LoginDTO();
			loginDTO.setUsername(request.getParameter("username"));
			loginDTO.setPassword(request.getParameter("password"));
			if (new User().login(loginDTO)) {
				// get username and id
				request.getSession().setAttribute("username", loginDTO.getUsername());
				request.getSession().setAttribute("userId", loginDTO.getId());
				request.getSession().setAttribute("role", loginDTO.getRoleName());
				request.getSession().setAttribute("roleId", loginDTO.getRoleId());

				if (loginDTO.getRoleId() == 3) {
	
					if (url!= null) nextUrl = request.getContextPath() + "/" + url;	
					
					else nextUrl = "home";
				} 
				else if (loginDTO.getRoleId() == 1 || loginDTO.getRoleId() == 2) {
					nextUrl = "admin/product";
				}

				// Load Cart for user
				CartDTO cartDTO = new Cart().getUserCart(loginDTO.getId());
				request.getSession().setAttribute("cartId", cartDTO.getId());
				
				response.sendRedirect(nextUrl);
				return;
			} else {
				request.setAttribute("loginMessage", "Password or username incorrect");
			}
		}
		request.setAttribute("url", request.getParameter("url"));
		request.getRequestDispatcher(nextUrl).forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}