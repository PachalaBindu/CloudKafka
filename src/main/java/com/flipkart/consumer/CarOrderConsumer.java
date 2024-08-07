package com.flipkart.consumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import com.flipkart.dto.CarOrderDto;

public class CarOrderConsumer {
	
	private static void simpleConsumer() {
		Properties properties = new Properties();
		properties.put("bootstrap.servers", "localhost:9092");
		properties.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer","com.flipkart.serializer.CarOrderDeserializer");
		properties.put("group.id", "orders");
		properties.put("partitioner.class", "com.flipkart.partition.OrderPartitioner");
		TopicPartition order_partition = new TopicPartition("FlipkartOrdersTopic", 0);
		Consumer<String, CarOrderDto> order_consumer = new KafkaConsumer<>(properties);
		order_consumer.assign(Arrays.asList(order_partition ));
		
		Duration duration = Duration.ofMillis(100);
		while(true) {
			ConsumerRecords<String, CarOrderDto> book_records = order_consumer.poll(duration);
			for(ConsumerRecord<String, CarOrderDto> record: book_records) {
				System.out.println("Message from broker: "+record.value());
			}
		}
	}

	public static void main(String[] args) {
		simpleConsumer();

	}

}
