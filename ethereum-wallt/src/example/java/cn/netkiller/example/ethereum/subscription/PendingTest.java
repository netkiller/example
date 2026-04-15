package cn.netkiller.example.ethereum.subscription;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.ipc.UnixIpcService;

import rx.Subscription;

public class PendingTest {

	public PendingTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Subscription Starting...");
		// Web3j web3 = Web3j.build(new UnixIpcService("/Users/neo/Library/Ethereum/geth.ipc"));
		Web3j web3 = Web3j.build(new HttpService("https://ropsten.infura.io/CsS9shwaAab0z7B4LP2d"));
		Subscription subscription = web3.pendingTransactionObservable().subscribe(block -> {
			// System.out.println(block.toString());
			System.out.println("block number: " + block.getBlockHash());
		});
		// subscription.unsubscribe();
	}

}
