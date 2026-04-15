package cn.netkiller.example.bitcoin.mnemonic;

import java.io.File;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.kits.WalletAppKit;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.wallet.UnreadableWalletException;
import org.bitcoinj.wallet.Wallet;

public class WalletAppKitMnemonic {

	public WalletAppKitMnemonic() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws UnreadableWalletException {
		// TODO Auto-generated method stub

		String filePrefix = "testnet9";
		NetworkParameters params = TestNet3Params.get();
		WalletAppKit kit = new WalletAppKit(params, new File("."), filePrefix) {
			@Override
			protected void onSetupCompleted() {

				if (wallet().getKeyChainGroupSize() < 5)
					wallet().importKey(new ECKey());

			}
		};

		// Download the block chain and wait until it's done.
		kit.startAsync();
		kit.awaitRunning();

		Wallet wallet = kit.wallet();
		System.out.println(wallet);

		System.out.println("Current Receive Address: " + wallet.currentReceiveAddress().toString() + "\nIssued Receive Addresses: " + wallet.getIssuedReceiveAddresses().toString() + "\nMnemonic: " + String.join(" ", wallet.getActiveKeyChain().getMnemonicCode()) + "\nWallets Balance: " + wallet.getBalance().toPlainString() + " BTC");

	}

}
