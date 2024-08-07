package com.flipkart.serializer;

import java.nio.ByteBuffer;

import org.apache.kafka.common.serialization.Serializer;

import com.flipkart.dto.BookOrderDto;

public class BookOrderSerializer implements Serializer<BookOrderDto> {

	@Override
	public byte[] serialize(String topic, BookOrderDto bookOrderDto) {
		if(bookOrderDto==null) {
			return null;
		}
		try {
			byte userNameArray[] = bookOrderDto.getName().getBytes("UTF8"); 
			int sizeOfNameArray = userNameArray.length;
			byte authorNameArray[] = bookOrderDto.getAuthor().getBytes("UTF8");
			int sizeOfAuthorArray = authorNameArray.length;
			byte emailArray[] = bookOrderDto.getEmail().getBytes("UTF8");
			int sizeOfEmailArray = emailArray.length;
			byte mobileArray[] = bookOrderDto.getMobile().getBytes("UTF8");
			int sizeOfMobileArray = mobileArray.length;
			byte dateArray[] = bookOrderDto.getOrderedDate().getBytes("UTF8");
			int sizeOfDateArray = dateArray.length;
			
			ByteBuffer byteBuffer = ByteBuffer.allocate(4 + 4 + sizeOfAuthorArray + 8 + 4 + sizeOfDateArray + 4 + sizeOfNameArray + 4
					+ sizeOfEmailArray + 4 + sizeOfMobileArray); 
			                                                                         
			byteBuffer.putInt(bookOrderDto.getId()); 
			byteBuffer.putInt(sizeOfAuthorArray); 
			byteBuffer.put(authorNameArray);
			byteBuffer.putDouble(bookOrderDto.getPrice());
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
