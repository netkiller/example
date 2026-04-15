package cn.netkiller.schedule.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import cn.netkiller.schedule.service.Kafka;

@Service
public class KafkaImpl implements Kafka {

	private static final Logger logger = LoggerFactory.getLogger(KafkaImpl.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	private static boolean status;

	public boolean send(String topic, String message) {

		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);

		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

			@Override
			public void onSuccess(SendResult<String, String> result) {
				logger.debug("sent message='{}' with offset={}", message, result.getRecordMetadata().offset());
				status = true;
			}

			@Override
			public void onFailure(Throwable ex) {
				logger.error("unable to send message='{}'", message, ex);
				status = false;
			}
		});
		return status;
	}
}
