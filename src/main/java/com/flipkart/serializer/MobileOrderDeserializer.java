package com.flipkart.serializer;

import java.nio.ByteBuffer;

import org.apache.kafka.common.serialization.Deserializer;

import com.flipkart.dto.MobileOrderDto;

public class MobileOrderDeserializer implements Deserializer<MobileOrderDto> {
	
	@Override
	public MobileOrderDto deserialize(String topic, byte[] data) {
		ByteBuffer byteBuffer = ByteBuffer.wrap(data); 

		int id = byteBuffer.getInt();

		int sizeOfVendor = byteBuffer.getInt();
		byte vendorArray[] = new byte[sizeOfVendor];
		byteBuffer.get(vendorArray);
		String vendor = new String(vendorArray);
		
		int sizeOfModel = byteBuffer.getInt();
		byte modelArray[] = new byte[sizeOfModel];
		byteBuffer.get(modelArray);
		String model = new String(modelArray);
		
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


		return new MobileOrderDto(id, vendor, model, price, date, name, email, mobile);
	}

}
