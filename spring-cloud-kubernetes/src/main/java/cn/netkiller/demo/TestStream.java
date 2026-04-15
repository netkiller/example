package cn.netkiller.demo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TestStream {

	public TestStream() {
	}

	public static void main(String[] args) {

		List<Student> list = Arrays.asList(new Student("Neo", 80), new Student("Tom", 60), new Student("Jerry", 70));
		// 平均分 总分
		String result = list.stream().collect(Collectors.teeing(Collectors.averagingInt(Student::getScore), Collectors.summingInt(Student::getScore), (s1, s2) -> s1 + ":" + s2));
		// 最低分 最高分
		String result2 = list.stream().collect(Collectors.teeing(Collectors.minBy(Comparator.comparing(Student::getScore)), Collectors.maxBy(Comparator.comparing(Student::getScore)), (s1, s2) -> s1.orElseThrow() + ":" + s2.orElseThrow()));
		System.out.println(result);
		System.out.println(result2);
	}

}
