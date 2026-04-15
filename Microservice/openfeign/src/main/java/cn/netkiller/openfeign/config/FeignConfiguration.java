//package cn.netkiller.openfeign.config;
//
//import java.security.KeyManagementException;
//import java.security.NoSuchAlgorithmException;
//import java.security.cert.CertificateException;
//import java.security.cert.X509Certificate;
//
//import javax.net.ssl.HostnameVerifier;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.SSLSession;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.X509TrustManager;
//
//import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
//import org.springframework.cloud.openfeign.ribbon.CachingSpringLoadBalancerFactory;
//import org.springframework.cloud.openfeign.ribbon.LoadBalancerFeignClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import feign.Client;
//
//@Configuration
//public class FeignConfiguration {
//	@Bean
//	public Client feignClient(CachingSpringLoadBalancerFactory cachingFactory, SpringClientFactory clientFactory) throws NoSuchAlgorithmException, KeyManagementException {
//		SSLContext ctx = SSLContext.getInstance("SSL");
//		X509TrustManager tm = new X509TrustManager() {
//			@Override
//			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//			}
//
//			@Override
//			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//			}
//
//			@Override
//			public X509Certificate[] getAcceptedIssuers() {
//				return null;
//			}
//		};
//		ctx.init(null, new TrustManager[] { tm }, null);
//		return new LoadBalancerFeignClient(new Client.Default(ctx.getSocketFactory(), new HostnameVerifier() {
//
//			@Override
//			public boolean verify(String hostname, SSLSession session) {
//				return true;
//			}
//		}), cachingFactory, clientFactory);
//	}
//}