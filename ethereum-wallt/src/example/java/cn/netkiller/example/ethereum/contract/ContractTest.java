package cn.netkiller.example.ethereum.contract;

import java.math.BigInteger;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;


public class ContractTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		String walletfile = "/Users/neo/Downloads/UTC--2018-01-20T04-04-06.786586541Z--83fda0ba7e6cfa8d7319d78fa0e6b753a2bcb5a6";

		Web3j web3j = Web3j.build(new HttpService("http://172.16.0.1:8545"));
		Credentials credentials = WalletUtils.loadCredentials("", walletfile);

		Netkiller contract = Netkiller.deploy(web3j, credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT)
				.send();

		System.out.println(contract.isValid());
		if (contract.isValid()) {
			System.out.println("---");
			String contractAddress = contract.getContractAddress();
			System.out.println(contractAddress);
			System.out.println("---");
			String result = contract.getName().send();
			System.out.println(result);

			contract.setName("Netkiller").send();
			System.out.println(contract.getName().send());
			System.out.println("---");
			contract.setNum(BigInteger.valueOf(8)).send();
			System.out.println(contract.addNum(BigInteger.valueOf(8)).send());
			System.out.println("---");
		} else {
			System.out.println("Deploy ERROR !!!");
		}
	}

}
