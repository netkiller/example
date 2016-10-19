package cn.netkiller.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnotationExample {

	public AnnotationExample() {
		// TODO Auto-generated constructor stub
	}
	public void annotations(){
		
		Class<AnnotationsTest> obj = AnnotationsTest.class;
		
		try {
			
			for (Field field: AnnotationsTest.class.getClassLoader().loadClass(("cn.netkiller.annotations.AnnotationsTest")).getFields()) {
				System.out.println(field.getName());
				//if (field.isAnnotationPresent((Class<? extends Annotation>)FieldTest.class)) {
					
					for (Annotation anno : field.getDeclaredAnnotations()) {
						System.out.println("Annotation in Method '" + field + "' : " + anno);
					}
					
					FieldTest fieldTest = field.getAnnotation(FieldTest.class);
					
					System.out.printf("%s - %s \n",field.getName(), fieldTest.value());
				//}
				
			}
			System.out.println("----------");
			for (Method method : AnnotationsTest.class.getClassLoader()
					.loadClass(("cn.netkiller.annotations.AnnotationsTest")).getMethods()) {
				
				if (method.isAnnotationPresent((Class<? extends Annotation>)MethodTest.class)) {
					try {
						// iterates all the annotations available in the method
						for (Annotation anno : method.getDeclaredAnnotations()) {
							System.out.println("Annotation in Method '" + method + "' : " + anno);
						}
						MethodTest methodAnno = method.getAnnotation(MethodTest.class);
						if (methodAnno.status() == true) {
							System.out.println("Method status() = " + method);
							
							method.invoke(obj.newInstance());
							System.out.printf("Test '%s' - %s\n",methodAnno.toString(), method.getName());
						}

					} catch (Throwable ex) {
						ex.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnnotationsTest testAnnotations = new AnnotationsTest();
		System.out.println(testAnnotations.getName());
		testAnnotations.testA();
		testAnnotations.testB();
		testAnnotations.testC();
		
		System.out.println("----------");
		
		AnnotationExample annotationExample = new AnnotationExample();
		annotationExample.annotations();

		System.out.println("----------");
		
		Method[] methods = testAnnotations.getClass().getMethods();

        for (Method method : methods) {
        	MethodTest annos = method.getAnnotation(MethodTest.class);
            if (annos.status()) {
                try {
                    method.invoke(testAnnotations);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
		
	/*	
		
		for (Method method : obj.getDeclaredMethods()) {

			// if method is annotated with @Test
			if (method.isAnnotationPresent((Class<? extends Annotation>) AnnotationsTest.class)) {

				AnnotationsTest annotation = method.getAnnotation(AnnotationsTest.class);
				//AnnotationsTest test = (AnnotationsTest) annotation;

				if (test.status()) {

				  try {
					method.invoke(obj.newInstance());
					System.out.printf("%s - Test '%s' - passed %n", ++count, method.getName());
					passed++;
				  } catch (Throwable ex) {
					System.out.printf("%s - Test '%s' - failed: %s %n", ++count, method.getName(), ex.getCause());
					failed++;
				  }

				} else {
					System.out.printf("%s - Test '%s' - ignored%n", ++count, method.getName());
					ignore++;
				}

			}*/
	}

}
