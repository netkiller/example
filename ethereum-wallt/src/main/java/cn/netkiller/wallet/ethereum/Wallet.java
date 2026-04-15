package cn.netkiller.wallet.ethereum;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.HDUtils;
import org.bitcoinj.crypto.MnemonicCode;
import org.bitcoinj.crypto.MnemonicException.MnemonicLengthException;
import org.bitcoinj.wallet.DeterministicKeyChain;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.UnreadableWalletException;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;

import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthEstimateGas;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

public class Wallet {
	private final String key = "sdFGYtjfGKHdfpo8";
	private Web3j web3;

	public Wallet() {

		try {
			// Web3j web3 = Web3j.build(new
			// HttpService("https://rinkeby.infura.io/CsS9shwaAab0z7B4LP2d"));
			web3 = Web3j.build(new HttpService("https://ropsten.infura.io/CsS9shwaAab0z7B4LP2d"));
			// Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().send();
			// System.out.println(web3ClientVersion.getWeb3ClientVersion());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public WalletResponse createMnemonic() throws UnreadableWalletException {
		SecureRandom secureRandom = new SecureRandom();
		long creationTimeSeconds = System.currentTimeMillis() / 1000;
		DeterministicSeed deterministicSeed = new DeterministicSeed(secureRandom, 128, "", creationTimeSeconds);
		List<String> mnemonicCode = deterministicSeed.getMnemonicCode();
		String mnemonic = String.join(" ", mnemonicCode);

		DeterministicSeed seed = new DeterministicSeed(mnemonic, null, "", creationTimeSeconds);
		DeterministicKeyChain chain = DeterministicKeyChain.builder().seed(seed).build();
		List<ChildNumber> keyPath = HDUtils.parsePath("M/44H/60H/0H/0/0");
		DeterministicKey key = chain.getKeyByPath(keyPath, true);
		BigInteger privKey = key.getPrivKey();
		String privateKey = privKey.toString(16);

		Credentials credentials = Credentials.create(privateKey);
		String address = credentials.getAddress();

		WalletResponse wallet = new WalletResponse(this.key);
		wallet.setAddress(address);
		wallet.setPrivateKey(privateKey);
		wallet.setMnemonic(mnemonic);
		return wallet;
	}

	public WalletResponse importFromMnemonic(String mnemonic) throws UnreadableWalletException {
		long creationTimeSeconds = System.currentTimeMillis() / 1000;
		DeterministicSeed seed = new DeterministicSeed(mnemonic, null, "", creationTimeSeconds);
		DeterministicKeyChain chain = DeterministicKeyChain.builder().seed(seed).build();
		List<ChildNumber> keyPath = HDUtils.parsePath("M/44H/60H/0H/0/0");
		DeterministicKey key = chain.getKeyByPath(keyPath, true);
		BigInteger privKey = key.getPrivKey();
		String privateKey = privKey.toString(16);

		Credentials credentials = Credentials.create(privateKey);
		String address = credentials.getAddress();

		WalletResponse wallet = new WalletResponse(this.key);
		wallet.setAddress(address);
		wallet.setPrivateKey(privateKey);
		wallet.setMnemonic(mnemonic);
		return wallet;

	}

	public WalletResponse createChineseMnemonic() throws UnreadableWalletException, IllegalArgumentException, IOException, MnemonicLengthException {

		SecureRandom secureRandom = new SecureRandom();
		byte[] entropy = new byte[16];
		secureRandom.nextBytes(entropy);
		long creationTimeSeconds = System.currentTimeMillis() / 1000;

		InputStream stream = Mnemonic.class.getResourceAsStream("/mnemonic/wordlist/chinese_simplified.txt");
		MnemonicCode chinese = new MnemonicCode(stream, null);

		final List<String> mnemonicCode = chinese.toMnemonic(entropy);
		String mnemonic = String.join(" ", mnemonicCode);

		DeterministicSeed seed = new DeterministicSeed(mnemonic, null, "", creationTimeSeconds);
		DeterministicKeyChain chain = DeterministicKeyChain.builder().seed(seed).build();
		List<ChildNumber> keyPath = HDUtils.parsePath("M/44H/60H/0H/0/0");
		DeterministicKey key = chain.getKeyByPath(keyPath, true);
		BigInteger privKey = key.getPrivKey();
		String privateKey = privKey.toString(16);

		Credentials credentials = Credentials.create(privateKey);
		String address = credentials.getAddress();

		WalletResponse wallet = new WalletResponse(this.key);
		wallet.setAddress(address);
		wallet.setPrivateKey(privateKey);
		wallet.setMnemonic(mnemonic);
		return wallet;
	}

	public WalletResponse createSecurityMnemonic(String passphrase, String birthday, String date) throws UnreadableWalletException {

		SecureRandom secureRandom = new SecureRandom();
		long creationTimeSeconds = System.currentTimeMillis() / 1000;
		DeterministicSeed deterministicSeed = new DeterministicSeed(secureRandom, 128, "", creationTimeSeconds);
		List<String> mnemonicCode = deterministicSeed.getMnemonicCode();
		String mnemonic = String.join(" ", mnemonicCode);

		DeterministicSeed seed = new DeterministicSeed(mnemonic, null, passphrase, creationTimeSeconds);
		DeterministicKeyChain chain = DeterministicKeyChain.builder().seed(seed).build();
		List<ChildNumber> keyPath = HDUtils.parsePath(String.format("M/44H/60H/%sH/0/%s", birthday, date));
		DeterministicKey key = chain.getKeyByPath(keyPath, true);
		BigInteger privKey = key.getPrivKey();
		String privateKey = privKey.toString(16);

		Credentials credentials = Credentials.create(privateKey);
		String address = credentials.getAddress();

		WalletResponse wallet = new WalletResponse(this.key);
		wallet.setAddress(address);
		wallet.setPrivateKey(privateKey);
		wallet.setMnemonic(mnemonic);
		return wallet;
	}

	public WalletResponse importFromSecurityMnemonic(String mnemonic, String passphrase, String birthday, String date) throws UnreadableWalletException {
		long creationTimeSeconds = System.currentTimeMillis() / 1000;
		DeterministicSeed seed = new DeterministicSeed(mnemonic, null, passphrase, creationTimeSeconds);
		DeterministicKeyChain chain = DeterministicKeyChain.builder().seed(seed).build();
		List<ChildNumber> keyPath = HDUtils.parsePath(String.format("M/44H/60H/%sH/0/%s", birthday, date));
		DeterministicKey key = chain.getKeyByPath(keyPath, true);
		BigInteger privKey = key.getPrivKey();
		String privateKey = privKey.toString(16);

		Credentials credentials = Credentials.create(privateKey);
		String address = credentials.getAddress();

		WalletResponse wallet = new WalletResponse(this.key);
		wallet.setAddress(address);
		wallet.setPrivateKey(privateKey);
		wallet.setMnemonic(mnemonic);
		return wallet;

	}

	public WalletResponse importFromPrivateKey(String privateKey) {
		Credentials credentials = Credentials.create(privateKey);
		String address = credentials.getAddress();

		WalletResponse wallet = new WalletResponse(this.key);
		wallet.setAddress(address);
		wallet.setPrivateKey(privateKey);

		return wallet;
	}

	public WalletResponse importFromKeystore(String json, String password) throws IOException, CipherException {

		File temp = File.createTempFile("keystore-", ".json");
		String keystore = temp.getAbsolutePath();

		FileWriter fw = new FileWriter(temp);
		fw.write(json);
		fw.close();

		Credentials credentials = WalletUtils.loadCredentials(password, temp.getAbsolutePath());
		String address = credentials.getAddress();
		String privateKey = credentials.getEcKeyPair().getPrivateKey().toString(16);
		WalletResponse wallet = new WalletResponse(this.key);
		wallet.setAddress(address);
		wallet.setPrivateKey(privateKey);
		wallet.setKeystore(keystore);
		return wallet;
	}

	public WalletResponse exportToPrivateKey(String privateKey) {
		WalletResponse wallet = new WalletResponse();
		return wallet;
	}

	public WalletResponse exportToKeystore(String password, String privateKey) throws CipherException, IOException {
		Credentials credentials = Credentials.create(privateKey);
		String address = credentials.getAddress();
		File temp = new File("/tmp");

		String filename = WalletUtils.generateWalletFile(password, credentials.getEcKeyPair(), temp, true);
		String keystore = "";
		try (BufferedReader br = new BufferedReader(new FileReader("/tmp/" + filename))) {

			String line;

			while ((line = br.readLine()) != null) {
				keystore += line;
				// System.out.println(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		WalletResponse wallet = new WalletResponse(this.key);
		wallet.setAddress(address);
		wallet.setPrivateKey(privateKey);
		wallet.setKeystore(keystore);
		return wallet;
	}

	public BigInteger getBalance(String account) throws IOException {

		EthGetBalance ethGetBalance = web3.ethGetBalance(account, DefaultBlockParameterName.LATEST).send();
		BigInteger balance = ethGetBalance.getBalance();
		return balance;

	}

	@SuppressWarnings("rawtypes")
	public BigInteger getTokenBalance(String account, String contractAddress) throws InterruptedException, ExecutionException, IOException {
		Function function = new Function("balanceOf", Arrays.<Type>asList(new Address(account)), Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
		}));

		String encodedFunction = FunctionEncoder.encode(function);
		// System.out.println(encodedFunction);

		EthCall response = web3.ethCall(Transaction.createEthCallTransaction(account, contractAddress, encodedFunction), DefaultBlockParameterName.LATEST).send();

		// System.out.println(response.getValue());

		List<Type> result = FunctionReturnDecoder.decode(response.getValue(), function.getOutputParameters());
		// System.out.println(result.size());
		// System.out.println(response.getRawResponse());
		// System.out.println((BigInteger) result.get(0).getValue());

		BigInteger balance = BigInteger.ZERO;
		if (result.size() == 1) {
			balance = (BigInteger) result.get(0).getValue();
		}
		return balance;
	}

	public String sendTransaction(String encryptPrivateKey, String toAddress, Double value) throws InterruptedException, IOException, TransactionException, Exception {

		AES aes = new AES(this.key);
		String privateKey = aes.decrypt(encryptPrivateKey);

		Credentials credentials = Credentials.create(privateKey);

		// TransactionReceipt transactionReceipt = Transfer.sendFunds(web3, credentials, toAddress, BigDecimal.valueOf(value), Convert.Unit.ETHER).send();
		TransactionReceipt transactionReceipt = Transfer.sendFunds(web3, credentials, toAddress, BigDecimal.valueOf(value), Convert.Unit.ETHER).sendAsync().get();

		return transactionReceipt.getTransactionHash();
	}

	public TransactionReceipt getTransactionReceipt(String transactionHash) throws InterruptedException, ExecutionException {
		EthGetTransactionReceipt ethGetTransactionReceipt = web3.ethGetTransactionReceipt(transactionHash).sendAsync().get();
		return ethGetTransactionReceipt.getResult();
	}

	public BigInteger getGasPrice() throws IOException {
		BigInteger gasPrice = BigInteger.ZERO;
		EthGasPrice ethGasPrice = web3.ethGasPrice().send();
		gasPrice = ethGasPrice.getGasPrice();
		return gasPrice;
	}

	public BigInteger getEstimateGas(Transaction transaction) {
		BigInteger gasLimit = BigInteger.ZERO;
		try {
			EthEstimateGas ethEstimateGas = web3.ethEstimateGas(transaction).send();
			gasLimit = ethEstimateGas.getAmountUsed();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return gasLimit;
	}

	public String sendTokenTransaction(String encryptPrivateKey, String toAddress, BigInteger amount, String contractAddress) throws InterruptedException, ExecutionException, IOException {

		AES aes = new AES(this.key);
		String privateKey = aes.decrypt(encryptPrivateKey);

		String txHash = null;
		Credentials credentials = Credentials.create(privateKey);
		String fromAddress = credentials.getAddress();

		Function function = new Function("transfer", Arrays.asList(new Address(toAddress), new Uint256(amount)), Arrays.asList(new TypeReference<Bool>() {
		}, new TypeReference<Bool>() {
		}));

		String encodedFunction = FunctionEncoder.encode(function);

		EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(fromAddress, DefaultBlockParameterName.PENDING).sendAsync().get();
		BigInteger nonce = ethGetTransactionCount.getTransactionCount();

		BigInteger gasPrice = this.getGasPrice();
		Transaction transaction = Transaction.createFunctionCallTransaction(fromAddress, nonce, gasPrice, Transaction.DEFAULT_GAS, contractAddress, encodedFunction);
		BigInteger gasLimit = this.getEstimateGas(transaction);

		RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit, contractAddress, encodedFunction);

		byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
		String signedTransactionData = Numeric.toHexString(signedMessage);
		EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(signedTransactionData).sendAsync().get();
		txHash = ethSendTransaction.getTransactionHash();

		return txHash;
	}

	public String encryptPrivateKey(String privateKey) {
		AES aes = new AES(this.key);
		return aes.encrypt(privateKey);
	}

	public String importAddressFromPrivateKey(String encryptPrivateKey) {
		AES aes = new AES(this.key);
		String privateKey = aes.decrypt(encryptPrivateKey);
		Credentials credentials = Credentials.create(privateKey);
		String fromAddress = credentials.getAddress();
		return fromAddress;
	}

	public class WalletResponse {

		private String address;
		private String privateKey;
		private String mnemonic;
		private String keystore;

		private AES aes = null;

		public WalletResponse() {
		}

		public WalletResponse(String key) {
			this.aes = new AES(key);
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getPrivateKey() {
			return privateKey;
		}

		public void setPrivateKey(String privateKey) {
			if (aes == null) {
				this.privateKey = privateKey;
			} else {
				this.privateKey = aes.encrypt(privateKey);
			}
		}

		public String getMnemonic() {
			return mnemonic;
		}

		public void setMnemonic(String mnemonic) {
			this.mnemonic = mnemonic;
		}

		public String getKeystore() {
			return keystore;
		}

		public void setKeystore(String keystore) {
			this.keystore = keystore;
		}

		@Override
		public String toString() {
			return "WalletResponse [address=" + address + ", privateKey=" + privateKey + ", mnemonic=" + mnemonic + ", keystore=" + keystore + "]";
		}

	}

	public static void main(String[] args) throws TransactionException, Exception {

		Wallet eth = new Wallet();
		// TransactionReceipt receipt = eth.getTransactionReceipt("0x7f5d9d1b6babea423f1647c33c95c6233eeb7d9cf8fcd8e5a170ad4ff6bc54fd");
		// System.out.println(receipt.getBlockNumber());
		String privateKey = eth.encryptPrivateKey("189E9468B93459DCBD44183A15853C3C60226041C5A5BC21CB2E18E148B95CD6");
		System.out.println("加密私钥：" + privateKey);

		WalletResponse token1 = eth.createMnemonic();
		System.out.println("普通钱包：" + token1.toString());
		WalletResponse token2 = eth.importFromMnemonic(token1.mnemonic);
		System.out.println("恢复普通钱包：" + token2.toString());

		WalletResponse chinese = eth.createChineseMnemonic();
		System.out.println("中文钱包：" + chinese.toString());

		WalletResponse chinese2 = eth.importFromMnemonic(chinese.mnemonic);
		System.out.println("恢复中文钱包：" + chinese2.toString());

		// WalletResponse bvw = eth.createSecurityMnemonic("password", "19800309", "20170601");
		// System.out.println("安全高防钱包:" + bvw.toString());
		//
		// WalletResponse token3 = eth.importFromSecurityMnemonic(bvw.mnemonic, "password", "19800309", "20170601");
		// System.out.println("恢复安全钱包：" + token3.toString());
		//
		// WalletResponse wallet1 = eth.importFromMnemonic("cover ensure daring swift tube crunch muffin snap strike benefit category chest", "password");
		// System.out.println(wallet1.toString());
		//
		// WalletResponse wallet1 = eth.importFromPrivateKey("1a75345d41b9540c625f698c0adab7933d50ecdd8b9aedf37f4160931222bb95");
		// System.out.println("导入私钥钱包：" + wallet1.toString());
		//
		// String json = "{\"address\":\"ce90d7410f260045b206acade280e0c4829cfc29\",\"crypto\":{\"cipher\":\"aes-128-ctr\",\"ciphertext\":\"a9bb9ad5fe99af4103899aede464536a95170bc3802b74a2322af31fd8cd2069\",\"cipherparams\":{\"iv\":\"488b6fb9901a828c9c702f6443ef77c3\"},\"kdf\":\"scrypt\",\"kdfparams\":{\"dklen\":32,\"n\":262144,\"p\":1,\"r\":8,\"salt\":\"104911661ca3e6249fddfe2791ea674d1f844bcb48cc29047379b09be78c17e1\"},\"mac\":\"1bff6a597df501aca9cefa42eb01c54195710ce822e1ab3ee1fc90de408a3491\"},\"id\":\"8718aa35-d667-4c96-b064-72f95ae235e1\",\"version\":3}";
		// WalletResponse wallet2 = eth.importFromKeystore(json, "12345678");
		// System.out.println("导入Keystore钱包：" + wallet2.toString());
		//
		// System.out.println("Export to keystore:");
		// WalletResponse keystore = eth.exportToKeystore("123456789", "c01127bd81636483c58472cd000e19fc321c62d5cdf58d8a39dea87be87a3f53");
		// System.out.println("导出 Keystore：" + keystore.toString());
		//
		// BigInteger balance = eth.getBalance("0xf56b81a2bcb964D2806071e9Be4289A5559BB0fA");
		// System.out.println("获取 Balance：" + balance);
		//
		// String hash = eth.sendTransaction(privateKey, "0xf56b81a2bcb964D2806071e9Be4289A5559BB0fA", 0.0001);
		// System.out.println("ETH转账：" + hash);
		//
		// BigInteger tokenBalance = eth.getTokenBalance("0xb94054c174995ae2a9e7fcf6c7924635fba8ecf7", "0x70682386d0dE84B1e549DC3c4305CCB2D261b2a8");
		// System.out.println("代币余额：" + tokenBalance);
		//
		// BigInteger tokenBalance1 = eth.getTokenBalance("0xCdF0253d8362d6c3334c8F28A6BFd74c90d03d92", "0x70682386d0dE84B1e549DC3c4305CCB2D261b2a8");
		// System.out.println("转账前：" + tokenBalance1);
		//
		// BigInteger amount = Convert.toWei(BigDecimal.valueOf(1), Convert.Unit.ETHER).toBigInteger();
		// // BigInteger amount = BigInteger.valueOf(100000);
		// String txhash = eth.sendTokenTransaction(privateKey, "0xCdF0253d8362d6c3334c8F28A6BFd74c90d03d92", amount, "0x70682386d0dE84B1e549DC3c4305CCB2D261b2a8");
		// System.out.println("交易HASH：" + txhash);
		// //
		// tokenBalance1 = eth.getTokenBalance("0xCdF0253d8362d6c3334c8F28A6BFd74c90d03d92", "0x70682386d0dE84B1e549DC3c4305CCB2D261b2a8");
		// System.out.println("转账后：" + tokenBalance1);

	}
}
