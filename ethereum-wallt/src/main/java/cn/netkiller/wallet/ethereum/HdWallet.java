package cn.netkiller.wallet.ethereum;

import java.math.BigInteger;
import java.util.List;

import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.HDUtils;
import org.bitcoinj.wallet.DeterministicKeyChain;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.UnreadableWalletException;

public class HdWallet {

	private String mnemonic;
	private String path;
	private String passphrase;

	private String publicKey;
	private String privateKey;

	public HdWallet() {

	}

	public HdWallet(String mnemonic) {
		this.mnemonic = mnemonic;
		this.path = "M/44H/60H/0H/0/0";
		this.passphrase = "";
	}
	
	public HdWallet(String mnemonic, String passphrase) {
		this.mnemonic = mnemonic;
		this.path = "M/44H/60H/0H/0/0";
		this.passphrase = passphrase;
	}

	public HdWallet(String mnemonic, String passphrase, String path) {
		this.mnemonic = mnemonic;
		this.path = path;
		this.passphrase = passphrase;
	}

	public void setPath(String account, String change, String index) {
		this.path = String.format("M/44H/60H/%sH/%s/%s", account, change, index);
	}

	public BigInteger generate() throws UnreadableWalletException {

		long creationTimeSeconds = System.currentTimeMillis() / 1000;

		DeterministicSeed seed = new DeterministicSeed(this.mnemonic, null, this.passphrase, creationTimeSeconds);
		DeterministicKeyChain chain = DeterministicKeyChain.builder().seed(seed).build();
		// "M/44H/60H/0H/0/0"
		List<ChildNumber> keyPath = HDUtils.parsePath(this.path);
		DeterministicKey key = chain.getKeyByPath(keyPath, true);
		BigInteger privKey = key.getPrivKey();

		this.privateKey = privKey.toString(16);

		this.publicKey = key.getPubKey().toString();
		return privKey;

	}

	public String getPublicKey() {
		return this.publicKey;
	}

	public String getPrivateKey() {
		return this.privateKey;
	}
}
