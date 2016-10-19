package cn.netkiller.annotations;

public class AnnotationsTest {

	@FieldTest("Neo")
	private String name ="sss";

	public AnnotationsTest() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@MethodTest
	public void testA() {
			System.out.println("Test is true");
	}

	@MethodTest(status = false)
	public void testB() {
			System.out.println("Test is false");
	}

	@MethodTest(status = false)
	public void testC() {
			System.out.println("Test is true");
	}

	public static void main(String[] args) {
	}

}
