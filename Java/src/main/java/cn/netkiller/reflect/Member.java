package cn.netkiller.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Member {
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

	@Override
	public String toString() {
		return "ClassA [name=" + name + ", age=" + age + "]";
	}

	public Member() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		Class<?> cls = Class.forName("cn.netkiller.reflect.Member");
		Object member = cls.newInstance();
		Method setMethod = cls.getDeclaredMethod("setAge", int.class);
		setMethod.invoke(member, 15);

		Method getMethod = cls.getDeclaredMethod("getAge");
		System.out.println(getMethod.invoke(member));

	}

}
