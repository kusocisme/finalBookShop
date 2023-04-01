package com.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.data.DAOs.ReviewDAO;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "review")
public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String date;
    @Type(type = "text")
    private String content;
    private int stars;

    // Relation
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
    @ManyToOne
    private User user;

    private static ReviewDAO reviewDAO = new ReviewDAO();

    public Review() {

    }
    
    public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Review(int id, String date, String content, User user, Product product, int stars) {
        super();
        this.id = id;
        this.date = date;
        this.stars = stars;
        this.content = content;
        this.user = user;
        this.product = product;
    }

    public Review(String date, String content, User user, Product product, int stars) {
        super();
        this.date = date;
        this.stars = stars;
        this.content = content;
        this.user = user;
        this.product = product;
    }

    public static Review createReview(Review review) {
        try {
            return reviewDAO.addReview(review);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Review deleteReview(String id) {
        try {
            return reviewDAO.deleteReview(Integer.parseInt(id));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean updateReview(Review review) {
        try {
            reviewDAO.updatReview(review);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static Review getReview(String id) {
        try {
            return reviewDAO.getReview(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public int getId() {
        return this.id;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getStars() {
        return this.stars;
    }

    public String getUserName() {
        return this.user.getUsername();
    }

    public int getProductId() {
        return this.product.getId();
    }

    public String getContent() {
        return this.content;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setContent(String content) {
        this.content = content;
    }
}