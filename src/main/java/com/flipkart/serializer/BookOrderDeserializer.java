package com.flipkart.serializer;

import java.nio.ByteBuffer;

import org.apache.kafka.common.serialization.Deserializer;

import com.flipkart.dto.BookOrderDto;

public class BookOrderDeserializer implements Deserializer<BookOrderDto> {

	@Override
	public BookOrderDto deserialize(String topic, byte[] data) {
		ByteBuffer byteBuffer = ByteBuffer.wrap(data); 
		int id = byteBuffer.getInt();

		int sizeOfAuthorName = byteBuffer.getInt();
		byte authorArray[] = new byte[sizeOfAuthorName];
		byteBuffer.get(authorArray);
		String author = new String(authorArray);
		
		double price = byteBuffer.getDouble();
		
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


		return new BookOrderDto(id, author, price, date, name, email, mobile);
	}

}
