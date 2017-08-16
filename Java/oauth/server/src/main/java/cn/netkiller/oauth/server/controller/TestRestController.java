package cn.netkiller.oauth.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestRestController {

	@RequestMapping(value = "hello", method = RequestMethod.GET)
	public ResponseEntity<String> hello() {
		return new ResponseEntity<String>("Hello world !", HttpStatus.OK);
	}

	@PreAuthorize("#oauth2.hasScope('write')")
	@RequestMapping(value = "set/{string}", method = RequestMethod.GET)
	public ResponseEntity<String> set(String string) {
		return new ResponseEntity<String>(string, HttpStatus.OK);
	}
}
