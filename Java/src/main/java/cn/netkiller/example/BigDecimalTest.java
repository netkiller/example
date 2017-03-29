package cn.netkiller.example;

import java.math.BigDecimal;

public class BigDecimalTest {

	public BigDecimalTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BigDecimal first = new BigDecimal("1.0");
		BigDecimal second = new BigDecimal("1.77");
		System.out.println(String.format("%s, %s", first, second));

		if (first.equals(second))

			System.out.println("equals: true");

		else

			System.out.println("equals: false");

		if (first.compareTo(second) == 0)

			System.out.println("compareTo: true");
		else
			System.out.println("compareTo: false");

		BigDecimal zero = new BigDecimal("0");
		BigDecimal one = new BigDecimal("1");
		BigDecimal minus = new BigDecimal("-1");

		if (zero.compareTo(one) < 0)

			System.out.println("比較演算子[ <  ]: true");

		if (one.compareTo(one) == 0)

			System.out.println("比較演算子[ == ]: true");

		if (zero.compareTo(minus) > 0)

			System.out.println("比較演算子[ >  ]: true");

		if (zero.compareTo(minus) >= 0)

			System.out.println("比較演算子[ >= ]: true");

		if (zero.compareTo(minus) != 0)

			System.out.println("比較演算子[ != ]: true");

		if (zero.compareTo(one) <= 0)
			System.out.println("比較演算子[ <= ]: true");

	}

}
