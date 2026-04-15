package cn.netkiller.web.restful;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.netkiller.web.domain.User;

@RestController
@RequestMapping("/restful")
public class TestRestController {

	@RequestMapping("/test")
	public String home() {
		return "OK";
	}

	@PostMapping("/validation")
	public String addUser(@RequestBody @Valid User user, BindingResult bindingResult) {
		// 如果有参数校验失败，返回错误信息
		if (bindingResult.hasErrors()) {
			System.out.println(user.toString());
			System.out.println(bindingResult.getErrorCount());
			System.out.println(bindingResult.getAllErrors().toString());
		}

		for (ObjectError error : bindingResult.getAllErrors()) {
			return error.getDefaultMessage();
		}
		return user.toString();
	}

}
