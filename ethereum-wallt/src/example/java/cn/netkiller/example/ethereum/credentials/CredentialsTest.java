package cn.netkiller.example.ethereum.credentials;

import java.io.IOException;
import java.math.BigInteger;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

public class CredentialsTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String url = "http://172.16.0.1:8545/";
		Web3j web3j = Web3j.build(new HttpService(url)); // defaults to http://localhost:8545/

		try {
			String account = web3j.ethAccounts().send().getAccounts().get(0);
			Credentials credentials = Credentials.create(account);
			ECKeyPair keyPair = credentials.getEcKeyPair();
			BigInteger privateKey = keyPair.getPrivateKey();
			BigInteger publicKey = keyPair.getPublicKey();

			System.out.println(privateKey);
			System.out.println("---");
			System.out.println(publicKey);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
