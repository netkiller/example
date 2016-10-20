package cn.netkiller.reflect;

public class ClassA {
	public String name;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public ClassA() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ClassA [name=" + name + ", age=" + age + "]";
	}
	

}
