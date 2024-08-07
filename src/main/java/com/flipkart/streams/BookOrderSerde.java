package com.flipkart.streams;

import org.apache.kafka.common.serialization.Serdes;

import com.flipkart.dto.BookOrderDto;
import com.flipkart.serializer.BookOrderDeserializer;
import com.flipkart.serializer.BookOrderSerializer;

public class BookOrderSerde extends Serdes.WrapperSerde<BookOrderDto> {

	public BookOrderSerde() {
		super(new BookOrderSerializer(), new BookOrderDeserializer());
	}

}
