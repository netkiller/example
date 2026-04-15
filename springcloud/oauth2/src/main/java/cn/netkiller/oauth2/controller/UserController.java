package cn.netkiller.oauth2.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.boot.actuate.trace.http.HttpTrace.Principal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class UserController {

	public UserController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("oauth/me")
	public Principal getUser(Principal user) {
		System.out.println(".. 进入　获取用户信息　方法   ..........  ");
		System.out.println(user);
		return user;
	}

	@GetMapping("api/user")
	public Principal user(Principal user) {
		System.out.println(".. 进入　获取用户信息　方法   ..........  ");
		System.out.println(user);
		return user;
	}

	@RequestMapping(path = "api/messages", method = RequestMethod.GET)
	public List<String> getMessages(Principal principal) {
		List<String> list = new LinkedList<>();
		list.add("俏如来");
		list.add("帝如来");
		list.add("鬼如来");
		return list;
	}

	@RequestMapping(path = "api/messages", method = RequestMethod.POST)
	public String postMessage(Principal principal) {
		return "POST -> 默苍离 ";
	}

	/**
	 * 当前登录人信息
	 * 
	 * @return
	 */
	@RequestMapping(path = "api/loginUser", method = RequestMethod.GET)
	public UserDetails currentlyLoginUser() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetails;
	}

}
