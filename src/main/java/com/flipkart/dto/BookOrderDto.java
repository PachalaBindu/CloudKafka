package com.flipkart.dto;

public class BookOrderDto {
	
	public int id;
	public String author;
	public double price;
	public String orderedDate;
	public String name;
	public String email;
	public String mobile;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getOrderedDate() {
		return orderedDate;
	}
	public void setOrderedDate(String orderedDate) {
		this.orderedDate = orderedDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public BookOrderDto(int id, String author, double price, String orderedDate, String name, String email,
			String mobile) {
		super();
		this.id = id;
		this.author = author;
		this.price = price;
		this.orderedDate = orderedDate;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
	}
	@Override
	public String toString() {
		return "BookOrderDto [id=" + id + ", author=" + author + ", price=" + price + ", orderedDate=" + orderedDate
				+ ", name=" + name + ", email=" + email + ", mobile=" + mobile + "]";
	}
	
	

}
