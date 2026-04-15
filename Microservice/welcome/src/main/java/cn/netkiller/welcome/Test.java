package cn.netkiller.welcome;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			System.out.println("关闭应用，释放资源");
		}));
	}

}
