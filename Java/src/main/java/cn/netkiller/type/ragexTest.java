package cn.netkiller.type;

public class ragexTest {

	public ragexTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String str = "<html>Netkiller</html>";
		String regex = "<html>|</html>";
		System.out.println(str.replaceAll(regex, ""));

		System.out.println("CF/USD/GTS/MM/MIN/0/A/XAGUSD_CF".replaceAll(".*/(.*)_CF", "$1"));

		System.out.println("CN/NETKILLER/WWW/Neo_Chen".replaceAll(".*/(.+)_Chen", "$1"));
	}

}
