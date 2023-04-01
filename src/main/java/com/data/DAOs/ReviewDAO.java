package com.data.DAOs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.data.DbUtil;
import com.model.Review;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ReviewDAO {
    public ReviewDAO() {

    }

    @SuppressWarnings("unchecked")
    public List<Review> getReviews() throws Exception {
        try {
            return DbUtil.getSessionFactory().openSession().createQuery("from Review").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Review> getReviews(String productId) throws Exception {

        EntityManager em = DbUtil.getSessionFactory().createEntityManager();

        String hql = "from Review as r where r.product.id = :productId";
        TypedQuery<Review> query = em.createQuery(hql, Review.class);
        query.setParameter("productId", Integer.parseInt(productId));

        try {
            List<Review> reviews = query.getResultList();
            return reviews;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Review getReview(String id) throws Exception {
        Transaction transaction = null;
        Review result = null;
        try (Session session = DbUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            result = session.get(Review.class, Integer.parseInt(id));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }

        return result;
    }

    public Review addReview(Review review) {
        Transaction transaction = null;
        try (Session session = DbUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(review);
            transaction.commit();

            return review;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
            return null;
        }
    }

    public void updatReview(Review review) {
        Transaction transaction = null;
        try (Session session = DbUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            session.update(review);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Review deleteReview(int id) {
        Transaction transaction = null;
        Review review = null;
        try (Session session = DbUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            review = session.get(Review.class, id);
            if (review != null) {
                session.delete(review);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return review;
    }
}
