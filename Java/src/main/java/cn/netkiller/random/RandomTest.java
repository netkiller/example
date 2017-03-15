package cn.netkiller.random;
import java.util.Random;
public class RandomTest {

	public RandomTest() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		double number=Math.random()*1000;
		System.out.println(number);
		
		
		Random rand = new Random();
        System.out.println(rand.nextInt(1000000));
	}
}



