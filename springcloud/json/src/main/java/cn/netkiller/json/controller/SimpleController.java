/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.netkiller.json.controller;

import cn.netkiller.json.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author neo
 */
@RestController
public class SimpleController {

	@Value("${spring.application.name}")
	String appName;

	@Autowired
	public ObjectMapper objectMapper;

	@GetMapping("/")
	public String home() throws JsonMappingException, JsonProcessingException {
		String json = "{\"name\":\"netkiller\"}";
		Member member = objectMapper.readValue(json, Member.class);
		System.out.println(member.getName());
		return member.getName();
	}

	@GetMapping("/test")
	public String test() {
		return "aaa";
	}
}
