package cn.netkiller.component;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class TestComponent {

	public TestComponent() {
		System.out.println("TestComponent");
	}

	public String test() {
		System.out.println("TestComponent test()");
		return "Helloworld!!!";
	}

	@PostConstruct
	public void construct() {
		System.out.println("TestComponent PostConstruct");
	}

}
