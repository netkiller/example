package cn.netkiller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.netkiller.domain.User;
import cn.netkiller.service.UserService;

@RestController
public class TestMongoController {

	@Autowired
	private UserService userService;

	public TestMongoController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/mongo/save")
	public String save() {
		User user = new User();
		user.setUseruame("netkiller");
		user.setPassword("123456");
		userService.save(user);
		return "Success";
	}

	@Autowired
	@Qualifier(value = "primaryMongoTemplate")
	private MongoTemplate primaryMongoTemplate;

	@Autowired
	@Qualifier(value = "secondaryMongoTemplate")
	private MongoTemplate secondaryMongoTemplate;

	@GetMapping("/mongo/primary/save")
	public String primarysave() {
		User user = new User();
		user.setUseruame("netkiller");
		user.setPassword("123456");
		primaryMongoTemplate.save(user);
		return "Success\r\n";
	}

	@GetMapping("/mongo/secondary/save")
	public String secondaryMongoTemplate() {
		User user = new User();
		user.setUseruame("netkiller");
		user.setPassword("123456");
		secondaryMongoTemplate.save(user);
		return "Success\r\n";
	}
}
