package cn.netkiller.demo;

public class DemoSwitch {

	private static final int SATURDAY = 6;
	private static final int MONDAY = 1;
	private static final int TUESDAY = 0;
	private static final int WEDNESDAY = 0;
	private static final int THURSDAY = 0;
	private static final int FRIDAY = 0;
	private static final int SUNDAY = 0;

	public DemoSwitch() {

	}

	public static void main(String[] args) {

		// var number = 4;
		// var operation = "平方";
		// var result = switch (operation) {
		// case "立方" -> {
		// yield number * number * number;
		// }
		// case "平方" -> {
		// yield number * number;
		// }
		// default -> number;
		// };
		// System.out.println(result);

		// var day = 1;
		// boolean isWeekend = switch (day) {
		// case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> false;
		// case SATURDAY, SUNDAY -> true;
		// default -> throw new IllegalStateException("Illegal day entry :: " + day);
		// };
		// System.out.println(isWeekend);

		int money = 3;
		String cn = switch (money) {
		case 1 -> "壹";
		case 2 -> "贰";
		case 3 -> "叁";
		case 4 -> "肆";
		case 5 -> "伍";
		case 6 -> "陆";
		case 7 -> "柒";
		case 8 -> "捌";
		case 9 -> "玖";
		case 10 -> "拾";
		default -> "零";
		};
		System.out.println(cn);
	}

}
