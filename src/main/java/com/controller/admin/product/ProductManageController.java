package com.controller.admin.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.DAOs.FileDAO;
import com.data.DAOs.OrderDAO;
import com.data.DAOs.PhotoDAO;
import com.data.DAOs.ProductDAO;
import com.model.File;
import com.model.Order;
import com.model.Photo;
import com.model.Product;
import com.services.CloudinaryUtil;

@WebServlet("/admin/product")
public class ProductManageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO;
	private OrderDAO orderDao;

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			productDAO = new ProductDAO();
			orderDao = new OrderDAO();
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	public ProductManageController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String theCommand = request.getParameter("command");

			if (theCommand == null) {
				theCommand = "Product";
			}

			switch (theCommand) {
			case "Product":
				listProduct(request, response);
				break;
			case "History Order":
				orderDetail(request, response);
				break;
			case "DetailOrder":
				orderUserDetail(request, response);
				break;
			case "ADD":
				addProduct(request, response);
				break;
			case "Load":
				loadProductForm(request, response);
				break;
			case "Update":
				updateProduct(request, response);
				break;
			case "Delete":
				deleteProduct(request, response);

			default:
				listProduct(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void orderUserDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = request.getParameter("userId");
		List<Order> order = null;
		try {
			order = orderDao.getListOrderByUserId(userId);
			request.setAttribute("detail_order", order);
			request.getRequestDispatcher("../WEB-INF/admin/userOrder.jsp").forward(request, response);
		}
		catch (Exception e) {
			//listProduct(request, response);
		}
		
	}

	private void orderDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Order> order = null;
		try {
			order = orderDao.getListOrder();
			request.setAttribute("list_order", order);
		} catch (Exception e) {
			log("productDao error", e);
		}
		request.getRequestDispatcher("../WEB-INF/admin/orderDetail.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));

		try {
			List<Photo> photos = new PhotoDAO().getProductPhotos(id);
			for (Photo photo : photos) {
				CloudinaryUtil.destroyItem(photo.getPublicId());
			}
			File file = new FileDAO().getProductFile(id);
			CloudinaryUtil.destroyItem(file.getPublicId());
		} catch (Exception e) {
			System.out.println("File delte or photo delete failse");
		}

		productDAO.deleteProduct(id);
		response.sendRedirect("product");
	}

	private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String nameAuthor = request.getParameter("author");
		String nxb = request.getParameter("nxb");
		String nameItem = request.getParameter("nameItem");
		String description = request.getParameter("description");
		int price = Integer.parseInt(request.getParameter("price"));
		int id = Integer.parseInt(request.getParameter("id"));
		String supplier = request.getParameter("supplier");
		int discount = Integer.parseInt(request.getParameter("discount"));
		String code = request.getParameter("code");
		Product theProduct = new Product(id, nameAuthor, description, nameItem, nxb, supplier, price);
		theProduct.setCodeProduct(code);
		theProduct.setDiscount(discount);
		productDAO.updateProducts(theProduct);
		response.sendRedirect("product?command=Load&id=" + id);
	}

	// Form for add or update product
	private void loadProductForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String theProductId = request.getParameter("id");
		if (theProductId != null) {
			request.setAttribute("FormCommand", "Update");
			Product theProduct = productDAO.getProduct(Integer.parseInt(theProductId));
			theProduct.setFile(new FileDAO().getProductFile(Integer.parseInt(theProductId)));
			request.setAttribute("item", theProduct);
		} else {
			request.setAttribute("FormCommand", "ADD");
			request.setAttribute("item", new Product());
		}
		request.getRequestDispatcher("../WEB-INF/admin/productForm.jsp").forward(request, response);
	}

	private void addProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String nameAuthor = request.getParameter("author");
		String nxb = request.getParameter("nxb");
		String nameItem = request.getParameter("nameItem");
		String description = request.getParameter("description");
		int price = Integer.parseInt(request.getParameter("price"));
		String supplier = request.getParameter("supplier");
		Product theProduct = new Product(nameAuthor, description, nameItem, nxb, supplier, price);
		theProduct = productDAO.addProducts(theProduct);
		response.sendRedirect("product?command=Load&id=" + theProduct.getId());
	}

	private void listProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Product> product = null;
		try {
			product = productDAO.getProducts();
			request.setAttribute("list", product);
		} catch (Exception e) {
			log("productDao error", e);
		}
		request.getRequestDispatcher("../WEB-INF/admin/product.jsp").forward(request, response);
	}
}