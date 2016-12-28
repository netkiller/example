package cn.netkiller.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class URLTEST {

	public URLTEST() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			String jarfile = "jar:file:/absolute/location/of/yourJar.jar!/1.txt";
			URL jar = new URL(jarfile);

			URL url = new URL("http://www.netkiller.cn/index.html");

			Object o = url.getContent();
			System.out.println("I got a " + o.getClass().getName());

			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

			String inputLine;
			while ((inputLine = in.readLine()) != null)
				System.out.println(inputLine);
			in.close();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
