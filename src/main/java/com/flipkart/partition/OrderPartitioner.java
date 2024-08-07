package com.flipkart.partition;

import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

public class OrderPartitioner implements Partitioner {

	@Override
	public void configure(Map<String, ?> configs) {
		System.out.println("Inside OrderPartitioner.configure()" + configs);
	}

	@Override
	public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
		List<PartitionInfo> partitionInfoList = cluster.availablePartitionsForTopic(topic);
		int noOfPartitions = partitionInfoList.size();
		int partitionId = 0;
		if(key==null || key.equals("")) {
			partitionId=0;
		}
		else if(key.equals("flipkart.order.car")) {
			if(noOfPartitions>1) {
				partitionId=0;
			}
			else {
				partitionId=0;
			}
		}
		else if(key.equals("flipkart.order.book")) {
			if(noOfPartitions>1) {
				partitionId=1;
			}
			else {
				partitionId=0;
			}
		}
		else if(key.equals("flipkart.order.mobile")) {
			if(noOfPartitions>1) {
				partitionId=2;
			}
			else {
				partitionId=0;
			}
		}
		System.out.println("Order Partitioner - key: " + key + " partitionId: " + partitionId);
		return partitionId;
	}

	@Override
	public void close() {
		System.out.println("Inside OrderPartitioner.close()");
	}

}
