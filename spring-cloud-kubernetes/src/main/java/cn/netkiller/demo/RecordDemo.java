package cn.netkiller.demo;

record User(String name, Integer age) {

}

public class RecordDemo {

	public RecordDemo() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		User user = new User("Neo", 35);
		System.out.println(user.toString());
	}

}
