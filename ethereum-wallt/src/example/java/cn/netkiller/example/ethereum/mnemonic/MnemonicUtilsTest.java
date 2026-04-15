package cn.netkiller.example.ethereum.mnemonic;

import java.security.SecureRandom;

//import org.web3j.crypto.Credentials;
//import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.MnemonicUtils;
//import static org.web3j.crypto.Hash.sha256;

public class MnemonicUtilsTest {

	public MnemonicUtilsTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		byte[] initialEntropy = new byte[16];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(initialEntropy);

		String mnemonic = MnemonicUtils.generateMnemonic(initialEntropy);
		System.out.println(mnemonic);

//		String password = "netkiller";
//		byte[] seed = MnemonicUtils.generateSeed(mnemonic, password);
//		ECKeyPair ecKeyPair = ECKeyPair.create(sha256(seed));
//
//		// System.out.println(ecKeyPair.getPublicKey().toString(16));
//		System.out.println(ecKeyPair.getPrivateKey().toString(16));
//
//		Credentials credentials = Credentials.create(ecKeyPair);
//		String address = credentials.getAddress();
//
//		System.out.println(address);

	}

}
