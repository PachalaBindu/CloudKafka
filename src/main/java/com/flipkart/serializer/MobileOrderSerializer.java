package com.flipkart.serializer;

import java.nio.ByteBuffer;

import org.apache.kafka.common.serialization.Serializer;

import com.flipkart.dto.MobileOrderDto;

public class MobileOrderSerializer implements Serializer<MobileOrderDto> {

	@Override
	public byte[] serialize(String topic, MobileOrderDto mobileOrderDto) {
		if(mobileOrderDto==null) {
			return null;
		}
		try {
			byte userNameArray[] = mobileOrderDto.getName().getBytes("UTF8"); 
			int sizeOfNameArray = userNameArray.length;
			byte vendorArray[] = mobileOrderDto.getVendor().getBytes("UTF8");
			int sizeOfVendorArray = vendorArray.length;
			byte modelArray[] = mobileOrderDto.getModel().getBytes("UTF8");
			int sizeOfModelArray = modelArray.length;
			byte emailArray[] = mobileOrderDto.getEmail().getBytes("UTF8");
			int sizeOfEmailArray = emailArray.length;
			byte mobileArray[] = mobileOrderDto.getMobile().getBytes("UTF8");
			int sizeOfMobileArray = mobileArray.length;
			byte dateArray[] = mobileOrderDto.getOrderedDate().getBytes("UTF8");
			int sizeOfDateArray = dateArray.length;
			
			ByteBuffer byteBuffer = ByteBuffer.allocate(4 + 4 + sizeOfVendorArray + 4 + sizeOfModelArray + 8 + 4 + sizeOfDateArray + 4 + sizeOfNameArray + 4
					+ sizeOfEmailArray + 4 + sizeOfMobileArray); 
			                                                                         
			byteBuffer.putInt(mobileOrderDto.getId()); 
			byteBuffer.putInt(sizeOfVendorArray); 
			byteBuffer.put(vendorArray);
			byteBuffer.putInt(sizeOfModelArray); 
			byteBuffer.put(modelArray);
			byteBuffer.putDouble(mobileOrderDto.getPrice());
			byteBuffer.putInt(sizeOfDateArray); 
			byteBuffer.put(dateArray);
			byteBuffer.putInt(sizeOfNameArray); 
			byteBuffer.put(userNameArray);
			byteBuffer.putInt(sizeOfEmailArray); 
			byteBuffer.put(emailArray);
			byteBuffer.putInt(sizeOfMobileArray); 
			byteBuffer.put(mobileArray);
			
			return byteBuffer.array();
		}
		catch(Exception e) {
			return null;
		}
	}

}
