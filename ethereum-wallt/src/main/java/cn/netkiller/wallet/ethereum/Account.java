package cn.netkiller.wallet.ethereum;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.WalletUtils;

public class Account {
	private String address;

	public Account() {
		// TODO Auto-generated constructor stub
	}

	public String getAddressFromPrivateKey(String privateKey) {
		Credentials credentials = Credentials.create(privateKey);
		this.address = credentials.getAddress();
		return this.address;
	}

	public void exportKeystore(BigInteger privateKey, String password, String directory) {
		ECKeyPair ecKeyPair = ECKeyPair.create(privateKey);
		try {
			String keystoreName = WalletUtils.generateWalletFile(password, ecKeyPair, new File(directory), true);
			System.out.println("keystore name " + keystoreName);
		} catch (CipherException | IOException e) {
			e.printStackTrace();
		}
	}
}
