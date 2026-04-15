package cn.netkiller.example.ethereum.wallet;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

public class WalletMain {
	// String url = "http://172.16.0.1:8545/";
	// Web3j web3j = Web3j.build(new HttpService(url));

	public void createWallet() throws NoSuchAlgorithmException, NoSuchProviderException,
			InvalidAlgorithmParameterException, CipherException, IOException {

		File file = new File("/tmp/ethereum/keystore");
		String password = "passw0rd";
		String fileName = WalletUtils.generateFullNewWalletFile(password, file);
		System.out.println(fileName);

	}

	public void walletAddress() throws IOException, CipherException {

		File file = new File(
				"/tmp/ethereum/keystore/UTC--2018-02-04T10-43-27.339000000Z--7cab470df532710d13078c5cdc0812a27f70cf51.json");
		String password = "passw0rd";
		Credentials credentials = WalletUtils.loadCredentials(password, file);
		System.out.println(credentials.getAddress());

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WalletMain wallet = new WalletMain();
		try {
			wallet.createWallet();
			wallet.walletAddress();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
