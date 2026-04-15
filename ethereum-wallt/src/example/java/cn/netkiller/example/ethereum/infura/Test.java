package cn.netkiller.example.ethereum.infura;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.HDUtils;
import org.bitcoinj.wallet.DeterministicKeyChain;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.UnreadableWalletException;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

public class Test {
	private Web3j web3;
	private String mnemonic = "denial initial biology deposit brush hotel jealous theme basic service flee ethics";
	private String passphrase = "netkiller";

	public Test() {
		// TODO Auto-generated constructor stub
		web3 = Web3j.build(new HttpService("https://ropsten.infura.io/CsS9shwaAab0z7B4LP2d"));
		// web3 = Web3j.build(new
		// HttpService("https://mainnet.infura.io/CsS9shwaAab0z7B4LP2d"));
		// Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().send();
		// System.out.println(web3ClientVersion.getWeb3ClientVersion());
	}

	public String getPrivateKey(String index) throws UnreadableWalletException {

		long creationTimeSeconds = System.currentTimeMillis() / 1000;
		DeterministicSeed seed = new DeterministicSeed(this.mnemonic, null, this.passphrase, creationTimeSeconds);
		DeterministicKeyChain chain = DeterministicKeyChain.builder().seed(seed).build();
		List<ChildNumber> keyPath = HDUtils.parsePath(String.format("M/44H/60H/10H/0/%s", index));
		DeterministicKey key = chain.getKeyByPath(keyPath, true);
		BigInteger privKey = key.getPrivKey();
		return privKey.toString(16);
	}

	public String getAddress(String privateKey) {

		Credentials credentials = Credentials.create(privateKey);
		String address = credentials.getAddress();
		return address;
	}

	public BigInteger getBalance(String account) throws IOException {

		EthGetBalance ethGetBalance = web3.ethGetBalance(account, DefaultBlockParameterName.LATEST).send();
		BigInteger balance = ethGetBalance.getBalance();
		return balance;

	}

	public String transaction(String privateKey, String toAddress, Double value) throws InterruptedException, IOException, TransactionException, Exception {

		Credentials credentials = Credentials.create(privateKey);

		TransactionReceipt transactionReceipt = Transfer.sendFunds(web3, credentials, toAddress, BigDecimal.valueOf(value), Convert.Unit.ETHER).send();

		return transactionReceipt.getTransactionHash();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Test test = new Test();
			String privateKey = test.getPrivateKey("0");

			String address = test.getAddress(privateKey);
			System.out.println(address);
			System.out.println(privateKey);

			BigInteger balance = test.getBalance(address);
			System.out.println(balance);

			test.transaction(privateKey, "0xCdF0253d8362d6c3334c8F28A6BFd74c90d03d92", 0.0005);

			System.out.println(test.getBalance(address));
			System.out.println(test.getBalance("0xCdF0253d8362d6c3334c8F28A6BFd74c90d03d92"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
