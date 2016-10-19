package cn.netkiller.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.TreeSet;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Helloworld".substring(0, "Helloworld".length()-1));
		// A A B E F G C D
		String[] array = { "A", "A", "B", "E", "F", "G", "C", "D" };
		Collection<String> list = new ArrayList<String>(Arrays.asList(array));
		for (String str : list) {
			System.out.print(str + " ");
		}
		System.out.println();

		// A A B E F G C D
		Collection<String> linkedList = new LinkedList<String>(Arrays.asList(array));
		for (String str : linkedList) {
			System.out.print(str + " ");
		}
		System.out.println();

		// 无重复，无序 D E F G A B C
		Collection<String> hashSet = new HashSet<String>(Arrays.asList(array));
		for (String str : hashSet) {
			System.out.print(str + " ");
		}
		System.out.println();

		// 无重复 A B C D E F G
		Collection<String> treeSet = new TreeSet<String>(Arrays.asList(array));
		for (String str : treeSet) {
			System.out.print(str + " ");
		}
		System.out.println();

		// 无重复 A B E F G C D
		Collection<String> linkedHashSet = new LinkedHashSet<String>(Arrays.asList(array));
		for (String str : linkedHashSet) {
			System.out.print(str + " ");

		}

	}

}
