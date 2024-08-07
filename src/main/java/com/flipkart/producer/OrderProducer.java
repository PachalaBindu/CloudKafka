package com.flipkart.producer;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.flipkart.dto.BookOrderDto;
import com.flipkart.dto.BookOrderList;
import com.flipkart.dto.CarOrderDto;
import com.flipkart.dto.CarOrderList;
import com.flipkart.dto.MobileOrderDto;
import com.flipkart.dto.MobileOrderList;

public class OrderProducer {

	private static void simpleProducer() {
		ExecutorService executor = Executors.newFixedThreadPool(3);
		Callback callback = new MyCallback();

		BookOrderList bookOrderList = new BookOrderList();
		Properties book_properties = new Properties();
		book_properties.put("bootstrap.servers", "localhost:9092");
		book_properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		book_properties.put("value.serializer", "com.flipkart.serializer.BookOrderSerializer");
		book_properties.put("partitioner.class", "com.flipkart.partition.OrderPartitioner");
		book_properties.put("acks", "all");
		book_properties.put("retries", 3);
		Producer<String, BookOrderDto> book_producer = new KafkaProducer<>(book_properties);
		List<BookOrderDto> bookOrders = bookOrderList.getBookOrders();
		
		CarOrderList carOrderList = new CarOrderList();
		Properties car_properties = new Properties();
		car_properties.put("bootstrap.servers", "localhost:9092");
		car_properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		car_properties.put("value.serializer", "com.flipkart.serializer.CarOrderSerializer");
		car_properties.put("partitioner.class", "com.flipkart.partition.OrderPartitioner");
		car_properties.put("acks", "all");
		car_properties.put("retries", 3);
		Producer<String, CarOrderDto> car_producer = new KafkaProducer<>(car_properties);
		List<CarOrderDto> carOrders = carOrderList.getCarOrders();
		
		MobileOrderList mobileOrderList = new MobileOrderList();
		Properties mobile_properties = new Properties();
		mobile_properties.put("bootstrap.servers", "localhost:9092");
		mobile_properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		mobile_properties.put("value.serializer", "com.flipkart.serializer.MobileOrderSerializer");
		mobile_properties.put("partitioner.class", "com.flipkart.partition.OrderPartitioner");
		car_properties.put("acks", "all");
		car_properties.put("retries", 3);
		Producer<String, MobileOrderDto> mobile_producer = new KafkaProducer<>(mobile_properties);
		List<MobileOrderDto> mobileOrders = mobileOrderList.getMobileOrders();


		Runnable bookOrderTask = new Runnable() {
			@Override
			public void run() {
				try {
		            while (!Thread.currentThread().isInterrupted()) {
		                for(BookOrderDto order: bookOrders) {
		                	 ProducerRecord<String, BookOrderDto> record = new ProducerRecord<>("FlipkartOrdersTopic", "flipkart.order.book", order);
				             book_producer.send(record, callback);
				             Thread.sleep(1000);
		                }
		            }
		        } catch (InterruptedException e) {
		            Thread.currentThread().interrupt(); // Restore interrupted status
		        } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
		            book_producer.close();
		        }

			}
		};
		
		Runnable carOrderTask = new Runnable() {
			@Override
			public void run() {
				try {
					while (!Thread.currentThread().isInterrupted()) {
						for (CarOrderDto order : carOrders) {
							ProducerRecord<String, CarOrderDto> record = new ProducerRecord<>("FlipkartOrdersTopic",
									"flipkart.order.car", order);
							car_producer.send(record, callback);
							Thread.sleep(1000);
						}
					}
				} catch (InterruptedException e) {
		            Thread.currentThread().interrupt(); // Restore interrupted status
		        } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
		            car_producer.close();
		        }
			}
		};
		
		Runnable mobileOrderTask = new Runnable() {
			@Override
			public void run() {
				try {
					while (!Thread.currentThread().isInterrupted()) {
						for (MobileOrderDto order : mobileOrders) {
							ProducerRecord<String, MobileOrderDto> record = new ProducerRecord<>("FlipkartOrdersTopic",
									"flipkart.order.mobile", order);
							mobile_producer.send(record, callback);
						    Thread.sleep(1000);
						}
					}
				} catch (InterruptedException e) {
		            Thread.currentThread().interrupt(); // Restore interrupted status
		        } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
		            mobile_producer.close();
		        }
			}
		};
		
		executor.submit(bookOrderTask);
		executor.submit(carOrderTask);
		executor.submit(mobileOrderTask);

	}

	static class MyCallback implements Callback {
		@Override
		public void onCompletion(RecordMetadata metadata, Exception exception) {
			if (exception != null) {
				System.out.println("Message failed " + exception.getMessage());
			} else {
				System.out.println("Message Confirmation respone: Pattern=" + metadata.partition() + "\toffset="
						+ metadata.offset());
			}
		}

	}

	public static void main(String[] args) {
		simpleProducer();
	}

}
