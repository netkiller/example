package cn.netkiller.wallet.ethereum;

import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.CountDownLatch;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.ipc.UnixIpcService;

import rx.Subscription;

public class EthereumSubscription extends Ethereum {

	private static final int COUNT = 10;
	private Web3j web3;

	public EthereumSubscription() {
		// TODO Auto-generated constructor stub
		// web3 = Web3j.build(new HttpService("wss://ropsten.infura.io/ws"));
		// web3 = Web3j.build(new HttpService("https://ropsten.infura.io/CsS9shwaAab0z7B4LP2d"));
		web3 = Web3j.build(new UnixIpcService("/Users/neo/Library/Ethereum/geth.ipc"));
	}

	private void newBlockFilter() {
		Subscription subscription = web3.blockObservable(false).subscribe(block -> {
			System.out.println("new block come in");
			System.out.println("block number" + block.getBlock().getNumber());
		});
		subscription.unsubscribe();
	}

	private void pendingTransactionObservable() {
		Subscription subscription = web3.pendingTransactionObservable().subscribe(block -> {
			System.out.println("new block come in");
			System.out.println("block number" + block.getBlockHash());
		});
		subscription.unsubscribe();
	}

	private void test() {
		CountDownLatch countDownLatch = new CountDownLatch(1);

		System.out.println("Waiting for " + COUNT + " transactions...");
		Subscription subscription = web3.blockObservable(true).take(COUNT).subscribe(ethBlock -> {
			EthBlock.Block block = ethBlock.getBlock();
			LocalDateTime timestamp = Instant.ofEpochSecond(block.getTimestamp().longValueExact()).atZone(ZoneId.of("UTC")).toLocalDateTime();
			int transactionCount = block.getTransactions().size();
			String hash = block.getHash();
			String parentHash = block.getParentHash();

			System.out.println(timestamp + " " + "Tx count: " + transactionCount + ", " + "Hash: " + hash + ", " + "Parent hash: " + parentHash);
			countDownLatch.countDown();
		}, Throwable::printStackTrace);

		subscription.unsubscribe();
	}

	private void newTransactionFilter() {
		Subscription subscription = web3.transactionObservable().subscribe(transaction -> {
			System.out.println("transaction come in");
			System.out.println("transaction txHash " + transaction.getHash());
		});
	}

	private void replayFilter() {
		BigInteger startBlock = BigInteger.valueOf(2000000);
		BigInteger endBlock = BigInteger.valueOf(2010000);
		/**
		 * 遍历旧区块
		 */
		Subscription subscription = web3.replayBlocksObservable(DefaultBlockParameter.valueOf(startBlock), DefaultBlockParameter.valueOf(endBlock), false).subscribe(ethBlock -> {
			System.out.println("replay block");
			System.out.println(ethBlock.getBlock().getNumber());
		});

		/**
		 * 遍历旧交易
		 */
		Subscription subscription1 = web3.replayTransactionsObservable(DefaultBlockParameter.valueOf(startBlock), DefaultBlockParameter.valueOf(endBlock)).subscribe(transaction -> {
			System.out.println("replay transaction");
			System.out.println("txHash " + transaction.getHash());
		});
	}

	private void catchUpFilter() {
		BigInteger startBlock = BigInteger.valueOf(2000000);

		/**
		 * 遍历旧区块，监听新区块
		 */
		Subscription subscription = web3.catchUpToLatestAndSubscribeToNewBlocksObservable(DefaultBlockParameter.valueOf(startBlock), false).subscribe(block -> {
			System.out.println("block");
			System.out.println(block.getBlock().getNumber());
		});

		/**
		 * 遍历旧交易，监听新交易
		 */
		Subscription subscription2 = web3.catchUpToLatestAndSubscribeToNewTransactionsObservable(DefaultBlockParameter.valueOf(startBlock)).subscribe(tx -> {
			System.out.println("transaction");
			System.out.println(tx.getHash());
		});
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EthereumSubscription ethereumSubscription = new EthereumSubscription();
		// ethereumEvent.newBlockFilter();
		 ethereumSubscription.pendingTransactionObservable();
		// System.out.println(web3.toString());
		// Subscription subscription = web3.blockObservable(false).subscribe(tx -> {
		// System.out.println("observation tx:" + tx.getRawResponse());
		// });

	}

}
