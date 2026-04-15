package cn.netkiller.example.bitcoin.mnemonic;

import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.Wallet;

public class MnemonicTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NetworkParameters params = TestNet3Params.get();
		Wallet wallet = new Wallet(params);

		DeterministicSeed seed = wallet.getKeyChainSeed();
		System.out.println("seed: " + seed.toString());

		System.out.println("creation time: " + seed.getCreationTimeSeconds());
		System.out.println("mnemonicCode: " + String.join(" ", seed.getMnemonicCode()));
	}

}
