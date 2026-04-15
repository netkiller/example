package cn.netkiller.wallet.restful;

import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;

import cn.netkiller.wallet.pojo.RestfulResponse;
import cn.netkiller.wallet.redis.JsonRedisTemplate;
import cn.netkiller.wallet.redis.RedisMessagePublisher;

@RestController
public class TestRestController {
	private static final Logger logger = LoggerFactory.getLogger(TestRestController.class);

	@Autowired
	private Web3j web3j;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private JsonRedisTemplate jsonRedisTemplate;

	public TestRestController() {

	}

	@GetMapping("/version")
	public String version() throws IOException {
		Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
		String clientVersion = web3ClientVersion.getWeb3ClientVersion();
		logger.info(clientVersion);
		return clientVersion;
	}

	@GetMapping("/pub/demo")
	public String pub() {

		RedisMessagePublisher publisher = new RedisMessagePublisher(stringRedisTemplate, new ChannelTopic("demo"));
		String message = "Message " + UUID.randomUUID();
		publisher.publish(message);
		return message;
	}

	@GetMapping("/pub/test")
	public String pub(@RequestParam String message) {
		RedisMessagePublisher publisher = new RedisMessagePublisher(stringRedisTemplate, new ChannelTopic("test"));
		publisher.publish(message);
		return message;
	}

//	@GetMapping("/pub/json")
//	public RestfulResponse pubJson() {
//		RestfulResponse restfulResponse = new RestfulResponse(true, 0, null, null);
//		jsonRedisTemplate.opsForValue().set("test", restfulResponse);
//		jsonRedisTemplate.convertAndSend("test", restfulResponse);
//		return restfulResponse;
//	}
//
//	@GetMapping("/pub/json")
//	public RestfulResponse pubJson() {
//		RestfulResponse restfulResponse = new RestfulResponse(true, 0, null, null);
//		jsonRedisTemplate.opsForValue().set("test", restfulResponse);
//		jsonRedisTemplate.convertAndSend("test", restfulResponse);
//		return restfulResponse;
//	}



}
