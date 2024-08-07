package com.flipkart.serializer;

import java.nio.ByteBuffer;

import org.apache.kafka.common.serialization.Serializer;

import com.flipkart.dto.CarOrderDto;

public class CarOrderSerializer implements Serializer<CarOrderDto> {

	@Override
	public byte[] serialize(String topic, CarOrderDto carOrderDto) {
		if(carOrderDto==null) {
			return null;
		}
		try {
			byte userNameArray[] = carOrderDto.getName().getBytes("UTF8"); 
			int sizeOfNameArray = userNameArray.length;
			byte vendorArray[] = carOrderDto.getVendor().getBytes("UTF8");
			int sizeOfVendorArray = vendorArray.length;
			byte modelArray[] = carOrderDto.getModel().getBytes("UTF8");
			int sizeOfModelArray = modelArray.length;
			byte colorArray[] = carOrderDto.getColor().getBytes("UTF8");
			int sizeOfColorArray = colorArray.length;
			byte emailArray[] = carOrderDto.getEmail().getBytes("UTF8");
			int sizeOfEmailArray = emailArray.length;
			byte mobileArray[] = carOrderDto.getMobile().getBytes("UTF8");
			int sizeOfMobileArray = mobileArray.length;
			byte dateArray[] = carOrderDto.getOrderedDate().getBytes("UTF8");
			int sizeOfDateArray = dateArray.length;
			
			ByteBuffer byteBuffer = ByteBuffer.allocate(4 + 4 + sizeOfVendorArray + 4 + sizeOfModelArray + 4 + sizeOfColorArray + 8 + 4 + sizeOfDateArray + 4 + sizeOfNameArray + 4
					+ sizeOfEmailArray + 4 + sizeOfMobileArray); 
			                                                                         
			byteBuffer.putInt(carOrderDto.getId()); 
			byteBuffer.putInt(sizeOfVendorArray); 
			byteBuffer.put(vendorArray);
			byteBuffer.putInt(sizeOfModelArray); 
			byteBuffer.put(modelArray);
			byteBuffer.putInt(sizeOfColorArray); 
			byteBuffer.put(colorArray);
			byteBuffer.putDouble(carOrderDto.getPrice());
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
