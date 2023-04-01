package com.data.DAOs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import javax.persistence.TypedQuery;

import com.DTOs.BusinessDtos.LoginDTO;
import com.data.DbUtil;
import com.model.Item;
import com.model.User;
import com.model.Order;

import org.hibernate.*;

public class UserDAO {
	// SELECT ----------------------------------------------------
	public User getUser(String id) {
		Transaction transaction = null;
		int ids = Integer.parseInt(id);
		User user = null;
		try (Session session = DbUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			user = session.get(User.class, ids);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return user;
	}

	public User getUserByUserName(String username) {
		EntityManager em = DbUtil.getSessionFactory().createEntityManager();
		String sql = "Select u from User u where u.username =:uname";
		TypedQuery<User> q = em.createQuery(sql, User.class);
		q.setParameter("uname", username);
		try {
			User user = q.getSingleResult();

			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	public User getUserByEmail(String email) {
		EntityManager em = DbUtil.getSessionFactory().createEntityManager();
		String sql = "Select u from User u where u.email =:email";
		TypedQuery<User> q = em.createQuery(sql, User.class);
		q.setParameter("email", email);
		try {
			User user = q.getSingleResult();

			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	public void getPasswordHashAndSalt(LoginDTO loginDTO) {
		Transaction transaction = null;
		List<User> rsList = null;
		try (Session session = DbUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<User> cr = cb.createQuery(User.class);
			Root<User> root = cr.from(User.class);
			cr.multiselect(root.get("username"), root.get("passwordHash"), root.get("passwordSalt"));
			cr.where(cb.equal(root.get("username"), loginDTO.getUsername()));

			rsList = session.createQuery(cr).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		if (rsList.size() > 0) {
			loginDTO.setPasswordHash(rsList.get(0).getPasswordHash());
			loginDTO.setPasswordSalt((rsList.get(0).getPasswordSalt()));
			loginDTO.setId((rsList.get(0).getId()));
		}
	}

	public Boolean isUsernameExisted(String username) {
		Transaction transaction = null;
		List<User> rsList = null;
		try (Session session = DbUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<User> cr = cb.createQuery(User.class);
			Root<User> root = cr.from(User.class);
			cr.multiselect(root.get("username"));
			cr.where(cb.equal(root.get("username"), username));

			rsList = session.createQuery(cr).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		if (rsList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean isEmailExisted(String email) {
		Transaction transaction = null;
		List<User> rsList = null;
		try (Session session = DbUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<User> cr = cb.createQuery(User.class);
			Root<User> root = cr.from(User.class);
			cr.multiselect(root.get("username"), root.get("email"));
			cr.where(cb.equal(root.get("email"), email));

			rsList = session.createQuery(cr).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		if (rsList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Item> getMyBook(int orderId) {
		try {
			return DbUtil.getSessionFactory().openSession()
					.createQuery("From Item I where I.order.id = " + "'" + orderId + "'").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	// INSERT ----------------------------------------------------
	public User addUser(User user) {
		Transaction transaction = null;
		try (Session session = DbUtil.getSessionFactory().openSession()) {

			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
			return user;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}

	public Item addItemToMyProduct(Item item) {
		Transaction transaction = null;
		try (Session session = DbUtil.getSessionFactory().openSession()) {

			transaction = session.beginTransaction();
			session.save(item);
			transaction.commit();
			return item;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}

	// UPDATE ----------------------------------------------------
	public void updateUser(User user) {
		Transaction transaction = null;
		try (Session session = DbUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			User dbUser = session.get(User.class, user.getId());
			dbUser.setBdate(user.getBdate());
			dbUser.setEmail(user.getEmail());
			dbUser.setFname(user.getFname());
			dbUser.setLname(user.getLname());
			dbUser.setGender(user.getGender());
			session.update(dbUser);

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public Boolean updateUserPassword(int userId, byte[] passwordHash, byte[] passwordSalt) {
		Transaction transaction = null;
		try (Session session = DbUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			User dbUser = session.get(User.class, userId);
			dbUser.setPasswordHash(passwordHash);
			dbUser.setPasswordSalt(passwordSalt);
			session.update(dbUser);

			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return false;
		}
	}

	// DELETE ----------------------------------------------------
	public User deleteUser(int id) {
		Transaction transaction = null;
		User user = null;
		try (Session session = DbUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			user = session.get(User.class, id);
			if (user != null) {
				session.delete(user);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return user;
	}
}
