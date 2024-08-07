package com.flipkart.streams;

import org.apache.kafka.common.serialization.Serdes;

import com.flipkart.dto.CarOrderDto;
import com.flipkart.serializer.CarOrderDeserializer;
import com.flipkart.serializer.CarOrderSerializer;


public class CarOrderSerde extends Serdes.WrapperSerde<CarOrderDto> {

	public CarOrderSerde() {
		super(new CarOrderSerializer(), new CarOrderDeserializer());
	}

}
