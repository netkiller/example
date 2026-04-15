package cn.netkiller.demo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestList {

	public TestList() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

		List<String> list = Arrays.asList("1", "2", "3");
		// jdk11 之前这样写
		List<Integer> list1 = list.stream().map(Integer::parseInt).collect(Collectors.toList());
		System.out.println(list1);
		// jdk16 现在可以这样写
		List<Integer> list2 = list.stream().map(Integer::parseInt).toList();
		System.out.println(list2);

	}
}
