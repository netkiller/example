package cn.netkiller.httpclient;

import java.io.File;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

public class HttpsPost {

	public HttpsPost() {
	}

	// TODO Auto-generated constructor stub
	public final static void main(String[] args) throws Exception {
		// Trust own CA and all self-signed certs
		SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(new File("d:/netkiller.com.cer"), "nopassword".toCharArray(), new TrustSelfSignedStrategy()).build();
		// Allow TLSv1 protocol only
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		try {

			HttpGet httpget = new HttpGet("https://api.netkiller.net/v1/withdraw/ping.json");

			System.out.println("Executing request " + httpget.getRequestLine());

			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				HttpEntity entity = response.getEntity();

				System.out.println("----------------------------------------");
				System.out.println(response.getStatusLine());
				EntityUtils.consume(entity);
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
	}

}
