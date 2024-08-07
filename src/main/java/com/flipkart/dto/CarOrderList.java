package com.flipkart.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CarOrderList {
	
	private Random r = new Random();
    private static final String[] vendors = {"Toyota", "Ford", "BMW", "Audi", "Honda"};
    private static final String[] models = {"ModelX", "ModelY", "ModelZ", "ModelA", "ModelB"};
    private static final String[] colors = {"Red", "Blue", "Green", "Black", "White"};
    
    public List<CarOrderDto> getCarOrders() {
		List<CarOrderDto> orders = new ArrayList<>();
		int numberOfOrders = 10;
        for (int i = 0; i < numberOfOrders; i++) {
            CarOrderDto order = new CarOrderDto(
            		r.nextInt(10000),
            		vendors[r.nextInt(vendors.length)],
            		models[r.nextInt(models.length)],
            		colors[r.nextInt(colors.length)],
            		10000 + r.nextDouble(90000),
            		"2024-07-" + (r.nextInt(30) + 1),
            		"Customer" + r.nextInt(100),
            		"customer" + r.nextInt(100) + "@example.com",
            		String.valueOf(600000000 + r.nextInt(400000000))
            );
            orders.add(order);
        }
		return orders;

	}


}
