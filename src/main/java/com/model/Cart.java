package com.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.DTOs.BusinessDtos.CartDTO;
import com.DTOs.BusinessDtos.LineItemDTO;
import com.data.DAOs.CartDAO;

@Entity
public class Cart implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Transient
	private float total;
	// Relation
	@OneToOne
	private User user;
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<LineItem> items = new ArrayList<LineItem>();

	public Cart() {

	}

	public Cart(int id, User user, List<LineItem> items) {
		this.id = id;
		this.user = user;
		this.items = items;
	}

	// BUSINESS LOGIC ----------------------------------------------------

	public void UpdateTotalPrice() {
		total = 0;
		for (LineItem lineItem : items) {
			Product product = lineItem.getProduct();
			if (product != null) {
				total += lineItem.getProduct().getPrice() * lineItem.getQuantity();
			}
		}
	}

	public CartDTO getUserCart(int userId) {
		CartDTO cartDTO = new CartDTO();
		Cart cart = new CartDAO().getUserCart(userId);

		// Map to CartDTO
		cartDTO.setId(cart.id);
		float total = 0;
		for (LineItem item : cart.getItems()) {
			LineItemDTO itemDTO = new LineItemDTO();
			itemDTO.setId(item.getId());
			itemDTO.setQuantity(item.getQuantity());
			itemDTO.setProductID(item.getProduct().getId());
			itemDTO.setProductName(item.getProduct().getProductName());
			itemDTO.setPrice(item.getProduct().getPrice());
			itemDTO.setAuthorName(item.getProduct().getNameAuthor());
			itemDTO.setPictureUrl(item.getProduct().getPictureUrl());
			itemDTO.setDiscount(item.getProduct().getDiscount());

			cartDTO.getItems().add(itemDTO);
			total += ((itemDTO.getPriceDiscount()) * itemDTO.getQuantity());
		}
		cartDTO.setTotal(total);

		return cartDTO;
	}

	public LineItem addItem(int cartId, int productId, int quantity) {
		if (quantity <= 0) {
			quantity = 1;
		}
		CartDAO cartDAO = new CartDAO();
		LineItem item = cartDAO.existItem(cartId, productId);
		if (item != null) {
			cartDAO.updateQuantity(item.getId(), item.getQuantity() + quantity);
			return item;
		}
		LineItem newItem = cartDAO.addToCart(cartId, productId, quantity);
		return newItem;
	}

	public LineItem updateQuantity(int itemId, int quantity) {
		if (quantity <= 0) {
			quantity = 1;
		}
		CartDAO cartDao = new CartDAO();
		LineItem item = cartDao.updateQuantity(itemId, quantity);
		return item;
	}

	public void removeItem(int itemId) {
		CartDAO cartDao = new CartDAO();
		cartDao.RemoveItem(itemId);
	}

	public Cart clearCart(int cartId) {
		CartDAO cartDao = new CartDAO();
		Cart cart = cartDao.clearCart(cartId);
		return cart;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<LineItem> getItems() {
		return items;
	}

	public void setItems(List<LineItem> items) {
		this.items = items;
	}

	public float getTotal() {
		return total;
	}
}