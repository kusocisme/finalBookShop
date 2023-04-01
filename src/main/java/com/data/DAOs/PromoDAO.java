package com.data.DAOs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.taglibs.standard.lang.jstl.test.Bean1;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.data.DbUtil;
import com.model.CheckPromo;
import com.model.Product;
import com.model.Promo;
import com.model.User;

public class PromoDAO {
	public PromoDAO() {
	}
	public void addPromoCode(Promo promo) {
		Transaction transaction = null;
		try (Session session = DbUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(promo);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	public void addProcodeUsed(CheckPromo promo) {
		Transaction transaction = null;
		try (Session session = DbUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(promo);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	public List<CheckPromo> checkPromoCode(int pid, int uid) throws Exception {
		try {
			return DbUtil.getSessionFactory().openSession()
					.createQuery("From CheckPromo P where P.promoId = "  + pid  + " and P.userId ="+ uid ).getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public void deletePromoCode(int id) {
		Transaction transaction = null;
		Promo promo = null;
		try (Session session = DbUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object
			promo = session.get(Promo.class, id);
			if (promo != null) {
				session.delete(promo);
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
	public Promo getPromoCode(String code) {
		EntityManager em = DbUtil.getSessionFactory().createEntityManager();
		String sql = "Select p from Promo p where p.code =:pcode";
		TypedQuery<Promo> q = em.createQuery(sql, Promo.class);
		q.setParameter("pcode", code);
		try {
			Promo promo = q.getSingleResult();
			
			return promo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	
}
