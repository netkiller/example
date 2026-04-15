package cn.netkiller.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.netkiller.component.TestComponent;

@RestController
public class TestController {
	@Autowired
	public TestComponent testComponent;

	public TestController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/test")
	public String test() {
		ClassPathResource resource = new ClassPathResource("test.ttf");
		File file = null;
		try {
			file = resource.getFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// InputStream inStream = new FileInputStream(file.getPath());
		// BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
		return file.getPath();
	}

	@GetMapping("/test1")
	public String test1() {
		URL url = Thread.currentThread().getContextClassLoader().getResource("test.ttf");
		return url.getPath();
	}

	@GetMapping("/test3")
	public String test3() {
		return testComponent.test();
	}

}
