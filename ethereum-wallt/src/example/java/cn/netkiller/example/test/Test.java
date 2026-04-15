package cn.netkiller.example.test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static BigDecimal weiValue(String input, String unit) {
		BigDecimal bigDecimal = new BigDecimal(input);
		bigDecimal = bigDecimal.multiply(new BigDecimal(unit));
		return bigDecimal;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// String i = Integer.valueOf("0x57c457",16).toString();
		System.out.println("0xea2299ab6e6d65fe895cf35c443087f8954b47e0".length());

		int decimal = 6;

		BigInteger amount = BigInteger.valueOf(1000000000000000L);

		System.out.println(amount);

		String tmp = amount.toString();

		if (tmp.length() < decimal) {
			tmp = String.format("%0" + decimal + "d", amount);
		}

		String number = new StringBuffer(tmp).insert(tmp.length() - decimal, ".").toString();
		BigDecimal balance = new BigDecimal(number);

		System.out.println(balance);

		System.out.println(BigDecimal.TEN.pow(decimal));
		BigDecimal balance1 = new BigDecimal("1234");
		BigDecimal value = balance1.divide(BigDecimal.TEN.pow(decimal));
		System.out.println(value);

		BigDecimal balance2 = new BigDecimal("12.107");
		BigDecimal value2 = balance2.multiply(BigDecimal.TEN.pow(decimal)).setScale(0, RoundingMode.DOWN);
		System.out.println(value2);

		double number1 = 895.25;
		String numberAsString = String.valueOf(number1);
		System.out.println(numberAsString);
		System.out.println(Double.valueOf("12.2"));
	}

}
