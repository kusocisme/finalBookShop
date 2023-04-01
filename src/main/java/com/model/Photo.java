package com.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "photo")
public class Photo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String Url;
    private boolean Main;
    private String PublicId;

    @ManyToOne
    private Product product;

    public Photo(int id, String url, boolean main, String publicId, Product product) {
        this.id = id;
        Url = url;
        Main = main;
        PublicId = publicId;
        this.product = product;
    }

    public Photo(int id, String url, boolean main, String publicId) {
        this.id = id;
        Url = url;
        Main = main;
        PublicId = publicId;
    }

    public Photo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public boolean isMain() {
        return Main;
    }

    public void setMain(boolean main) {
        Main = main;
    }

    public String getPublicId() {
        return PublicId;
    }

    public void setPublicId(String publicId) {
        PublicId = publicId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
