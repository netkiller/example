package cn.netkiller.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionTest {

	public ReflectionTest() {
		// TODO Auto-generated constructor stub
	}

	public void testSetMethod() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {

		// ClassA a = new ClassA();

		ClassB b = new ClassB();
		b.setAddress("Shenzhen");

		Class<ClassA> classA = ClassA.class;
		ClassA a = classA.newInstance();
		a.setName("Neo");
		a.setAge(30);

		System.out.println(classA.getDeclaredMethod("getAge").invoke(a));

		Method m = classA.getDeclaredMethod("setAge", int.class);
		m.setAccessible(true); // 因为写成private 所以这里必须设置
		m.invoke(b, 26);

		System.out.println(a.toString());
		System.out.println(b.toString());

		System.out.println(b.getName());
		System.out.println(b.getAge());
	}

	public static void main(String[] args) throws InvocationTargetException {

		ClassA a = new ClassA();
		a.setName("Neo");
		a.setAge(30);

		ClassB b = new ClassB();
		b.setAddress("Shenzhen");

		try {
			
			Class<?> objClass = a.getClass();
			
			System.out.println(objClass.getSimpleName());
			System.out.println(objClass.getName());
			System.out.println(objClass.toString());
			System.out.println(objClass.getTypeName());
			System.out.println("----- A -----");
			
			Field[] fields = objClass.getFields();
			
			for (Field field : fields) {
				String name = field.getName();
				Object value = field.get(a);
				field.set(b, value);
				System.out.println(name + ": " + value.toString());
			}
			System.out.println("----- B -----");
			
			//ClassA tmp = objClass.newInstance();
			
			Method[] methods =  objClass.getDeclaredMethods();
			for (Method method : methods) {
				System.out.println(method);
			}
			for (Method method : objClass.getMethods()) {
				System.out.println(method.getName());
				//method.invoke(b, method);
				System.out.println(method.getParameterCount());
				//System.out.println(method.getParameters()());
				
				if("setAge".equals(method.getName())){  
	                //method.invoke(b, method.get);  
					
	            }  
			}
			

			

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("----- C -----");
		ReflectionTest rt = new ReflectionTest();
		try {
			rt.testSetMethod();
			
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

