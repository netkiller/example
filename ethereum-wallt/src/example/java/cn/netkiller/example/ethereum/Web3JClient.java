package cn.netkiller.example.ethereum;

import java.io.IOException;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

public class Web3JClient {
	// TODO Auto-generated method stub
	// private static String ip = "http://172.16.0.1:8545/";

	// private volatile static Web3j web3j;
	//
	// public static Web3j getClient() {
	// if (web3j == null) {
	// synchronized (Web3JClient.class) {
	// if (web3j == null) {
	// web3j = Web3j.build(new HttpService(ip));
	// }
	// }
	// }
	// return web3j;
	// }

	public static void main(String[] args) {
		String ip = "http://172.16.0.1:9545/";
		Web3j web3j = Web3j.build(new HttpService(ip)); // defaults to http://localhost:8545/

		try {
			Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
			String clientVersion = web3ClientVersion.getWeb3ClientVersion();
			System.out.println(clientVersion);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
