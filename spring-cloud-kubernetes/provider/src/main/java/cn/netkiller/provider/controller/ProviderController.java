package cn.netkiller.provider.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ProviderController {

//	@SuppressWarnings("unused")
//	private static final Log LOG = LogFactory.getLog(ProviderController.class);

	@Autowired
	private DiscoveryClient discoveryClient;

	@GetMapping("/")
	public String index() {
		return "Hello world!!!";
	}

	@GetMapping("/ping")
	public String ping() {
		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			return "Pong";
		}
	}

	@GetMapping("/services")
	public List<String> services() {
		return this.discoveryClient.getServices();
	}

}
