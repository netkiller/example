package cn.netkiller.security;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {

	public SHA256() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		String text = "Text to hash, cryptographically.";

		// Change this to UTF-16 if needed
		md.update(text.getBytes(StandardCharsets.UTF_8));
		byte[] digest = md.digest();

		String hex = String.format("%064x", new BigInteger(1, digest));
		System.out.println(hex);

	}

}
