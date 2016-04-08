package cn.netkiller.type;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		list.add("Jack");
		list.add("Jet");
		list.add("Jack");
		list.add("Mike");
		list.add("Kitty");
		list.add("Tom");

		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}

		for (Iterator<String> it1 = list.iterator(); it1.hasNext();) {
			System.out.println(it1.next());
		}

		// for循环
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

		// for循环加强版
		for (String i : list) {
			System.out.println(i);
		}

	}

}
