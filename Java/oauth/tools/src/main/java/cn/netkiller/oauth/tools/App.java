package cn.netkiller.oauth.tools;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		int i = 0;
		while (i < 10) {
//			String password = "123456";
			String password = "py2nplfq96hrjqw1o3AjA4XkBxAFmBrB";
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(password);

			System.out.println(hashedPassword);
			i++;
		}
	}
}