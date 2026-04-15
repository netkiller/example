package cn.netkiller.security;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
	private static final String initVector = "BxsdTjVUNDQwRXNQ";
	private String key;
	
	public AES(String key) {
		// TODO Auto-generated constructor stub
		this.key = key;
	}

	public String encrypt(String value) {
		return this.encrypt(value, this.key);
	}

	public String encrypt(String value, String key) {
		try {
			IvParameterSpec ivParameterSpec = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

			byte[] encrypted = cipher.doFinal(value.getBytes());
			return Base64.getEncoder().encodeToString(encrypted);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public String decrypt(String encrypted) {
		return this.decrypt(encrypted, this.key);
	}

	public String decrypt(String encrypted, String key) {
		try {
			IvParameterSpec ivParameterSpec = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
			byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

			return new String(original);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) {
		// key 长度16个字节
		String key = "www.netkiller.cn";

		System.out.println(key.length());
		AES aes = new AES(key);
		String en = aes.encrypt("Helloworld!!!");
		String de = aes.decrypt(en);
		System.out.println(en);
		System.out.println(de);

	}
}
