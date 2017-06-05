package cn.netkiller.url;

import java.io.UnsupportedEncodingException;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		System.out.println("Sys");
		String crypt = Aes.encrypt("18555555554", "FmbVKoOEUyJkIJmB");
		System.out.println(crypt);
		String en = java.net.URLEncoder.encode(crypt, "UTF-8");
		System.out.println(en);
		String de = java.net.URLDecoder.decode(en, "UTF-8");
		System.out.println(de);
		System.out.println(Aes.decrypt(de, "FmbVKoOEUyJkIJmB"));
	}

}
