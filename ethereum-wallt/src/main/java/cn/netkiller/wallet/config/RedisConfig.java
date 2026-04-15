package cn.netkiller.wallet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.netkiller.wallet.redis.JsonRedisTemplate;
import cn.netkiller.wallet.redis.RedisMessageSubscriber;

@Configuration
public class RedisConfig {

	public RedisConfig() {
	}

	@Bean
	public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory connectionFactory) {
		StringRedisTemplate redisTemplate = new StringRedisTemplate();
		redisTemplate.setConnectionFactory(connectionFactory);
		return redisTemplate;
	}

	@Bean
	public MessageListenerAdapter messageListener() {
		return new MessageListenerAdapter(new RedisMessageSubscriber());
	}

	@Bean
	public ChannelTopic topic() {
		return new ChannelTopic("demo");
	}

	@Bean
	public RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory, MessageListenerAdapter messageListener) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();

		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(messageListener(), topic());
		container.addMessageListener(messageListener(), new ChannelTopic("test"));
		return container;
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

	@Bean
	public JsonRedisTemplate jsonRedisTemplate(RedisConnectionFactory connectionFactory, ObjectMapper objectMapper) {
		return new JsonRedisTemplate(connectionFactory, objectMapper, Object.class);
	}

}
