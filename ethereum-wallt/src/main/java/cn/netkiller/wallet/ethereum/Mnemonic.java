package cn.netkiller.wallet.ethereum;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.HDUtils;
import org.bitcoinj.crypto.MnemonicCode;
import org.bitcoinj.crypto.MnemonicException.MnemonicLengthException;
import org.bitcoinj.wallet.DeterministicKeyChain;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.UnreadableWalletException;
import org.web3j.crypto.Credentials;

public class Mnemonic {
	private String passphrase;

	public Mnemonic() {
		this.passphrase = "";

	}

	public Mnemonic(String passphrase) {
		if (passphrase != null) {
			this.passphrase = passphrase;
		}
	}

	public String getMnemonic() {
		SecureRandom secureRandom = new SecureRandom();
		long creationTimeSeconds = System.currentTimeMillis() / 1000;
		DeterministicSeed deterministicSeed = new DeterministicSeed(secureRandom, 128, this.passphrase, creationTimeSeconds);
		List<String> mnemonicCode = deterministicSeed.getMnemonicCode();
		return (String.join(" ", mnemonicCode));
	}

	public static void main(String[] args) throws UnreadableWalletException, IOException, MnemonicLengthException {
		// TODO Auto-generated method stub
		// ECKey key = new ECKey();
		// MnemonicCode mc = new MnemonicCode();
		// final List<String> split = mc.toMnemonic(key.getSecretBytes());
		// System.out.println(split);

		SecureRandom secureRandom = new SecureRandom();
		byte[] entropy = new byte[20];
		secureRandom.nextBytes(entropy);

		MnemonicCode mc = new MnemonicCode();
		final List<String> split = mc.toMnemonic(entropy);
		String englist = String.join(" ", split);
		System.out.println(englist);

		InputStream stream = Mnemonic.class.getResourceAsStream("netkiller_mnemonic.txt");

		// InputStream stream = new FileInputStream("mnemonic/wordlist/chinese_simplified.txt");
		// InputStream stream = new FileInputStream("/tmp/netkiller.txt");
		MnemonicCode chinese = new MnemonicCode(stream, null);

		final List<String> mnemonic = chinese.toMnemonic(entropy);
		System.out.println(String.join(" ", mnemonic));

		long creationTimeSeconds = System.currentTimeMillis() / 1000;
		DeterministicSeed seed = new DeterministicSeed(mnemonic, null, "", creationTimeSeconds);

		DeterministicKeyChain chain = DeterministicKeyChain.builder().seed(seed).build();
		List<ChildNumber> keyPath = HDUtils.parsePath("M/44H/60H/0H/0/0");
		DeterministicKey key = chain.getKeyByPath(keyPath, true);
		BigInteger privKey = key.getPrivKey();
		String privateKey = privKey.toString(16);

		Credentials credentials = Credentials.create(privateKey);
		String address = credentials.getAddress();

		System.out.println("汉字助记词：" + String.join(" ", seed.getMnemonicCode()));
		System.out.println("地址：" + address);
		System.out.println("私钥：" + privateKey);

	}
}
