package com.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.data.DAOs.UserDAO;
import com.model.User;
import com.services.EmailService;
import com.services.HashService;

/**
 * Servlet implementation class ForgetPasswordController
 */
@WebServlet("/forgot-password")
public class ForgetPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String nextUrl;

	public ForgetPasswordController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		nextUrl = "WEB-INF/forgot-password.jsp";
		// get current action
		String action = request.getParameter("action");

		if (action == null) {
			action = "Go to Login.jsp";
		}

		if (action.equals("CheckEmail")) {
			CheckEmail(request, response, getServletContext());
		} else if (action.equals("resetPass")) {
			Boolean result = resetPass(request, response);
			if (result == true) {
				response.sendRedirect("login");
				return;
			}
		}

		request.getRequestDispatcher(nextUrl).forward(request, response);
	}

	public void CheckEmail(HttpServletRequest request, HttpServletResponse response, ServletContext context) {
		UserDAO userDAO = new UserDAO();
		String email = request.getParameter("email");
		User user = userDAO.getUserByEmail(email);
		if (user == null) {
			request.setAttribute("resetMessage", "We have no account match this email");
			return;
		}
		nextUrl = "verify";
		request.getSession().setAttribute("type", "passReset");
		String code = EmailService.getRandom();
		try {
			EmailService.sendEmail(context, email, "Email Verification",
					"Please using this code to reset your password: " + code);
		} catch (MessagingException e) {
			e.printStackTrace();
			request.setAttribute("resetMessage", "We have no account match this email");
		}
		request.getSession().setAttribute("email", email);
		request.getSession().setAttribute("code", code);
	}

	public Boolean resetPass(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		if (email == null) {
			request.setAttribute("resetMessage", "We have no account match this email");
			nextUrl = "WEB-INF/resetPass.jsp";
			return false;
		}

		String password = request.getParameter("password");
		String reEnter = request.getParameter("reEnter");
		if (!password.equals(reEnter)) {
			request.setAttribute("resetMessage", "Pass word not match");
			nextUrl = "WEB-INF/resetPass.jsp";
			return false;
		}

		UserDAO userDAO = new UserDAO();
		User user = userDAO.getUserByEmail(email);

		// Compute hash
		HashService hashService = new HashService();
		byte[] salt = hashService.generateSalt();
		byte[] hash = hashService.doHash(password.getBytes(), salt);

		Boolean result = userDAO.updateUserPassword(user.getId(), hash, salt);

		return result;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
