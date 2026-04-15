package cn.netkiller.example.ethereum.account;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert.Unit;

/**
 * Hello world!
 *
 */
public class Account {
	String url = "http://172.16.0.1:8545/";
	Web3j web3j = Web3j.build(new HttpService(url));

	public Account() {

	}

	public List<String> getAccountlist() {

		try {
			return web3j.ethAccounts().send().getAccounts();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getAccount(int index) {
		String account = null;

		try {
			account = web3j.ethAccounts().send().getAccounts().get(index);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return account;
	}

	
	public BigInteger getBalance(String account) throws IOException {

		EthGetBalance ethGetBalance = web3j.ethGetBalance(account, DefaultBlockParameterName.LATEST).send();
		BigInteger balance = ethGetBalance.getBalance();
		return balance;

	}

	public void transfer(String account, float coin)
			throws InterruptedException, IOException, TransactionException, Exception {
		String password = "";
		String walletfile = "/Users/neo/Downloads/UTC--2018-01-20T04-04-06.786586541Z--83fda0ba7e6cfa8d7319d78fa0e6b753a2bcb5a6";
		Credentials credentials = WalletUtils.loadCredentials(password, walletfile);
		TransactionReceipt transactionReceipt = Transfer
				.sendFunds(web3j, credentials, account, BigDecimal.valueOf(coin), Unit.ETHER).send();
		System.out.println(transactionReceipt.getStatus());
	}

	public static void main(String[] args) {
		Account account = new Account();
		System.out.println("------");
		System.out.println(account.getAccountlist());
		System.out.println("------");
		System.out.println(account.getAccount(0));
		System.out.println("------");

		try {
			System.out.println(account.getBalance(account.getAccount(0)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			System.out.println("------");
			System.out.println(account.getBalance(account.getAccount(2)));
			account.transfer("013b5e735e1b48421dd3de3b931d6f03e769e22b", 10);
			System.out.println(account.getBalance(account.getAccount(2)));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransactionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
