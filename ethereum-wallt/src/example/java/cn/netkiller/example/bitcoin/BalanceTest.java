package cn.netkiller.example.bitcoin;

import java.io.File;
import java.util.List;

import org.bitcoinj.core.Address;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.kits.WalletAppKit;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.wallet.Wallet;

public class BalanceTest {

	public BalanceTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		NetworkParameters params = TestNet3Params.get();
		String filePrefix = "testnet";
		WalletAppKit kit = new WalletAppKit(params, new File("."), filePrefix);

		// Download the block chain and wait until it's done.
		kit.startAsync();
		kit.awaitRunning();

		Wallet wallet = kit.wallet();

		List<Address> list = wallet.getWatchedAddresses();
		if (list.size() < 1) {
			wallet.addWatchedAddress(kit.wallet().freshReceiveAddress());
			System.out.println("New address created");
			list = wallet.getWatchedAddresses();
		}

		System.out.println("You have " + list.size() + " addresses!");
		for (Address address : list) {
			System.out.println(address.toString());

		}

		String balance = wallet.getBalance().toFriendlyString();
		System.out.println(balance);
	}

}
