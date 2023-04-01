package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.Seed;
import com.data.DAOs.ProductDAO;
import com.google.gson.Gson;
import com.model.Product;

@WebServlet("/home")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO productDao;

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			productDao = new ProductDAO();

		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	public HomeController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String theCommand = request.getParameter("command");
			if (theCommand == null) {
				theCommand = "HOME";
			}
			switch (theCommand) {
				case "HOME":
					// create init data
					new Seed().doSeed();
					request.setAttribute("username", request.getSession().getAttribute("username"));
					loadPopularOrder(request, response);
					loadTrendingBook(request, response);
					loadRomanceBook(request, response);
					loadHumanBook(request, response);
					loadBusinessBook(request, response);

					goHomePage(request, response);

					break;
				case "LOAD":
					detailProduct(request, response);
					break;
				case "More":
					loadHumanBookMore(request, response);
					break;
				case "Add":
					loadBussinessBookMore(request, response);
					break;
				default:
					goHomePage(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadBussinessBookMore(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		int status;
		List<Product> list_product = new ArrayList<Product>();
		int index = Integer.parseInt(request.getParameter("amount2"));
		list_product = productDao.getHumanBook(index);
		request.setAttribute("amount2", index);
		PrintWriter writer = response.getWriter();
		if (action.equals("Prev"))
			status = 0;
		else if (action.equals("Next"))
			status = 1;
		else
			status = 2;
		writer.println(productDao.ReturnListBussinessProductByString(list_product, index, status));
	}

	private void loadBusinessBook(HttpServletRequest request, HttpServletResponse response) {
		List<Product> list_product = new ArrayList<Product>();
		list_product = productDao.getHumanBook(57);
		request.setAttribute("bussin_book", list_product);

	}

	private void loadHumanBook(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		List<Product> list_product = new ArrayList<Product>();
		list_product = productDao.getHumanBook(36);
		request.setAttribute("human_book", list_product);
	}

	private void loadHumanBookMore(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		int status;
		List<Product> list_product = new ArrayList<Product>();
		int index = Integer.parseInt(request.getParameter("amount"));
		list_product = productDao.getHumanBook(index);
		request.setAttribute("amount", index);
		PrintWriter writer = response.getWriter();
		if (action.equals("Prev"))
			status = 0;
		else if (action.equals("Next"))
			status = 1;
		else
			status = 2;
		writer.println(productDao.ReturnListProductByString(list_product, index, status));

	}

	private void loadRomanceBook(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Product> list_product = new ArrayList<Product>();
		list_product = productDao.getRomanceBook();
		request.setAttribute("romance_book", list_product);

	}

	private void loadTrendingBook(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Product> list_product = new ArrayList<Product>();
		list_product = productDao.getProducts(36);
		request.setAttribute("trending_book", list_product);

	}

	private void loadPopularOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Product> list_product = new ArrayList<Product>();
		list_product = productDao.getPopularOrder();
		request.setAttribute("po_order", list_product);
	}

	private void detailProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String productId = null;
		Product product = null;

		try {
			productId = request.getParameter("productID");
			product = productDao.getProduct(Integer.parseInt(productId));

		} catch (Exception e) {
			log("productDao error", e);
		}
		request.setAttribute("product", product);
		String path = "/product?command=LOAD&id=" + request.getParameter("productID");
		// request.getRequestDispatcher("WEB-INF/productItem.jsp").forward(request,
		// response);
		response.sendRedirect(request.getContextPath() + path);
	}

	private void goHomePage(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// set popular
		List<Product> list_product = new ArrayList<Product>();

		list_product = productDao.getProducts();

		String action = request.getParameter("action");
		String next = request.getParameter("next");
		String sorting = request.getParameter("sort");

		String index_at = request.getParameter("index");

		if (sorting == null)
			sorting = "INC";

		int index;
		try {
			// set index
			if (next == null)
				index = 0;

			else
				index = Integer.parseInt(next);

			// get action to execute
			if (action == null)
				action = "";

			if (action.equals("PREV"))
				index--;

			else if (action.equals("NEXT"))
				index++;

			else if (action.equals("INDEX"))
				index = Integer.parseInt(index_at) - 1;

			int limit = list_product.size() / 15;
			// when user next or prev over page available we set default for this case
			if (index < 0)
				index = 0;
			else if (index > limit - 1)
				index = limit;

			if (sorting.equals("INC")) {
				list_product = productDao.getProducts(index * 15);
				request.setAttribute("sorting", "INC");
			} else {
				list_product = productDao.getProductsDes(index * 15);
				request.setAttribute("sorting", "DES");
			}

			// Section Side
			if (action.equals("Popular")) {
				list_product = productDao.getProducts(60);
				limit = list_product.size() / 15;
				Gson gson = new Gson();
				String json = gson.toJson(list_product);
				System.out.println("Json: " + json);
			} else if (action.equals("New")) {
				list_product = productDao.getProducts(18);
				limit = list_product.size() / 15;
			} else if (action.equals("Sell")) {
				list_product = productDao.getPopularOrder();
				limit = list_product.size() / 15;
			} else if (action.equals("Roman")) {
				list_product = productDao.getRomanceBook();
				limit = list_product.size() / 15;
			} else if (action.equals("Adventure")) {
				list_product = productDao.getAdventureBook();
				limit = list_product.size() / 15;
			} else if (action.equals("Action")) {
				list_product = productDao.getActionBook();
				limit = list_product.size() / 15;
			} else if (action.equals("Business")) {
				list_product = productDao.getBusinessBook();
				limit = list_product.size() / 15;
			}

			request.setAttribute("list_product", list_product);

			request.setAttribute("next", index);
			request.setAttribute("max", limit);

		} catch (Exception e) {
			log("productDao error", e);
		}

		request.setAttribute("amount", 36);
		request.setAttribute("amount2", 57);
		request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
