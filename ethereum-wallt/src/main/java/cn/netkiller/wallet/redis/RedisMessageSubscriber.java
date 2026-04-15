package cn.netkiller.wallet.redis;

import java.nio.charset.StandardCharsets;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

public class RedisMessageSubscriber implements MessageListener {
	public void onMessage(final Message message, final byte[] pattern) {
		// byte[] body = message.getBody();
		// byte[] channel = message.getChannel();
		//
		System.out.println("Topic : " + new String(message.getChannel(), StandardCharsets.UTF_8));
		System.out.println("Message : " + message.toString());
	}
}
