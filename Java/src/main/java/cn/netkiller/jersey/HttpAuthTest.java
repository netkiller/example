package cn.netkiller.jersey;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

public class HttpAuthTest {

	public HttpAuthTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientConfig clientConfig = new ClientConfig();

		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("neo", "chen");
		clientConfig.register(feature);

		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target("http://api.netkiller.cn/v1/config/ping.json").path("");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

		System.out.println(response.getStatus());
		System.out.println(response.getStatusInfo());

		if (response.getStatus() == 200) {

			String output = response.readEntity(String.class);
			System.out.println(output);

		}
	}

}
