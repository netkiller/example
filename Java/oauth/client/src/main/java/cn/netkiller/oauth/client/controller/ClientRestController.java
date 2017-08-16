package cn.netkiller.oauth.client.controller;

import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

@RestController
public class ClientRestController {
	@Autowired
	private OAuth2RestOperations restTemplate;

	@Value("${config.oauth2.resourceURI}")
	private String resourceURI;

	@RequestMapping("/string")
	public String string() {
		return restTemplate.getForObject(resourceURI+"/test/hello.json", String.class);
	}

	@RequestMapping("/json")
	public JsonNode json() {
		return restTemplate.getForObject(resourceURI, JsonNode.class);
	}
	@RequestMapping("/test")
	public String test() {
		return "Test OK";
	}
	@RequestMapping("/token")
	public String token() {
		
//		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
//	    headers.add("Authorization", String.format("%s %s", "bearer", "a6654119-a9de-4c03-90d1-2ffc168c74e5"));
//	    headers.add("Content-Type", "application/json");
//
	    RestTemplate restTemplate = new RestTemplate();
//	    //restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
//
//	    HttpEntity<String> request = new HttpEntity<String>(createOrder, headers);
//
//	    ResponseEntity<String> result = restTemplate.exchange(url_POST, HttpMethod.POST, request, String.class);
//	    System.out.println("#### post response = " + result);
		
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.set("Authorization","Bearer "+"6296057a-ed06-4899-9adc-1993ba7a4946");
	    HttpEntity<String> entity = new HttpEntity<String>(headers);
	    ResponseEntity<String> response = restTemplate.exchange("http://localhost:8000/api/test/hello.json",HttpMethod.GET,entity,String.class);
	    System.out.println(response.getBody());
		return response.getStatusCode().toString() + " " + response.getBody();
	}
	
	
}
