package cn.netkiller.example.bitcoin;

import java.io.File;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.kits.WalletAppKit;
import org.bitcoinj.params.RegTestParams;
import org.bitcoinj.params.TestNet3Params;

public class CreateAddress {

	public CreateAddress() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String filePrefix = "testnet";
		NetworkParameters params = TestNet3Params.get();
		WalletAppKit kit = new WalletAppKit(params, new File("."), filePrefix) {
			@Override
			protected void onSetupCompleted() {
				if (wallet().getKeyChainGroupSize() < 1)
					wallet().importKey(new ECKey());
			}
		};

		if (params == RegTestParams.get()) {
			kit.connectToLocalHost();
		}

		// Download the block chain and wait until it's done.
		kit.startAsync();
		kit.awaitRunning();
	}

}
