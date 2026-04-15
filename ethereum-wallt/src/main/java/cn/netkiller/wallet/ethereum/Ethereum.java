package cn.netkiller.wallet.ethereum;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Uint;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
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

public class Ethereum {
	private Web3j web3;
	private String contractAddress = null;
	private String privateKey = null;
	// private BigInteger gasLimit = BigInteger.ZERO;
	//
	private String fromAddress;

	public Ethereum() {
		// TODO Auto-generated constructor stub
		web3 = Web3j.build(new HttpService("https://localhost:8545"));
	}

	public Ethereum(String url) {
		web3 = Web3j.build(new HttpService(url));
	}

	public Ethereum(Web3j web3j) {
		web3 = web3j;
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

	public TransactionReceipt getTransactionReceipt(String transactionHash) throws InterruptedException, ExecutionException {
		EthGetTransactionReceipt ethGetTransactionReceipt = web3.ethGetTransactionReceipt(transactionHash).sendAsync().get();
		return ethGetTransactionReceipt.getResult();
	}

	public BigInteger getBalance(String account) throws IOException {

		EthGetBalance ethGetBalance = web3.ethGetBalance(account, DefaultBlockParameterName.LATEST).send();
		BigInteger balance = ethGetBalance.getBalance();
		return balance;

	}
	public String sendTransaction(String toAddress, Double value) throws InterruptedException, IOException, TransactionException, Exception {

		Credentials credentials = Credentials.create(this.privateKey);

		TransactionReceipt transactionReceipt = Transfer.sendFunds(web3, credentials, toAddress, BigDecimal.valueOf(value), Convert.Unit.ETHER).sendAsync().get();

		return transactionReceipt.getTransactionHash();
	}
	public String sendTransaction(String privateKey, String toAddress, Double value) throws InterruptedException, IOException, TransactionException, Exception {

		Credentials credentials = Credentials.create(privateKey);

		TransactionReceipt transactionReceipt = Transfer.sendFunds(web3, credentials, toAddress, BigDecimal.valueOf(value), Convert.Unit.ETHER).sendAsync().get();

		return transactionReceipt.getTransactionHash();
	}

	public String send(String privateKey, String to, BigInteger amount) throws IOException, InterruptedException, ExecutionException {

		Credentials credentials = Credentials.create(privateKey);
		String from = credentials.getAddress();

		BigInteger value = Convert.toWei(amount.toString(), Convert.Unit.ETHER).toBigInteger();

		EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(from, DefaultBlockParameterName.LATEST).send();
		BigInteger nonce = ethGetTransactionCount.getTransactionCount();

		Transaction transaction = Transaction.createEtherTransaction(from, nonce, this.getGasPrice(), Transaction.DEFAULT_GAS, to, value);
		BigInteger gasLimit = this.getEstimateGas(transaction);

		RawTransaction rawTransaction = RawTransaction.createEtherTransaction(nonce, this.getGasPrice(), gasLimit, to, value);

		// 签名Transaction，这里要对交易做签名
		byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
		String hexValue = Numeric.toHexString(signedMessage);

		// 发送交易
		EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(hexValue).sendAsync().get();
		String transactionHash = ethSendTransaction.getTransactionHash();

		System.out.println(transactionHash);
		return transactionHash;
	}

	

	// private static void decodeMessage(String signedData) {
	//
	// try {
	// System.out.println(signedData);
	// System.out.println("解密 start " + System.currentTimeMillis());
	// RlpList rlpList = RlpDecoder.decode(Numeric.hexStringToByteArray(signedData));
	// List<RlpType> values = ((RlpList) rlpList.getValues().get(0)).getValues();
	// BigInteger nonce = Numeric.toBigInt(((RlpString) values.get(0)).getBytes());
	// BigInteger gasPrice = Numeric.toBigInt(((RlpString) values.get(1)).getBytes());
	// BigInteger gasLimit = Numeric.toBigInt(((RlpString) values.get(2)).getBytes());
	// String to = Numeric.toHexString(((RlpString) values.get(3)).getBytes());
	// BigInteger value = Numeric.toBigInt(((RlpString) values.get(4)).getBytes());
	// String data = Numeric.toHexString(((RlpString) values.get(5)).getBytes());
	// RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit, to, value, data);
	// RlpString v = (RlpString) values.get(6);
	// RlpString r = (RlpString) values.get(7);
	// RlpString s = (RlpString) values.get(8);
	// Sign.SignatureData signatureData = new Sign.SignatureData(v.getBytes()[0], r.getBytes(), s.getBytes());
	// BigInteger pubKey = Sign.signedMessageToKey(TransactionEncoder.encode(rawTransaction), signatureData);
	//// System.out.println("publicKey " + pubKey.toString(16));
	//// String address = Numeric.prependHexPrefix(Keys.getAddress(pubKey));
	//// System.out.println("address " + address);
	//// System.out.println("解密 end " + System.currentTimeMillis());
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	// ======================================

	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {

		Credentials credentials = Credentials.create(privateKey);
		String fromAddress = credentials.getAddress();
		this.setFromAddress(fromAddress);

		this.privateKey = privateKey;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	@SuppressWarnings("rawtypes")
	public String getOwner() {
		String owner = null;
		Function function = new Function("owner", Arrays.<Type>asList(), Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
		}));

		String encodedFunction = FunctionEncoder.encode(function);
		Transaction transaction = Transaction.createEthCallTransaction(null, this.contractAddress, encodedFunction);

		EthCall ethCall;
		try {
			ethCall = web3.ethCall(transaction, DefaultBlockParameterName.LATEST).sendAsync().get();
			List<Type> results = FunctionReturnDecoder.decode(ethCall.getValue(), function.getOutputParameters());
			owner = results.get(0).getValue().toString();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return owner;
	}

	@SuppressWarnings("rawtypes")
	public String getName() {
		String name = null;
		/*
		 * String methodName = "name"; List<Type> inputParameters = new ArrayList<>(); List<TypeReference<?>> outputParameters = new ArrayList<>();
		 * 
		 * TypeReference<Utf8String> typeReference = new TypeReference<Utf8String>() { }; outputParameters.add(typeReference);
		 * 
		 * Function function = new Function(methodName, inputParameters, outputParameters);
		 */
		Function function = new Function("name", Arrays.<Type>asList(), Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
		}));

		String encodedFunction = FunctionEncoder.encode(function);
		Transaction transaction = Transaction.createEthCallTransaction(null, this.contractAddress, encodedFunction);

		EthCall ethCall;
		try {
			ethCall = web3.ethCall(transaction, DefaultBlockParameterName.LATEST).sendAsync().get();
			List<Type> results = FunctionReturnDecoder.decode(ethCall.getValue(), function.getOutputParameters());
			if (results.size() > 0) {
				name = results.get(0).getValue().toString();
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return name;
	}

	@SuppressWarnings("rawtypes")
	public String getSymbol() {
		String symbol = null;
		/*
		 * String methodName = "symbol"; List<Type> inputParameters = new ArrayList<>(); List<TypeReference<?>> outputParameters = new ArrayList<>();
		 * 
		 * TypeReference<Utf8String> typeReference = new TypeReference<Utf8String>() { }; outputParameters.add(typeReference);
		 * 
		 * Function function = new Function(methodName, inputParameters, outputParameters);
		 */
		Function function = new Function("symbol", Arrays.<Type>asList(), Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
		}));

		String encodedFunction = FunctionEncoder.encode(function);
		Transaction transaction = Transaction.createEthCallTransaction(null, this.contractAddress, encodedFunction);

		EthCall ethCall;
		try {
			ethCall = web3.ethCall(transaction, DefaultBlockParameterName.LATEST).sendAsync().get();
			List<Type> results = FunctionReturnDecoder.decode(ethCall.getValue(), function.getOutputParameters());
			if (results.size() > 0) {
				symbol = results.get(0).getValue().toString();
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return symbol;
	}

	@SuppressWarnings("rawtypes")
	public int getDecimals() {
		int decimal = 0;
		/*
		 * String methodName = "decimals"; List<Type> inputParameters = new ArrayList<>(); List<TypeReference<?>> outputParameters = new ArrayList<>();
		 * 
		 * TypeReference<Uint> typeReference = new TypeReference<Uint>() { }; outputParameters.add(typeReference);
		 * 
		 * Function function = new Function(methodName, inputParameters, outputParameters);
		 */
		Function function = new Function("decimals", Arrays.<Type>asList(), Arrays.<TypeReference<?>>asList(new TypeReference<Uint>() {
		}));
		String encodedFunction = FunctionEncoder.encode(function);
		Transaction transaction = Transaction.createEthCallTransaction(null, this.contractAddress, encodedFunction);

		EthCall ethCall;
		try {
			ethCall = web3.ethCall(transaction, DefaultBlockParameterName.LATEST).sendAsync().get();
			List<Type> results = FunctionReturnDecoder.decode(ethCall.getValue(), function.getOutputParameters());
			if (results.size() > 0) {
				decimal = Integer.parseInt(results.get(0).getValue().toString());
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return decimal;
	}

	@SuppressWarnings("rawtypes")
	public BigInteger getTotalSupply() {
		BigInteger totalSupply = BigInteger.ZERO;

		/*
		 * String methodName = "totalSupply"; List<Type> inputParameters = new ArrayList<>(); List<TypeReference<?>> outputParameters = new ArrayList<>();
		 * 
		 * TypeReference<Uint256> typeReference = new TypeReference<Uint256>() { }; outputParameters.add(typeReference);
		 * 
		 * Function function = new Function(methodName, inputParameters, outputParameters);
		 */
		Function function = new Function("totalSupply", Arrays.<Type>asList(), Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
		}));
		String encodedFunction = FunctionEncoder.encode(function);
		Transaction transaction = Transaction.createEthCallTransaction(null, this.contractAddress, encodedFunction);

		EthCall ethCall;
		try {
			ethCall = web3.ethCall(transaction, DefaultBlockParameterName.LATEST).sendAsync().get();
			List<Type> results = FunctionReturnDecoder.decode(ethCall.getValue(), function.getOutputParameters());
			totalSupply = (BigInteger) results.get(0).getValue();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return totalSupply;
	}

	@SuppressWarnings("rawtypes")
	public BigInteger getTokenBalance(String account) throws InterruptedException, ExecutionException, IOException {
		Function function = new Function("balanceOf", Arrays.<Type>asList(new Address(account)), Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
		}));

		String encodedFunction = FunctionEncoder.encode(function);
		// System.out.println(encodedFunction);

		EthCall response = web3.ethCall(Transaction.createEthCallTransaction(account, this.contractAddress, encodedFunction), DefaultBlockParameterName.LATEST).send();

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

	@SuppressWarnings("rawtypes")
	public BigInteger getAllowance(String _owner, String _spender) throws InterruptedException, ExecutionException {
		Function function = new Function("allowance", Arrays.asList(new Address(_owner), new Address(_spender)), Arrays.asList(new TypeReference<Uint256>() {
		}));
		String encodedFunction = FunctionEncoder.encode(function);

		EthCall response = web3.ethCall(Transaction.createEthCallTransaction(_owner, this.contractAddress, encodedFunction), DefaultBlockParameterName.LATEST).sendAsync().get();
		List<Type> result = FunctionReturnDecoder.decode(response.getValue(), function.getOutputParameters());
		BigInteger balance = BigInteger.ZERO;
		if (result.size() == 1) {
			balance = (BigInteger) result.get(0).getValue();
		}
		return balance;
	}

	public String sendTokenTransaction(String toAddress, BigInteger amount) throws InterruptedException, ExecutionException, IOException {
		String txHash = null;
		Credentials credentials = Credentials.create(this.privateKey);
		String fromAddress = credentials.getAddress();

		Function function = new Function("transfer", Arrays.asList(new Address(toAddress), new Uint256(amount)), Arrays.asList(new TypeReference<Bool>() {
		}, new TypeReference<Bool>() {
		}));

		String encodedFunction = FunctionEncoder.encode(function);

		EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(fromAddress, DefaultBlockParameterName.PENDING).send();
		BigInteger nonce = ethGetTransactionCount.getTransactionCount();

		BigInteger gasPrice = BigInteger.ZERO;
		gasPrice = this.getGasPrice();

		Transaction transaction = Transaction.createFunctionCallTransaction(fromAddress, nonce, gasPrice, Transaction.DEFAULT_GAS, this.contractAddress, encodedFunction);
		BigInteger gasLimit = this.getEstimateGas(transaction);
		System.out.println("评估GAS：" + gasLimit);

		// BigInteger gasLimit = BigInteger.valueOf(63000L);

		RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit, this.contractAddress, encodedFunction);

		byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
		String signedTransactionData = Numeric.toHexString(signedMessage);
		EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(signedTransactionData).sendAsync().get();
		txHash = ethSendTransaction.getTransactionHash();

		return txHash;
	}

	public String sendTokenTransaction(String privateKey, String toAddress, BigInteger amount, String contractAddress) throws Exception {
		String txHash = null;
		Credentials credentials = Credentials.create(privateKey);
		String fromAddress = credentials.getAddress();

		Function function = new Function("transfer", Arrays.asList(new Address(toAddress), new Uint256(amount)), Arrays.asList(new TypeReference<Bool>() {
		}, new TypeReference<Bool>() {
		}));

		String encodedFunction = FunctionEncoder.encode(function);

		EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(fromAddress, DefaultBlockParameterName.PENDING).send();
		BigInteger nonce = ethGetTransactionCount.getTransactionCount();

		//BigInteger gasPrice = this.getGasPrice();
		BigInteger gasPrice = BigInteger.valueOf(10000000000L);
		
		//Transaction transaction = Transaction.createFunctionCallTransaction(fromAddress, nonce, gasPrice, Transaction.DEFAULT_GAS, contractAddress, encodedFunction);
		//BigInteger gasLimit = this.getEstimateGas(transaction);
		BigInteger gasLimit = BigInteger.valueOf(100000);

		RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit, contractAddress, encodedFunction);

		byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
		String signedTransactionData = Numeric.toHexString(signedMessage);
		EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(signedTransactionData).send();
		txHash = ethSendTransaction.getTransactionHash();

		return txHash;
	}
	
	public String setApprove(String _spender, BigInteger _amount) throws InterruptedException, ExecutionException, IOException {
		String txHash = null;
		Credentials credentials = Credentials.create(this.privateKey);
		String fromAddress = credentials.getAddress();

		Function function = new Function("approve", Arrays.asList(new Address(_spender), new Uint256(_amount)), Arrays.asList(new TypeReference<Bool>() {
		}, new TypeReference<Bool>() {
		}));

		String encodedFunction = FunctionEncoder.encode(function);

		EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(fromAddress, DefaultBlockParameterName.PENDING).sendAsync().get();
		BigInteger nonce = ethGetTransactionCount.getTransactionCount();

		BigInteger gasPrice = BigInteger.ZERO;
		gasPrice = this.getGasPrice();

		Transaction transaction = Transaction.createFunctionCallTransaction(fromAddress, nonce, gasPrice, Transaction.DEFAULT_GAS, this.contractAddress, encodedFunction);
		BigInteger gasLimit = this.getEstimateGas(transaction);

		RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit, this.contractAddress, encodedFunction);

		byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
		String signedTransactionData = Numeric.toHexString(signedMessage);
		EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(signedTransactionData).sendAsync().get();
		txHash = ethSendTransaction.getTransactionHash();

		return txHash;
	}

	public String sendTransactionFrom(String _from, String _to, BigInteger _amount) throws InterruptedException, ExecutionException, IOException {
		String txHash = null;
		Credentials credentials = Credentials.create(this.privateKey);
		String fromAddress = credentials.getAddress();

		Function function = new Function("transferFrom", Arrays.asList(new Address(_from), new Address(_to), new Uint256(_amount)), Arrays.asList(new TypeReference<Bool>() {
		}, new TypeReference<Bool>() {
		}));

		String encodedFunction = FunctionEncoder.encode(function);

		EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(fromAddress, DefaultBlockParameterName.PENDING).send();
		BigInteger nonce = ethGetTransactionCount.getTransactionCount();

		BigInteger gasPrice = BigInteger.ZERO;
		gasPrice = this.getGasPrice();

		Transaction transaction = Transaction.createFunctionCallTransaction(fromAddress, nonce, gasPrice, Transaction.DEFAULT_GAS, this.contractAddress, encodedFunction);
		BigInteger gasLimit = this.getEstimateGas(transaction);

		RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit, this.contractAddress, encodedFunction);

		byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
		String signedTransactionData = Numeric.toHexString(signedMessage);
		EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(signedTransactionData).sendAsync().get();
		txHash = ethSendTransaction.getTransactionHash();

		return txHash;
	}

	public String transferBatch(List<String> toAddress, BigInteger amount) throws InterruptedException, ExecutionException, IOException {
		String txHash = null;
		Credentials credentials = Credentials.create(this.privateKey);
		String fromAddress = credentials.getAddress();

		// BigInteger gasLimit = BigInteger.ZERO;

		// final Function function = new Function("transferBatch", Arrays.asList(new DynamicArray<Address>(org.web3j.abi.Utils.typeMap(toAddress, Address.class)), new Uint256(amount)), Collections.<TypeReference<?>>emptyList());

		final Function function = new Function("transferBatch", Arrays.asList(new DynamicArray<Address>(org.web3j.abi.Utils.typeMap(toAddress, Address.class)), new Uint256(amount)), Arrays.asList(new TypeReference<Bool>() {
		}, new TypeReference<Bool>() {
		}));

		String encodedFunction = FunctionEncoder.encode(function);

		EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(fromAddress, DefaultBlockParameterName.PENDING).send();
		BigInteger nonce = ethGetTransactionCount.getTransactionCount();

		BigInteger gasPrice = BigInteger.ZERO;
		gasPrice = this.getGasPrice();

		// if (gas == null) {

		Transaction transaction = Transaction.createFunctionCallTransaction(fromAddress, nonce, gasPrice, Transaction.DEFAULT_GAS, this.contractAddress, encodedFunction);
		BigInteger gasLimit = this.getEstimateGas(transaction);
		// gasLimit = this.getEstimateGas(transaction);
		// System.out.println("评估GAS：" + gasLimit);
		// } else {
		// gasLimit = gas;
		// }

		RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit, this.contractAddress, encodedFunction);

		byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
		String signedTransactionData = Numeric.toHexString(signedMessage);
		System.out.println(signedTransactionData);
		EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(signedTransactionData).sendAsync().get();

		txHash = ethSendTransaction.getTransactionHash();

		return txHash;
	}

	public String toEth(BigInteger amount) {
		return Convert.fromWei(amount.toString(), Convert.Unit.ETHER).toPlainString();
	}

	public BigDecimal toBigDecimal(BigInteger balance, int decimal) {
		BigDecimal value = new BigDecimal(balance);
		value = value.divide(BigDecimal.TEN.pow(decimal));
		return value;
	}

	public BigInteger toBigInteger(BigDecimal value, int decimal) {
		// BigDecimal balance = new BigDecimal(value);
		BigInteger amount = value.multiply(BigDecimal.TEN.pow(decimal)).setScale(0, RoundingMode.DOWN).toBigInteger();
		return amount;

	}

	public static void main(String[] args) {

		// TODO Auto-generated method stub

		// Ethereum eth = new Ethereum();
		// EthTransaction transaction = web3.ethGetTransactionByHash("0x7f5d9d1b6babea423f1647c33c95c6233eeb7d9cf8fcd8e5a170ad4ff6bc54fd").sendAsync().get();
		// System.out.println(transaction.getResult().toString());

		// TransactionReceipt receipt = eth.getTransactionReceipt("0x7f5d9d1b6babea423f1647c33c95c6233eeb7d9cf8fcd8e5a170ad4ff6bc54fd");
		// System.out.println(receipt.getBlockNumber());

	}

}
