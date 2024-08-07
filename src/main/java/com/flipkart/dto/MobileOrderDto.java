package com.flipkart.dto;

public class MobileOrderDto {
	public int id;
	public String vendor;
	public String model;
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
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
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
	public MobileOrderDto(int id, String vendor, String model, double price, String orderedDate, String name,
			String email, String mobile) {
		super();
		this.id = id;
		this.vendor = vendor;
		this.model = model;
		this.price = price;
		this.orderedDate = orderedDate;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
	}
	
	public MobileOrderDto() {}
	
	@Override
	public String toString() {
		return "MobileOrderDto [id=" + id + ", vendor=" + vendor + ", model=" + model + ", price="
				+ price + ", orderedDate=" + orderedDate + ", name=" + name + ", email=" + email + ", mobile=" + mobile
				+ "]";
	}
}
