package com.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.data.DAOs.ProductDAO;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

@SuppressWarnings("serial")
@Entity
@Table(name = "product")
public class Product implements Serializable {

	@ManyToMany
	@JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "productId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "categoryId", referencedColumnName = "id"))
	private List<Category> categorys;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int id;
	public String codeProduct;
	public String nameAuthor;
	@Type(type = "text")
	public String description;
	public String name;
	public String NXB;
	public String supplier;
	public int price;
	public String pictureUrl;
	public String Sku;
	public String typeBook;	
	public int discount;
	@LazyCollection(LazyCollectionOption.FALSE)
	// Relation
	@OneToMany(mappedBy = "product")
	private List<Review> reviews = new ArrayList<Review>();
	@OneToMany(mappedBy = "product")
	private List<Photo> photos = new ArrayList<Photo>();
	@OneToOne(mappedBy = "product")
	private File file;

	public Product(String codeProduct, String name, String nameAuthor, String des, String nxb, String supplier,
			String pictureUrl, String sku, String typeBook, int price, int discount) {
		this.codeProduct = codeProduct;
		this.nameAuthor = nameAuthor;
		this.name = name;
		this.description = des;
		this.NXB = nxb;
		this.supplier = supplier;
		this.pictureUrl = pictureUrl;
		this.Sku = sku;
		this.typeBook = typeBook;
		this.price = price;
		this.discount = discount;
	}

	public Product(String codeProduct, String productName, String nameAuthor, String description, int price,
			String typeBook) {
		super();
		this.codeProduct = codeProduct;
		this.name = productName;
		this.nameAuthor = nameAuthor;
		this.description = description;
		this.price = price;
		this.typeBook = typeBook;
	}

	public Product(String nameAuthor, String description, String name, String nXB, String supplier, int price) {
		super();
		this.nameAuthor = nameAuthor;
		this.description = description;
		this.name = name;
		NXB = nXB;
		this.supplier = supplier;
		this.price = price;
	}

	public Product(int id, String nameAuthor, String description, String name, String nXB, String supplier, int price) {
		super();
		this.id = id;
		this.nameAuthor = nameAuthor;
		this.description = description;
		this.name = name;
		NXB = nXB;
		this.supplier = supplier;
		this.price = price;
	}

	public Product() {

	}

	public Product(String nameAuthor, String description, int price, String type) {
		super();
		this.nameAuthor = nameAuthor;
		this.description = description;
		this.price = price;
		this.typeBook = type;

	}

	private float calAverageStar() {
		int sum = 0;

		for (Review review : reviews) {
			sum += review.getStars();
		}

		int averageStar = sum == 0 ? sum : sum / reviews.size();

		return averageStar;
	}
	
    @SuppressWarnings("unused")
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

	static public Product find(int id) {
		ProductDAO productDAO = new ProductDAO();
		Product product = productDAO.getProduct(id);

		return product;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getPriceDiscount() {
		return price - (discount * price) / 100;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Category> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<Category> categorys) {
		this.categorys = categorys;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodeProduct() {
		return codeProduct;
	}

	public void setCodeProduct(String codeProduct) {
		this.codeProduct = codeProduct;
	}

	public String getNameAuthor() {
		return nameAuthor;
	}

	public void setNameAuthor(String nameAuthor) {
		this.nameAuthor = nameAuthor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getSku() {
		return Sku;
	}

	public void setSku(String sku) {
		Sku = sku;
	}

	public String getTypeBook() {
		return typeBook;
	}

	public void setTypeBook(String typeBook) {
		this.typeBook = typeBook;
	}

	public void setProductName(String productName) {
		this.name = productName;
	}

	public String getProductName() {
		return this.name;
	}

	public String getNXB() {
		return this.NXB;
	}

	public void setNXB(String nxb) {
		this.NXB = nxb;
	}

	public String getSupplier() {
		return this.supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public int sumStar(int numStar) {
		int sum = 0;

		for (Review review : reviews) {
			sum += review.getStars() == numStar ? 1 : 0;
		}

		return sum;
	}

	public double getNStar(int numStar) {
		int sumStar = sumStar(numStar);

		return sumStar == 0 ? sumStar
				: Math.round((sumStar * 100 / reviews.size()));
	}

	public int getStar() {
		return Math.round(calAverageStar());
	}

	public float getAverageStar() {
		return calAverageStar();
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}