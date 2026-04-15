//package cn.netkiller.oauth2.jwt.authorization;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//@Configuration
//@EnableCaching
//public class CachingConfigurer extends CachingConfigurerSupport {
//	@Value("${redis.cache.expiration:3600}")
//	private Long expiration;
//
////	@Bean
////	public CacheManager cacheManager(RedisTemplate redisTemplate) {
////		RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
////		redisCacheManager.setDefaultExpiration(expiration);
////		return redisCacheManager;
////	}
//
//	@Bean
//	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
//		RedisTemplate<String, Object> template = new RedisTemplate<>();
//		template.setConnectionFactory(factory);
//		template.setKeySerializer(new StringRedisSerializer());
//		template.setValueSerializer(new JdkSerializationRedisSerializer());
//		return template;
//	}
//}