package com.flipkart.streams;

import org.apache.kafka.common.serialization.Serdes;

import com.flipkart.dto.MobileOrderDto;
import com.flipkart.serializer.MobileOrderDeserializer;
import com.flipkart.serializer.MobileOrderSerializer;


public class MobileOrderSerde extends Serdes.WrapperSerde<MobileOrderDto> {

	public MobileOrderSerde() {
		super(new MobileOrderSerializer(), new MobileOrderDeserializer());
	}

}
