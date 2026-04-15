package cn.netkiller.example.ethereum.transaction;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.RawTransaction;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

public class RawTransactionTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		// 设置需要的矿工费
		BigInteger gasPrice = BigInteger.valueOf(18_000_000_000L);
		BigInteger gasLimit = BigInteger.valueOf(4_300_000);
		// System.out.println(gasPrice);

		// 连接 ropsten测试环境，这里使用的是infura这个客户端
		Web3j web3j = Web3j.build(new HttpService("https://ropsten.infura.io/CsS9shwaAab0z7B4LP2d"));
		// 转出账户地址
		String fromAddress = "0x22c57F0537414FD95b9f0f08f1E51d8b96F14029";
		// 接收账户地址
		String toAddress = "0xf56b81a2bcb964D2806071e9Be4289A5559BB0fA";
		// 转账人的私钥
		Credentials credentials = Credentials.create("166970EC6655853BDE13A07228DB022FF67717B3ECCADABF68AC090967F2BC3A");

		// Nonce 就相当于数据中PK主键，每次Nonce会做 +1 操作
		EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(fromAddress, DefaultBlockParameterName.LATEST).sendAsync().get();
		BigInteger nonce = ethGetTransactionCount.getTransactionCount();

		// 创建交易并转0.05个以太币
		BigInteger value = Convert.toWei("0.05", Convert.Unit.ETHER).toBigInteger();
		RawTransaction rawTransaction = RawTransaction.createEtherTransaction(nonce, gasPrice, gasLimit, toAddress, value);

		// 对交易做签名
		byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
		String hexValue = Numeric.toHexString(signedMessage);

		// 发送交易
		EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
		String transactionHash = ethSendTransaction.getTransactionHash();

		// 获得到transactionHash后就可以到以太坊的网站上查询这笔交易的状态了
		System.out.println("https://ropsten.etherscan.io/tx/" + transactionHash);

	}

}
