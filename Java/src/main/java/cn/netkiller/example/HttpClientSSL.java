package cn.netkiller.example;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

public class HttpClientSSL {

	public HttpClientSSL() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SSLContextBuilder builder = new SSLContextBuilder();
		try {
			builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());

			SSLConnectionSocketFactory sslFactory = new SSLConnectionSocketFactory(builder.build());
			CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslFactory).build();

			HttpPost httpPost = new HttpPost("https://cfd:99U3YruuUe1CNXnLFOKe@api.cfd88.com/v1/withdraw/create.json");
			httpPost.addHeader("content-type", "application/json");
			httpPost.addHeader("Accept", "application/json");

			HttpEntity httpEntity = new StringEntity("{\"loginname\":\"888666\", \"bankname\":\"中国银行\",\"billno\":\"B040216210517590856\",\"flag\":\"a\",\"amount\":12,\"accountnumber\":\"9555500060007000\",\"accounttype\":\"借记卡\",\"createdate\":\"2016-09-10T20:12:12\",\"remarks\":\"CFD\",\"currency\":\"CNY\",\"accountname\":\"王宝强\",\"branchname\":\"南山支行\",\"bankaddress\":\"深圳市南山区科技园\",\"customerlevel\":2,\"trustlevel\":1}", "UTF-8");
			
			httpPost.setEntity(httpEntity);

			// HttpGet httpGet = new
			// HttpGet("https://cfd:99U3YruuUe1CNXnLFOKe@cfapi.cfdealer88.com/v1/withdraw/ping.json");
			// HttpGet httpGet = new
			// HttpGet("https://cfd:99U3YruuUe1CNXnLFOKe@api.cfd88.com/v1/withdraw/ping.json");
			CloseableHttpResponse response = httpclient.execute(httpPost);

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

		finally {

		}
	}

}
