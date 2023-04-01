package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.data.DAOs.FileDAO;
import com.data.DAOs.ProductDAO;
import com.data.DAOs.ReviewDAO;
import com.model.Product;
import com.model.Review;

@WebServlet("/product")
public class ProductItemController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO;

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			productDAO = new ProductDAO();
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	public ProductItemController() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String nextUrl = "WEB-INF/productItem.jsp";
		res.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("utf-8");

		// get current action
		try {
			String action = req.getParameter("command");

			if (action == null) {
				action = "Go to productList.jsp";
			}

			switch (action) {
			case "LOAD":
				loadProductItem(req, res);
				loadReviews(req, res);
				break;
			default:
				loadProductItem(req, res);
			}

			req.getRequestDispatcher(nextUrl).forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadProductItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = 0;
		int userId = 0;
		Product product = null;
		HttpSession session = request.getSession();

		try {
			id = Integer.parseInt(request.getParameter("id"));
			product = Product.find(id);

			if (session.getAttribute("userId") != null) {
				userId = Integer.parseInt(session.getAttribute("userId").toString());

				System.out.println("User id: " + userId + " Product id: " + id);

				System.out.println("Is user own file: " + new FileDAO().isUserOwnThisFile(userId, id));
				request.setAttribute("isUserOwnFile", new FileDAO().isUserOwnThisFile(userId, id));
			}

			if (product == null) {
				response.sendRedirect(request.getContextPath() + "/home");
				return;
			}

			request.setAttribute("product", product);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadReviews(HttpServletRequest req, HttpServletResponse res) throws Exception {
		List<Review> reviews = null;
		String productId = req.getParameter("id");

		try {
			Product product = new ProductDAO().getProductwithFileAndReview(Integer.parseInt(productId));
			reviews = product.getReviews();

		} catch (Exception e) {
			System.out.println("reviewDAO error!" + e);
			log("reviewDAO error!", e);
		}

		req.setAttribute("reviews", reviews);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
