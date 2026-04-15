package cn.netkiller.example.bitcoin;

import java.io.File;

import org.bitcoinj.core.Address;
import org.bitcoinj.core.BlockChain;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.PeerGroup;
import org.bitcoinj.net.discovery.DnsDiscovery;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.store.BlockStoreException;
import org.bitcoinj.store.SPVBlockStore;
import org.bitcoinj.wallet.Wallet;

public class AddressTest {

	public AddressTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws BlockStoreException {
		// TODO Auto-generated method stub
		NetworkParameters params = TestNet3Params.get();

		ECKey key = new ECKey();
		System.out.println("We created a new key:\n" + key);

		Address addressFromKey = key.toAddress(params);
		System.out.println("Public Address generated: " + addressFromKey);

		System.out.println("Private key is: " + key.getPrivateKeyEncoded(params).toString());

		Wallet wallet = new Wallet(params);
		wallet.importKey(key);

		File blockFile = new File("/tmp/bitcoin-blocks");
		SPVBlockStore blockStore = new SPVBlockStore(params, blockFile);

		BlockChain blockChain = new BlockChain(params, wallet, blockStore);
		PeerGroup peerGroup = new PeerGroup(params, blockChain);
		peerGroup.addPeerDiscovery(new DnsDiscovery(params));
		peerGroup.addWallet(wallet);

		System.out.println("Start peer group");
		peerGroup.start();

		System.out.println("Downloading block chain");
		peerGroup.downloadBlockChain();
		System.out.println("Block chain downloaded");
	}

}
