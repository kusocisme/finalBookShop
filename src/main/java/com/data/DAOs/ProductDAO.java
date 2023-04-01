package com.data.DAOs;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.hibernate.*;
import com.data.DbUtil;
import com.model.Photo;
import com.model.Product;
import com.model.Review;
import com.model.User;

public class ProductDAO {
	public ProductDAO() {
	}

	@SuppressWarnings("unchecked")
	public List<Product> getProducts() throws Exception {
		try {
			return DbUtil.getSessionFactory().openSession().createQuery("From Product").getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@SuppressWarnings("unchecked")
	public List<Product> getProducts(int index) throws Exception {
		try {
			return DbUtil.getSessionFactory().openSession().createQuery("From Product P ORDER BY P.price")
					.setFirstResult(index).setMaxResults(15).list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Product> getProductsDes(int index) throws Exception {
		try {
			return DbUtil.getSessionFactory().openSession().createQuery("From Product P ORDER BY P.price DESC")
					.setFirstResult(index).setMaxResults(15).list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public List<Product> getHumanBook(int index) {
		try {
			return DbUtil.getSessionFactory().openSession().createQuery("From Product")
						.setFirstResult(index).setMaxResults(4).list();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

	@SuppressWarnings("unchecked")
	public List<Product> getRomanceBook() throws Exception {
		try {
			return DbUtil.getSessionFactory().openSession()
					.createQuery("From Product P where P.typeBook = " + "'" + "Romance" + "'").getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> getAdventureBook() throws Exception {
		try {
			return DbUtil.getSessionFactory().openSession()
					.createQuery("From Product P where P.typeBook = " + "'" + "Adventure" + "'").getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public List<Product> getBusinessBook() throws Exception {
		try {
			return DbUtil.getSessionFactory().openSession()
					.createQuery("From Product P where P.typeBook = " + "'" + "Buisiness & Management"+ "'" +" or P.typeBook ="+ "'" + "Historical" +"'").getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public List<Product> getActionBook() throws Exception {
		try {
			return DbUtil.getSessionFactory().openSession()
					.createQuery("From Product P where P.typeBook = " + "'" + "Action"+ "'" +" or P.typeBook ="+ "'" + "Action, Comedy" +"'").getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Product> getPopularOrder() throws Exception {
		EntityManager em = DbUtil.getSessionFactory().createEntityManager();
		String sql = "Select p from Product p inner join Item i on p.id = i.product.id";
		TypedQuery<Product> q = em.createQuery(sql, Product.class);
		try {
			List<Product> list = q.getResultList();

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}
	public Product addProducts(Product product) {
		Transaction transaction = null;
		try (Session session = DbUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(product);
			transaction.commit();
			return product;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}

	public void updateProducts(Product product) {
		EntityManager em = DbUtil.getSessionFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			Product theProduct = em.find(Product.class, product.getId());
			theProduct.setNameAuthor(product.getNameAuthor());
			theProduct.setNXB(product.getNXB());
			theProduct.setProductName(product.getProductName());
			theProduct.setDescription(product.getDescription());
			theProduct.setPrice(product.getPrice());
			theProduct.setSupplier(product.getSupplier());
			theProduct.setCodeProduct(product.getCodeProduct());
			theProduct.setDiscount(product.getDiscount());
			em.merge(theProduct);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public void deleteProduct(int id) {
		Transaction transaction = null;
		Product product = null;
		try (Session session = DbUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object
			product = session.get(Product.class, id);
			if (product != null) {
				session.delete(product);
			}
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public Product getProduct(int id) {
		Transaction transaction = null;
		Product product = null;
		try (Session session = DbUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object
			product = session.get(Product.class, id);
			List<Photo> photos = product.getPhotos();
			for (Photo photo : photos) {
				photo.getId();
			}
			product.setPhotos(photos);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return product;
	}

	public List<Product> getByName(String keyword) {
		String hql = "Select p from Product p where p.name like :keyword";
		EntityManager em = DbUtil.getSessionFactory().createEntityManager();
		TypedQuery<Product> query = em.createQuery(hql, Product.class);

		try {
			query.setParameter("keyword", "%" + keyword + "%");

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	public Product getProductwithFileAndReview(int productId) {
		Transaction transaction = null;
		try (Session session = DbUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			Product product = session.get(Product.class, productId);
			List<Review> reviews = product.getReviews();
			product.setReviews(reviews);

			transaction.commit();
			return product;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}
	private String makeStar(int star) {
        String stars = "";
        String starCheck = "   <i class=\"star-gold fas fa-star\"></i>";
        String starUnCheck = "   <i class=\"star-gold far fa-star\"></i>";

        for (int i = 0; i < star; i++) {
            stars += starCheck;
        }

        for (int i = 0; i < 5 - star; i++) {
            stars += starUnCheck;
        }

        return stars;
    }
	public String ReturnListProductByString(List<Product> list_product, int index, int check) {
		String productList = "";
		String footer = "";
		for(Product product: list_product) {
			productList += "<div class=\"number_product grid_column-6\">\r\n"
					+ "            <div class=\"side-contain\" style=\"display: flex; flex-wrap: nowrap;\">\r\n"
					+ "             <div class=\"img-sidebar\">\r\n"
					+ "              <a href=\"product?command=LOAD&id="+ product.getId() +"\">\r\n"
					+ "                <img src=\"" + product.getPictureUrl() +"\" alt=\"\" class=\"img-card-sidebar\">\r\n"
					+ "               </a>\r\n"
					+ "              </div>"
					+ "              <div class=\"sidebar-content\">\r\n"
					+ "                <p class=\"trending-item-name\"> " + product.getProductName() + "</p>\r\n"
					+ "                <p class=\"trending-item-author\">" + product.getNameAuthor() + "</p>\r\n"
					+ "                <div class=\"product-action\">\r\n"
					+ "                  <span class=\"product-action-heart product-action-liked\">\r\n"
					+ "                    <i class=\"like-icon far fa-heart\"></i>\r\n"
					+ "                    <i class=\"liked-icon fas fa-heart\"></i>\r\n"
					+ "                  </span>\r\n"
					+ "					<div class=\"product-action-star\">\r\n"
					+ "                    "+ makeStar(product.getStar()) + ""
					+ "                  </div>\r\n"
					+ "                </div>\r\n"
					+ "                <div class=\"trending-item-price\">\r\n"
					+ "                  <span class=\"price-old mr-up\"> "+ product.getPrice() + "</span>\r\n"
					+ "                  <span class=\"price-current mr-up\"> " + product.getPriceDiscount() + "</span>\r\n"
					+ "                </div>\r\n"
					+ "              </div>\r\n"
					+ "            </div>\r\n"
					+ "          </div>"
					+ "";	
		}
		if (check == 0) index -= 4;
		else index += 4;
		footer ="<div class=\"footer-side hidden-s\" style=\"width: 100%;\">\r\n"
				+ "<center>\r\n "
				+ "<input class=\"index-product\" type=\"hidden\" name=\"amount\" value=\""+ index +"\">"
				+ "<button style=\"\" onclick=\"loadMore()\" type=\"button\" name=\"button\" class=\"btn-watch\">Xem thêm</button>\r\n"
				+ "</div>\r\n"
				+ "</center>";
		return productList + footer;
	}
	public String ReturnListBussinessProductByString(List<Product> list_product, int index, int check) {
		String productList = "";
		String footer = "";
		for(Product product: list_product) {
			productList += "<div class=\"number_product-2 grid_column-6\">\r\n"
					+ "            <div class=\"side-contain\" style=\"display: flex; flex-wrap: nowrap;\">\r\n"
					+ "             <div class=\"img-sidebar\">\r\n"
					+ "              <a href=\"product?command=LOAD&id="+ product.getId() +"\">\r\n"
					+ "                <img  src=\"" + product.getPictureUrl() +"\" alt=\"\" class=\"img-card-sidebar\">\r\n"
					+ "               </a>\r\n"
					+ "              </div>"
					+ "              <div class=\"sidebar-content\">\r\n"
					+ "                <p class=\"trending-item-name\"> " + product.getProductName() + "</p>\r\n"
					+ "                <p class=\"trending-item-author\">" + product.getNameAuthor() + "</p>\r\n"
					+ "                <div class=\"product-action\">\r\n"
					+ "                  <span class=\"product-action-heart product-action-liked\">\r\n"
					+ "                    <i class=\"like-icon far fa-heart\"></i>\r\n"
					+ "                    <i class=\"liked-icon fas fa-heart\"></i>\r\n"
					+ "                  </span>\r\n"
					+ "					<div class=\"product-action-star\">\r\n"
					+ "                    "+ makeStar(product.getStar()) + ""
					+ "                  </div>\r\n"
					+ "                </div>\r\n"
					+ "                <div class=\"trending-item-price\">\r\n"
					+ "                  <span class=\"price-old mr-up\"> "+ product.getPrice() + "</span>\r\n"
					+ "                  <span class=\"price-current mr-up\"> " + product.getPriceDiscount() + "</span>\r\n"
					+ "                </div>\r\n"
					+ "              </div>\r\n"
					+ "            </div>\r\n"
					+ "          </div>"
					+ "";
		}
		if (check == 0) index -= 4;
		else index += 4;
		footer ="<div class=\"footer-side hidden-s\" style=\"width: 100%;\">\r\n"
				+ "<center>\r\n "
				+ "<input class=\"index-product-2\" type=\"hidden\" name=\"amount2\" value=\""+ index +"\">"
				+ "<button style=\"\" onclick=\"loadInMore()\" type=\"button\" name=\"button\" class=\"btn-watch\">Xem thêm</button>\r\n"
				+ "</div>\r\n"
				+ "</center>";
		return productList + footer;
	}
	/*
	public List<Product> loadProductBySection(String action) throws Exception {
		// Section Side
		List<Product> list_product = new ArrayList<Product>();
		if(action.equals("Popular")) {
			list_product = getProducts(60);
		}
		else if(action.equals("New")) {
			list_product = getProducts(18);
		}
		else if(action.equals("Sell")) {
			list_product = getProducts(18);

		}
		else if(action.equals("Roman")) {
			list_product = getRomanceBook();
		}
		else if(action.equals("Adventure")) {
			list_product = getAdventureBook();

		}
		else if(action.equals("Action")) {
			list_product = getActionBook();
		}
		else if(action.equals("Business")) {
			list_product = getBusinessBook();
		}
		return list_product;
	}
	*/
}
