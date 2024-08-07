package com.flipkart.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BookOrderList {

	private Random r = new Random();
	private static final String[] authors = {"AuthorA", "AuthorB", "AuthorC", "AuthorD", "AuthorE"};

	public List<BookOrderDto> getBookOrders() {
		List<BookOrderDto> orders = new ArrayList<>();
		int numberOfOrders = 100;
		for (int i = 0; i < numberOfOrders; i++) {
			BookOrderDto order = new BookOrderDto(r.nextInt(100), authors[r.nextInt(authors.length)],
					1000 + r.nextInt(900), "2024-07-" + (r.nextInt(30) + 1),
					"Customer" + r.nextInt(100), "customer" + r.nextInt(100) + "@example.com",
					String.valueOf(600000000 + r.nextInt(400000000)));
			orders.add(order);
		}
		return orders;

	}
}
