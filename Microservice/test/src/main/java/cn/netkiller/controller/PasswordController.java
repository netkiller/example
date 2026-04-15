package cn.netkiller.controller;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordController {
	@Autowired
	private StringEncryptor encryptor;

//	@Value("${test.password}")
//	private String cleartext;

	public PasswordController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/password/encrypt")
	public String encrypt(@RequestParam("text") String text) {
		return String.format("ENC(%s)\r\n", encryptor.encrypt(text));
	}

	@PostMapping("/password/decrypt")
	public String decrypt(@RequestParam("text") String text) {
		System.out.println(text);
		return encryptor.decrypt(text);
	}

//	@GetMapping("/cleartext")
//	public String getPassword() {
//		return this.cleartext;
//	}
}
