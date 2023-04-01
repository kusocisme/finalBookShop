package com.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "user_order")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String createAt;
    private String status;
    private float amount;
    private String methodPayment;
    // Relation
    @ManyToOne
    private User user;
    
    @OneToMany(mappedBy = "order")
    private List<Item> items = new ArrayList<Item>();

    public Order(String createAt, String status, float amount, String method, User user) {
        this.createAt = createAt;
        this.status = status;
        this.amount = amount;
        this.user = user;
        this.methodPayment = method;
    }
    public Order() {}
    
    public String getMethodPayment() {
		return methodPayment;
	}
	public void setMethodPayment(String methodPayment) {
		this.methodPayment = methodPayment;
	}
	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}
