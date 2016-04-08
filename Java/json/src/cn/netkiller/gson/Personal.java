package cn.netkiller.gson;

import java.util.ArrayList;
import java.util.List;

public class Personal {
	private int age = 30;
	private String name = "neo";
	private List<String> telphone = new ArrayList<String>() {
		{
			add("13113668890");
			add("13322993040");
			add("29812080");
		}
	};

	// getter and setter methods

	@Override
	public String toString() {
		return "Personal [age=" + age + ", name=" + name + ", telphone=" + telphone + "]";
	}
}
