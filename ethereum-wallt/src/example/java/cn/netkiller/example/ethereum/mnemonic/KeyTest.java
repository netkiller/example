package cn.netkiller.example.ethereum.mnemonic;

import java.math.BigInteger;
import java.util.List;

import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.HDUtils;
import org.bitcoinj.wallet.DeterministicKeyChain;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.UnreadableWalletException;
import org.web3j.crypto.Credentials;

public class KeyTest {

	public static void main(String[] args) throws UnreadableWalletException {
		// TODO Auto-generated method stub
		String mnemonicCode = "client dune unfair assume level width bind control mad member old crystal";

		// BitcoinJ
		long creationTimeSeconds = System.currentTimeMillis() / 1000;
		String passphrase = "123";
		DeterministicSeed seed = new DeterministicSeed(mnemonicCode, null, passphrase, creationTimeSeconds);
		DeterministicKeyChain chain = DeterministicKeyChain.builder().seed(seed).build();
		List<ChildNumber> keyPath = HDUtils.parsePath("M/44H/60H/0H/0/0");
		DeterministicKey key = chain.getKeyByPath(keyPath, true);
		BigInteger privKey = key.getPrivKey();

		// Web3j
		Credentials credentials = Credentials.create(privKey.toString(16));
		String address = credentials.getAddress();
		String privateKey = privKey.toString(16);
		System.out.println(address);
		System.out.println(privateKey);
	}
}
