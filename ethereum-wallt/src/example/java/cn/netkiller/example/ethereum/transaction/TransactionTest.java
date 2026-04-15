package cn.netkiller.example.ethereum.transaction;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.math.BigDecimal;

public class TransactionTest {
	public static void main(String[] args) throws Exception {
		Web3j web3j = Web3j.build(new HttpService("https://ropsten.infura.io/CsS9shwaAab0z7B4LP2d"));
		String toAddress = "0xf56b81a2bcb964D2806071e9Be4289A5559BB0fA";
		Credentials credentials = Credentials.create("16690967F2BADABF63ECCB022FFBD58537228D8AC970EC717E13A0670665BC3A");

		TransactionReceipt transactionReceipt = Transfer.sendFunds(web3j, credentials, toAddress, BigDecimal.valueOf(0.002), Convert.Unit.ETHER).send();

		System.out.println(transactionReceipt.getTransactionHash());
	}
}
