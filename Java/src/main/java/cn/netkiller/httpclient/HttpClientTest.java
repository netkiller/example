package cn.netkiller.httpclient;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

public class HttpClientTest {

	public HttpClientTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SSLContextBuilder builder = new SSLContextBuilder();
		try {
			builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());

			SSLConnectionSocketFactory sslFactory = new SSLConnectionSocketFactory(builder.build());
			CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslFactory).build();

			HttpGet httpGet = new HttpGet("https://api:password@api.cfd88.com/v1/withdraw/ping.json");
//			httpGet.addHeader("Host", "api.netkiller.com");
			CloseableHttpResponse response = httpclient.execute(httpGet);
			System.out.println(response.getStatusLine());
			HttpEntity entity = response.getEntity();
			String responseBody = EntityUtils.toString(entity, "UTF-8");
			System.out.println(responseBody.toString());
			response.close();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
