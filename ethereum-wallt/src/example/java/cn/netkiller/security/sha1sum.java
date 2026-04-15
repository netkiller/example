package cn.netkiller.security;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class sha1sum {

	public sha1sum() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

		String hex = checksum("/etc/hosts");
		System.out.println(hex);
	}

	private static String checksum(String filepath) throws IOException, NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance("SHA"); // SHA, MD2, MD5, SHA-256, SHA-384...
		// file hashing with DigestInputStream
		try (DigestInputStream dis = new DigestInputStream(new FileInputStream(filepath), md)) {
			while (dis.read() != -1)
				; // empty loop to clear the data
			md = dis.getMessageDigest();
		}

		// bytes to hex
		StringBuilder result = new StringBuilder();
		for (byte b : md.digest()) {
			result.append(String.format("%02x", b));
		}
		return result.toString();

	}

}
