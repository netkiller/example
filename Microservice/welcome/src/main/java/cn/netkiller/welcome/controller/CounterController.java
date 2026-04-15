package cn.netkiller.welcome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.netkiller.welcome.component.RestfulMetrics;

@RestController
public class CounterController {
	@Autowired
	private RestfulMetrics restfulMetrics;

	public CounterController() {
	}

	@GetMapping("/counter1")
	public void counter1() {
		restfulMetrics.job2Counter.increment();
	}

	@GetMapping("/counter2")
	public void counter2() {
		restfulMetrics.job2Counter.increment();
	}

	@GetMapping("/gauge")
	public void gauge(@RequestParam("x") String x) {
		System.out.println("gauge controller x" + x);
		restfulMetrics.map.put("x", Double.valueOf(x));
	}
}
