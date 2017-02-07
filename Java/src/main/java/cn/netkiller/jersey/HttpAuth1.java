package cn.netkiller.jersey;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class HttpAuth1 {

	public HttpAuth1() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {

			ClientConfig clientConfig = new DefaultClientConfig();

			Client client = Client.create(clientConfig);
			client.addFilter(new HTTPBasicAuthFilter("user", "password"));

			WebResource webResource = client.resource("http://api.netkiller.cn/v1/config/read.json?name=cache");

			ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			String output = response.getEntity(String.class);

			System.out.println("Server response .... \n");
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
