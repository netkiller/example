package cn.netkiller.wallet.bitcoin;

import java.io.File;
import java.math.BigInteger;
import java.util.List;

import org.bitcoinj.core.Address;
import org.bitcoinj.core.BlockChain;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.PeerGroup;
import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.crypto.HDUtils;
import org.bitcoinj.kits.WalletAppKit;
import org.bitcoinj.net.discovery.DnsDiscovery;
import org.bitcoinj.params.RegTestParams;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.store.BlockStoreException;
import org.bitcoinj.store.SPVBlockStore;
import org.bitcoinj.utils.BriefLogFormatter;
import org.bitcoinj.wallet.DeterministicKeyChain;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.UnreadableWalletException;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.wallet.Wallet;

import com.google.common.base.Joiner;

public class Bitcoin {
	public static String filePrefix = "forwarding-service-testnet";
	public static NetworkParameters params = TestNet3Params.get();

	public Bitcoin() {
		// TODO Auto-generated constructor stub
	}

	public void initWallet() {
		NetworkParameters params = TestNet3Params.get();
		WalletAppKit kit = new WalletAppKit(params, new File("."), filePrefix) {
			@Override
			protected void onSetupCompleted() {
				// This is called in a background thread after startAndWait is called, as setting up various objects
				// can do disk and network IO that may cause UI jank/stuttering in wallet apps if it were to be done
				// on the main thread.
				if (wallet().getKeyChainGroupSize() < 1)
					wallet().importKey(new ECKey());
			}
		};

		if (params == RegTestParams.get()) {
			// Regression test mode is designed for testing and development only, so there's no public network for it.
			// If you pick this mode, you're expected to be running a local "bitcoind -regtest" instance.
			kit.connectToLocalHost();
		}

		// Download the block chain and wait until it's done.
		kit.startAsync();
		kit.awaitRunning();

	}

	/*
	 * public void createWallet() {
	 * 
	 * BriefLogFormatter.init();
	 * 
	 * // NetworkParameters params = RegTestParams.get();
	 * 
	 * System.out.println("Network: " + params.getId()); // System.out.println("Forwarding address: " + forwardingAddress); // Start up a basic app using a class that automates some boilerplate. WalletAppKit kit = new WalletAppKit(params, new File("."), filePrefix) { protected void onSetupCompleted() {
	 * 
	 * if (wallet().getKeyChainGroupSize() < 1) wallet().importKey(new ECKey()); } };
	 * 
	 * // if (params == TestNet3Params.get()) { // kit.connectToLocalHost(); // }
	 * 
	 * kit.startAsync(); kit.awaitRunning();
	 * 
	 * Wallet wallet = kit.wallet(); System.out.println("Wallet->" + wallet); System.out.println(wallet.getIssuedReceiveKeys().get(0).toAddress(TestNet3Params.get()).toBase58().toString());
	 * 
	 * DeterministicSeed seed = wallet.getKeyChainSeed(); DeterministicKeyChain chain = DeterministicKeyChain.builder().seed(seed).build();
	 * 
	 * List<ChildNumber> keyPath = HDUtils.parsePath("M/44H/1H/0H/0/0");
	 * 
	 * DeterministicKey dkey = chain.getKeyByPath(keyPath, true);
	 * 
	 * BigInteger privKey = dkey.getPrivKey();
	 * 
	 * String mnemonicCode = Joiner.on(" ").join(seed.getMnemonicCode());
	 * 
	 * System.out.println("We created a new mnemonicCode:\n" + mnemonicCode); System.out.println("We created a new seed:\n" + seed); System.out.println("We created a new dkey:\n" + dkey); System.out.println("dkey ->privKey:" + privKey + "\\nprivKey str:" + dkey.getPrivateKeyEncoded(params) + "\nAddress:" + dkey.toAddress(params).toString());
	 * 
	 * }
	 * 
	 * public void importMnemonic(String mnemonic) throws UnreadableWalletException {
	 * 
	 * String mnemonicCode = "yard impulse luxury drive today throw farm pepper survey wreck glass federal"; long creationTimeSeconds = System.currentTimeMillis() / 1000; DeterministicSeed seed = new DeterministicSeed(mnemonicCode, null, "", creationTimeSeconds); Wallet wallet = Wallet.fromSeed(params, seed); System.out.println(wallet.toString()); System.out.println(wallet.getWatchedAddresses());
	 * 
	 * }
	 * 
	 * public void exportMnemonic() { WalletAppKit kit = new WalletAppKit(params, new File("."), filePrefix); Wallet wallet = kit.wallet(); DeterministicSeed seed = wallet.getKeyChainSeed(); System.out.println("Seed words are: " + Joiner.on(" ").join(seed.getMnemonicCode())); System.out.println("Seed birthday is: " + seed.getCreationTimeSeconds()); }
	 */

	public void getBalances() {
		// NetworkParameters params = TestNet3Params.get();

		WalletAppKit kit = new WalletAppKit(params, new File("."), filePrefix);

		// Download the block chain and wait until it's done.
		kit.startAsync();
		kit.awaitRunning();

		List<Address> list = kit.wallet().getWatchedAddresses();
//		if (list.size() < 2) {
//			kit.wallet().addWatchedAddress(kit.wallet().freshReceiveAddress());
//			System.out.println("New address created");
//		}

		System.out.println("You have " + list.size() + " addresses!");
		for (Address a : list) {
			System.out.println(a.toString());
		}

		String balance = kit.wallet().getBalance().toFriendlyString();
		System.out.println(balance);
	}

	public static void main(String[] args) throws UnreadableWalletException, BlockStoreException {
		// TODO Auto-generated method stub
		Bitcoin test = new Bitcoin();
		// test.initWallet();
		// test.exportMnemonic();
		// test.importMnemonic("yard impulse ÷luxury drive today throw farm pepper survey wreck glass federal");
		// test.createWallet();
		test.getBalances();

	}

}
