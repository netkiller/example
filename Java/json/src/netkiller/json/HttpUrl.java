package netkiller.json;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import javax.json.*;

public class HttpUrl {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String URL = "http://www.example.com/mix/json/2/20/0.html";
		// system.out.println("Requeted URL:" + URL);
		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;
		try {
			URL url = new URL(URL);
			urlConn = url.openConnection();
			if (urlConn != null)
				urlConn.setReadTimeout(60 * 1000);
			if (urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(), Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}
					bufferedReader.close();
				}
			}
			in.close();

			String jsonString = sb.toString();

			//System.out.println(jsonString);

			JsonReader reader = Json.createReader(new StringReader(jsonString));

			JsonObject jsonObject = reader.readObject();

			reader.close();

			// System.out.println(jsonObject.size());

			for (int i = 0; i < jsonObject.size() - 2; i++) {
				JsonObject rowObject = jsonObject.getJsonObject(Integer.toString(i));
				// System.out.println(rowObject.toString());
				System.out.printf("%s\t%s\t%s\n", rowObject.getJsonString("id"), rowObject.getJsonString("title"),
						rowObject.getJsonString("ctime"));
			}

		} catch (Exception e) {
			throw new RuntimeException("Exception while calling URL:" + URL, e);
		}

	}

}
