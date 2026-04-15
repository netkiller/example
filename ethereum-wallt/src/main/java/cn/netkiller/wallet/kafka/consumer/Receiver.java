//package cn.netkiller.wallet.kafka.consumer;
//
//import java.util.concurrent.CountDownLatch;
//
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Receiver {
//
//	public Receiver() {
//		// TODO Auto-generated constructor stub
//	}
//
//	private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);
//
//	private CountDownLatch latch = new CountDownLatch(1);
//
//	public CountDownLatch getLatch() {
//		return latch;
//	}
//
//	@KafkaListener(topics = "test.1")
//	public void receive(ConsumerRecord<?, ?> consumerRecord) {
//		LOGGER.info("received payload='{}'", consumerRecord.toString());
//		latch.countDown();
//	}
//}
