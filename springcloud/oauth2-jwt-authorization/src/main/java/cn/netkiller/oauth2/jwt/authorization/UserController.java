package cn.netkiller.oauth2.jwt.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {
	@Autowired
	private RedisConnectionFactory redisConnectionFactory;

	@RequestMapping("/")
	public String index() {
		return "Home";
	}

	@RequestMapping("/test")
	public String test() {
		return "OK";
	}

	@RequestMapping("/user")
	public Object user(Principal user) {
		return user;
	}

	@PostMapping("/removeToken")
	public Boolean removeToken(String accesstoken, String refreshToken) {
		RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
		tokenStore.removeRefreshToken(refreshToken);
		tokenStore.removeAccessToken(accesstoken);
		return Boolean.TRUE;
	}

}
