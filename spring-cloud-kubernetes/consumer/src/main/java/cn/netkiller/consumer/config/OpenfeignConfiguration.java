package cn.netkiller.consumer.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import feign.codec.Decoder;

@Configuration
//@EnableReactiveFeignClients
public class OpenfeignConfiguration {

	@Bean
	public Decoder feignDecoder() {

		ObjectFactory<HttpMessageConverters> messageConverters = () -> {
			HttpMessageConverters converters = new HttpMessageConverters();
			return converters;
		};
		return new SpringDecoder(messageConverters);
	}

//	@Bean
//	public Decoder feignDecoder() {
//		return new ResponseEntityDecoder(new SpringDecoder(feignHttpMessageConverter()));
//	}
//
//	public ObjectFactory<HttpMessageConverters> feignHttpMessageConverter() {
//		final HttpMessageConverters httpMessageConverters = new HttpMessageConverters(
//				new MappingJackson2HttpMessageConverter());
//		return new ObjectFactory<HttpMessageConverters>() {
//			@Override
//			public HttpMessageConverters getObject() throws BeansException {
//				return httpMessageConverters;
//			}
//		};
//	}

	/*
	 * @Bean public Contract feignContract() { return new feign.Contract.Default();
	 * }
	 * 
	 * @Bean public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
	 * return new BasicAuthRequestInterceptor("user", "password"); }
	 */
}
