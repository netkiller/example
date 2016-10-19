package cn.netkiller.example;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

public class NginxAndOpenSSLAndTomcatAndHttpclient {
	public static void main(String[] args) throws ParseException, IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
		SSLContextBuilder builder = new SSLContextBuilder();
		builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
		SSLConnectionSocketFactory sslFactory = new SSLConnectionSocketFactory(builder.build());
		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslFactory).build();

		//HttpGet httpGet = new HttpGet("https://cfd:99U3YruuUe1CNXnLFOKe@cfapi.cfdealer88.com/v1/withdraw/ping.json");
		//HttpGet httpGet = new HttpGet("https://cfd:99U3YruuUe1CNXnLFOKe@api.cfd88.com/v1/withdraw/ping.json");
		HttpGet httpGet = new HttpGet("https://cfd:99U3YruuUe1CNXnLFOKe@api.cfd88.com/v1/withdraw/read/billno/B0402162190527090856.json");
		
		CloseableHttpResponse response = httpclient.execute(httpGet);
		try {
			System.out.println(response.getStatusLine());
			HttpEntity entity = response.getEntity();
			String responseBody = EntityUtils.toString(entity, "UTF-8");
			System.out.println(responseBody.toString());
		} finally {
			response.close();
		}
	}
}
