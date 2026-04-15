package cn.netkiller.example.ethereum.mnemonic;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;

import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.UnreadableWalletException;

public class MnemonicTest {
	public static void main(String[] args) throws UnreadableWalletException, IOException {
		// TODO Auto-generated method stub

		String passphrase = "";
		SecureRandom secureRandom = new SecureRandom();
		long creationTimeSeconds = System.currentTimeMillis() / 1000;
		DeterministicSeed deterministicSeed = new DeterministicSeed(secureRandom, 128, passphrase, creationTimeSeconds);
		List<String> mnemonicCode = deterministicSeed.getMnemonicCode();
		System.out.println(String.join(" ", mnemonicCode));

	}
}
