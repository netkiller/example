package cn.netkiller.example.bitcoin.mnemonic;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.HDUtils;
import org.bitcoinj.crypto.MnemonicCode;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.wallet.DeterministicKeyChain;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.UnreadableWalletException;
import org.bitcoinj.wallet.Wallet;

import com.google.common.base.Joiner;

public class ImportMnemonic {

	public ImportMnemonic() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws UnreadableWalletException {
		// TODO Auto-generated method stub

		String mnemonic = "income velvet eye juice finger debris gloom already tobacco argue fault lava";

		// String filePrefix = "testnet";
		NetworkParameters params = TestNet3Params.get();
		// WalletAppKit kit = new WalletAppKit(params, new File("."), filePrefix);
		//
		long creationTimeSeconds = MnemonicCode.BIP39_STANDARDISATION_TIME_SECS;
		DeterministicSeed seed = new DeterministicSeed(mnemonic, null, "", creationTimeSeconds);
		//
		// kit.restoreWalletFromSeed(seed);
		//
		// kit.startAsync();
		// kit.awaitRunning();
		//
		// Wallet wallet = kit.wallet();
		// System.out.println(wallet);
		//
		// System.out.println("Current Receive Address: " + wallet.currentReceiveAddress().toString() + "\nIssued Receive Addresses: " + wallet.getIssuedReceiveAddresses().toString() + "\nMnemonic: " + String.join(" ", wallet.getActiveKeyChain().getMnemonicCode()) + "\nWallets Balance: " + wallet.getBalance().toPlainString() + " BTC");

		DeterministicKeyChain chain = DeterministicKeyChain.builder().seed(seed).build();

		List<ChildNumber> keyPath = HDUtils.parsePath("M/44H/1H/0H/0/0");

		DeterministicKey dkey = chain.getKeyByPath(keyPath, true);

		BigInteger privKey = dkey.getPrivKey();

		String mnemonicCode = Joiner.on(" ").join(seed.getMnemonicCode());

		System.out.println("We created a new mnemonicCode:\n" + mnemonicCode);
		System.out.println("We created a new seed:\n" + seed);
		System.out.println("We created a new dkey:\n" + dkey);
		System.out.println("privKey:" + privKey + "\nprivateKey:" + dkey.getPrivateKeyEncoded(params) + "\nAddress:" + dkey.toAddress(params).toString());

		// DeterministicKey ddkey = wallet.getKeyByPath(HDUtils.parsePath("M/0H/0/0"));
		// DeterministicKeyChain wch = wallet.getActiveKeyChain();
		// System.out.println("We created a new ddkey:\n" + ddkey);

		ECKey key1 = ECKey.fromPrivate(dkey.getPrivKey());

		List<ECKey> keys = new ArrayList<ECKey>();
		keys.add(key1);

		Wallet w1 = Wallet.fromKeys(params, keys);
		System.out.println("wallet(): " + w1);

	}

}
