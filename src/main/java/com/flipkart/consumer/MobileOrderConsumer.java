package com.flipkart.consumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import com.flipkart.dto.MobileOrderDto;

public class MobileOrderConsumer {

	public static void simpleConsumer() throws Exception {
		Properties mobile_properties = new Properties();
		mobile_properties.put("bootstrap.servers", "localhost:9092");
		mobile_properties.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
		mobile_properties.put("value.deserializer","com.flipkart.serializer.MobileOrderDeserializer");
		mobile_properties.put("group.id", "orders");
		mobile_properties.put("partitioner.class", "com.flipkart.partition.OrderPartitioner");
		TopicPartition mobile_order_partition = new TopicPartition("FlipkartOrdersTopic", 2);
		Consumer<String, MobileOrderDto> mobile_order_consumer = new KafkaConsumer<>(mobile_properties);
		mobile_order_consumer.assign(Arrays.asList(mobile_order_partition ));
		
		Duration duration = Duration.ofMillis(100);
		while(true) {
			ConsumerRecords<String, MobileOrderDto> mobile_records = mobile_order_consumer.poll(duration);
			for(ConsumerRecord<String, MobileOrderDto> record: mobile_records) {
				System.out.println("Message from broker: "+record.value());
			}
			
		}
	}	

	public static void main(String[] args) throws Exception {
		simpleConsumer();
	}
}
