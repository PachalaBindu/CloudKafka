package com.flipkart.streams;

import java.util.Properties;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.state.KeyValueStore;

import com.flipkart.dto.BookOrderDto;
import com.flipkart.dto.CarOrderDto;
import com.flipkart.dto.MobileOrderDto;
import com.flipkart.serializer.BookOrderDeserializer;
import com.flipkart.serializer.CarOrderDeserializer;
import com.flipkart.serializer.MobileOrderDeserializer;

public class OrdersCountStream {
	public static void main(String[] args) throws Exception {
		StreamsBuilder streamsBuilder = new StreamsBuilder();
		final Serde<String> stringSerde = Serdes.String();

		KStream<String, String> ordersStream = streamsBuilder.stream("FlipkartOrdersTopic",
				Consumed.with(stringSerde, stringSerde));
		
		KStream<String, BookOrderDto> bookOrders = ordersStream
				.filter((key, value)->key.equals("flipkart.order.book"))
				.mapValues(value->{
					try {
				        Deserializer<BookOrderDto> deserializer = new BookOrderDeserializer(); 
			            return deserializer.deserialize("FlipkartOrdersTopic", value.getBytes());
			        } catch (Exception e) {
			            return null; 
			        }
				});
				
		KStream<String, CarOrderDto> carOrders = ordersStream
				.filter((key, value)->key.equals("flipkart.order.car"))
				.mapValues(value->{
					Deserializer<CarOrderDto> deserializer = new CarOrderDeserializer(); 
					return deserializer.deserialize("FlipkartOrdersTopic", value.getBytes());
				});
		
		KStream<String, MobileOrderDto> mobileOrders = ordersStream
				.filter((key, value)->key.equals("flipkart.order.mobile"))
				.mapValues(value->{
					Deserializer<MobileOrderDto> deserializer = new MobileOrderDeserializer(); 
					return deserializer.deserialize("FlipkartOrdersTopic", value.getBytes());
				});
		
		KTable<String, Long> bookOrdersCount = bookOrders.mapValues(order -> 1L).groupByKey().count();
		
		KTable<String, Double> bookOrdersTotalPrice = bookOrders.groupByKey().aggregate(
				() -> 0.0, 
				(key, order, aggregate) -> aggregate + order.getPrice(),
				Materialized.<String, Double, KeyValueStore<Bytes, byte[]>>as("book-store").withValueSerde(Serdes.Double()));

		KTable<String, Long> carOrdersCount = carOrders.mapValues(order -> 1L).groupByKey().count();

		KTable<String, Double> carOrdersTotalPrice = carOrders.groupByKey().aggregate(
				() -> 0.0, 
				(key, order, aggregate) -> aggregate + order.getPrice(),
				Materialized.<String, Double, KeyValueStore<Bytes, byte[]>>as("car-store").withValueSerde(Serdes.Double()));
		
		KTable<String, Long> mobileOrdersCount = mobileOrders.mapValues(order -> 1L).groupByKey().count();

		KTable<String, Double> mobileOrdersTotalPrice = mobileOrders.groupByKey().aggregate(
				() -> 0.0, 
				(key, order, aggregate) -> aggregate + order.getPrice(),
				Materialized.<String, Double, KeyValueStore<Bytes, byte[]>>as("mobile-store").withValueSerde(Serdes.Double()));

        bookOrdersCount.join(
               bookOrdersTotalPrice,
               (count, totalAmount) -> String.format("\"Books_order_count\": %d, \"Books_total_transaction_amount\": %.2f", count, totalAmount)
            ).toStream().foreach((key,value)-> System.out.println(key + " " + value));
        
        carOrdersCount.join(
                carOrdersTotalPrice,
                (count, totalAmount) -> String.format("\"Cars_order_count\": %d, \"Cars_total_transaction_amount\": %.2f", count, totalAmount)
             ).toStream().foreach((key,value)-> System.out.println(key + " " + value));
        
        mobileOrdersCount.join(
                mobileOrdersTotalPrice,
                (count, totalAmount) -> String.format("\"Mobiles_order_count\": %d, \"Mobiles_total_transaction_amount\": %.2f", count, totalAmount)
             ).toStream().foreach((key,value)-> System.out.println(key + " " + value));

		Topology topology = streamsBuilder.build();
		KafkaStreams kafkaStreams = new KafkaStreams(topology, getProperties());
		System.out.println("kafka stream started..");
		kafkaStreams.start();
	}

	public static Properties getProperties() throws Exception {
		Properties props = new Properties();
		props.put("application.id", "order-stream-app");
		props.put("bootstrap.servers", "localhost:9092, localhost:9093");
		return props;
	}
}
