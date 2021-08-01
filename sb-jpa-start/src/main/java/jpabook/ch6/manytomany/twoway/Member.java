package jpabook.ch6.manytomany.twoway;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Member {
	@Id
	@Column(name = "MEMBER_ID")
	private String id;

	private String username;

	@ManyToMany
	@JoinTable(name = "MEMBER_PRODUCT",
		joinColumns = @JoinColumn(name = "MEMBER_ID"),
		inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
	private List<Product> products = new ArrayList<>();

	public void addProduct(Product product) {
		products.add(product);
		product.getMembers().add(this);
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
