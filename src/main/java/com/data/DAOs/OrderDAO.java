package com.data.DAOs;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.hibernate.*;

import com.data.DbUtil;
import com.model.Item;
import com.model.LineItem;
import com.model.Order;
import com.model.Product;
import com.model.User;

public class OrderDAO {
	public OrderDAO() {}
	public Order CreateOrderForUser(Order order, User user) {
		Transaction transaction = null;
		try (Session session = DbUtil.getSessionFactory().openSession()) {

			transaction = session.beginTransaction();
			
			order.setUser(user);
			session.save(order);
			transaction.commit();
			return order;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}

    public void addAllItem(int uid, Order order) throws Exception {
    	CartDAO carDao = new CartDAO();
    	UserDAO userDao = new UserDAO();
    	ProductDAO productDao = new ProductDAO();
		List<LineItem> items = carDao.getListItemForUser(uid);
		for (LineItem line_item: items) {
			Product product = productDao.getProduct(line_item.getProduct().getId());
			Item item = new Item(1, product.price, order, product);
			userDao.addItemToMyProduct(item);
		}	
    }
	@SuppressWarnings("unchecked")
    public List<Order> getListOrderByUserId(String id) throws Exception{
		int uid = Integer.parseInt(id);
    	try {
    		return DbUtil.getSessionFactory().openSession().createQuery("From Order O where O.user.id = " + "'" + uid + "'").getResultList();
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
	@SuppressWarnings("unchecked")
    public List<Order> getListOrder() throws Exception{
    	try {
    		return DbUtil.getSessionFactory().openSession().createQuery("From Order").getResultList();
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
}
