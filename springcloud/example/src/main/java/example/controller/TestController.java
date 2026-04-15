package example.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class TestController {
	@Autowired
	private OAuth2RestOperations restTemplate;

	@GetMapping("/")
	@ResponseBody
	public String index() {
		OAuth2AccessToken token = restTemplate.getAccessToken();
		System.out.println(token.getValue());
		String tmp = restTemplate.getForObject("http://api.alpha.5kwords.com/", String.class);
		System.out.println(tmp);
		return tmp;
	}

	@GetMapping("/ssl")
	@ResponseBody
	public String ssl() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		String url = "https://api.alpha.5kwords.com/";

		// InputStream keyStoreInputStream =
		// RemoteService.class.getClass().getClassLoader().getResourceAsStream("keystore.jks");
		// if (keyStoreInputStream == null) {
		// throw new FileNotFoundException("Could not find file named 'shunya_keystore'
		// in the CLASSPATH");
		// }
		// final KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
		// try {
		// trustStore.load(keyStoreInputStream, "shunya".toCharArray());
		// } finally {
		// keyStoreInputStream.close();
		// }
		// SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(trustStore,
		// new TrustSelfSignedStrategy()).build();
		//
		//

		// Allow TLSv1 protocol only
		// SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext,
		// new String[]{"TLSv1"}, null,
		// SSLConnectionSocketFactory.getDefaultHostnameVerifier());
		// CloseableHttpClient httpClient =
		// HttpClients.custom().setSSLSocketFactory(sslsf).build();
		// HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory
		// = new HttpComponentsClientHttpRequestFactory(httpClient);
		// httpComponentsClientHttpRequestFactory.setConnectTimeout(60000);
		// httpComponentsClientHttpRequestFactory.setReadTimeout(180000);

		SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(null, (chain, authType) -> true).build();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null, new NoopHostnameVerifier());
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		httpComponentsClientHttpRequestFactory.setConnectTimeout(60000);
		httpComponentsClientHttpRequestFactory.setReadTimeout(180000);

		final RestTemplate restTemplate = new RestTemplate(httpComponentsClientHttpRequestFactory);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + this.restTemplate.getAccessToken().getValue());
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		String str = response.getBody();
		return str;
	}

	@GetMapping("/pkcs12")
	@ResponseBody
	public String PKCS12(String url, String data) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, KeyManagementException, UnrecoverableKeyException {
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		FileInputStream instream = new FileInputStream(new File("/opt/xxx.p12"));
		keyStore.load(instream, "netkiller".toCharArray());
		// Trust own CA and all self-signed certs
		SSLContext sslcontext = SSLContextBuilder.create().loadKeyMaterial(keyStore, "netkiller".toCharArray()).build();
		// Allow TLSv1 protocol only
		HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null, hostnameVerifier);
		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpclient);

		RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Connection", "keep-alive");
		httpHeaders.add("Accept", "*/*");
		httpHeaders.add("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		httpHeaders.add("Host", "api.netkiller.cn");
		httpHeaders.add("X-Requested-With", "XMLHttpRequest");
		httpHeaders.add("Cache-Control", "max-age=0");
		httpHeaders.add("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");

		HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
		return response.getBody();

	}
	


}
