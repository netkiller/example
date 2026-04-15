package cn.netkiller.security;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Test {

	public Base64Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		String asB64 = Base64.getEncoder().encodeToString("some string".getBytes("utf-8"));
		System.out.println(asB64); // 输出为: c29tZSBzdHJpbmc=

		// 解码
		byte[] asBytes = Base64.getDecoder().decode("c29tZSBzdHJpbmc=");
		System.out.println(new String(asBytes, "utf-8")); // 输出为: some string

		// 但由于URL对反斜线“/”有特殊的意义，因此URL编码需要替换掉它，使用下划线替换
		String basicEncoded = Base64.getEncoder().encodeToString("subjects?abcd".getBytes("utf-8"));
		System.out.println("Using Basic Alphabet: " + basicEncoded);

		String urlEncoded = Base64.getUrlEncoder().encodeToString("subjects?abcd".getBytes("utf-8"));
		System.out.println("Using URL Alphabet: " + urlEncoded);
	}

}
