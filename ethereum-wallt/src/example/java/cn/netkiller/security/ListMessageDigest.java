package cn.netkiller.security;

import java.security.Security;
import java.util.Set;

public class ListMessageDigest {

	public static void main(String[] args) {

		Set<String> messageDigest = Security.getAlgorithms("MessageDigest");
		messageDigest.forEach(x -> System.out.println(x));

	}

}