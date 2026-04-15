package cn.netkiller.wallet.redis;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonRedisTemplate extends RedisTemplate<String, Object> {

	public JsonRedisTemplate(RedisConnectionFactory connectionFactory, ObjectMapper objectMapper, Class<?> valueType) {
		RedisSerializer<String> stringSerializer = new StringRedisSerializer();
		super.setKeySerializer(stringSerializer);
		super.setHashKeySerializer(stringSerializer);
		super.setHashValueSerializer(stringSerializer);
		Jackson2JsonRedisSerializer<?> jsonRedisSerializer = new Jackson2JsonRedisSerializer<>(valueType);
		jsonRedisSerializer.setObjectMapper(objectMapper);
		super.setValueSerializer(jsonRedisSerializer);
		super.setConnectionFactory(connectionFactory);
		super.afterPropertiesSet();
	}
}
