package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DTOs.BusinessDtos.RegisterDTO;
import com.model.User;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterController() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nextUrl = "WEB-INF/register.jsp";

		// get current action
		String action = request.getParameter("action");

		if (action == null) {
			action = "Go to register.jsp";
		}

		if (action.equals("REGISTER")) {
			String email = request.getParameter("email");
			String username = request.getParameter("username");
			String pass = request.getParameter("password");
			String reEnter = request.getParameter("reEnter");

			RegisterDTO registerDTO = new RegisterDTO(email, username, pass, reEnter);
			request.getSession().setAttribute("register", registerDTO);
			if (new User().register(registerDTO, getServletContext())) {
				nextUrl = "verify";
				request.getSession().setAttribute("type", "register");
			} else {
				request.setAttribute("registerMessage", registerDTO.getErrorMessage());
				nextUrl = "WEB-INF/register.jsp";
			}
		}

		request.getRequestDispatcher(nextUrl).forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}