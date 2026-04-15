package api.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

	public TestController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/get")
	public String get(@RequestHeader String lang) throws IOException {
		System.out.println(lang);
		return lang;
	}

	@PostMapping("/post")
	public String post(@RequestHeader String lang) throws IOException {
		System.out.println(lang);
		return lang;
	}

	@GetMapping("/list")
	public Map<String, String> x(HttpServletRequest request) throws IOException {

		return getHeadersInfo(request);
	}

	private Map<String, String> getHeadersInfo(HttpServletRequest request) {

		Map<String, String> map = new HashMap<String, String>();

		Enumeration<?> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}

		return map;
	}

}
