package com.flipkart.serializer;

import java.nio.ByteBuffer;

import org.apache.kafka.common.serialization.Deserializer;

import com.flipkart.dto.CarOrderDto;

public class CarOrderDeserializer implements Deserializer<CarOrderDto> {
	
	@Override
	public CarOrderDto deserialize(String topic, byte[] data) {
		ByteBuffer byteBuffer = ByteBuffer.wrap(data); // this will convert array of bytes into ByteBuffer

		// code to convert byteBuffer into userDto

		int id = byteBuffer.getInt();

		int sizeOfVendor = byteBuffer.getInt();
		byte vendorArray[] = new byte[sizeOfVendor];
		byteBuffer.get(vendorArray);
		String vendor = new String(vendorArray);
		
		int sizeOfModel = byteBuffer.getInt();
		byte modelArray[] = new byte[sizeOfModel];
		byteBuffer.get(modelArray);
		String model = new String(modelArray);
		
		int sizeOfColor = byteBuffer.getInt();
		byte colorArray[] = new byte[sizeOfColor];
		byteBuffer.get(colorArray);
		String color = new String(colorArray);
		
		Double price = byteBuffer.getDouble();
		
		int sizeOfDateArray = byteBuffer.getInt();
		byte dateArray[] = new byte[sizeOfDateArray];
		byteBuffer.get(dateArray);
		String date = new String(dateArray);
		
		int sizeOfNameArray = byteBuffer.getInt();
		byte nameArray[] = new byte[sizeOfNameArray];
		byteBuffer.get(nameArray);
		String name = new String(nameArray);
		
		int sizeOfEmailArray = byteBuffer.getInt();
		byte emailArray[] = new byte[sizeOfEmailArray];
		byteBuffer.get(emailArray);
		String email = new String(emailArray);
		
		int sizeOfMobileArray = byteBuffer.getInt();
		byte mobileArray[] = new byte[sizeOfMobileArray];
		byteBuffer.get(mobileArray);
		String mobile = new String(mobileArray);


		return new CarOrderDto(id, vendor, model, color, price, date, name, email, mobile);
	}

}
