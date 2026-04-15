package cn.netkiller.wallet.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

public class RedisMessagePublisher {

	private final StringRedisTemplate redisTemplate;

	private final ChannelTopic topic;

	public RedisMessagePublisher(StringRedisTemplate redisTemplate, ChannelTopic topic) {
		this.redisTemplate = redisTemplate;
		this.topic = topic;
	}

	public void publish(String message) {
		redisTemplate.convertAndSend(topic.getTopic(), message);
	}
}
