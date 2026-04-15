package cn.netkiller.example.bitcoin;

import java.io.File;

import org.bitcoinj.core.Coin;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.TransactionConfidence;
import org.bitcoinj.kits.WalletAppKit;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.wallet.Wallet;
import org.bitcoinj.wallet.listeners.WalletCoinsReceivedEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;

public class ReceivingMoney {
	private static final Logger logger = LoggerFactory.getLogger(ReceivingMoney.class);
	public ReceivingMoney() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filePrefix = "testnet";
		NetworkParameters params = TestNet3Params.get();
		WalletAppKit kit = new WalletAppKit(params, new File("."), filePrefix);

		// Download the block chain and wait until it's done.
		kit.startAsync();
		kit.awaitRunning();

		kit.wallet().addCoinsReceivedEventListener(new WalletCoinsReceivedEventListener() {
			@Override
			public void onCoinsReceived(Wallet wallet, Transaction tx, Coin prevBalance, Coin newBalance) {
				// Runs in the dedicated "user thread".
				//
				// The transaction "tx" can either be pending, or included into a block (we didn't see the broadcast).
				Coin value = tx.getValueSentToMe(wallet);

				System.out.println("address for " + wallet.currentReceiveAddress().toString());
				System.out.println("Received tx for " + value.toFriendlyString() + ": " + tx);
				System.out.println("Previous balance is " + prevBalance.toFriendlyString());
				System.out.println("New estimated balance is " + newBalance.toFriendlyString());
				System.out.println("Coin received, wallet balance is :" + wallet.getBalance());

				System.out.println("Received tx for " + value.toFriendlyString() + ": " + tx);
				System.out.println("Transaction will be forwarded after it confirms.");
				// Wait until it's made it into the block chain (may run immediately if it's already there).
				//
				// For this dummy app of course, we could just forward the unconfirmed transaction. If it were
				// to be double spent, no harm done. Wallet.allowSpendingUnconfirmedTransactions() would have to
				// be called in onSetupCompleted() above. But we don't do that here to demonstrate the more common
				// case of waiting for a block.
				Futures.addCallback(tx.getConfidence().getDepthFuture(1), new FutureCallback<TransactionConfidence>() {
					public void onSuccess(TransactionConfidence result) {
						// "result" here is the same as "tx" above, but we use it anyway for clarity.
						System.out.println(result);
					}

					public void onFailure(Throwable t) {
					}
				});
			}
		});

	}

}
