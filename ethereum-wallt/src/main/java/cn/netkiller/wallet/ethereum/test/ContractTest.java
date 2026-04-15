//package cn.netkiller.wallet.ethereum.test;
//
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.math.BigInteger;
//
//import org.web3j.crypto.Credentials;
//import org.web3j.protocol.Web3j;
//import org.web3j.protocol.core.methods.response.EthGasPrice;
//import org.web3j.protocol.core.methods.response.TransactionReceipt;
//import org.web3j.protocol.http.HttpService;
//import org.web3j.tx.Contract;
//import org.web3j.tx.ManagedTransaction;
//import org.web3j.tx.gas.DefaultGasProvider;
//import org.web3j.utils.Convert;
//
//import cn.netkiller.wallet.ethereum.contract.NetkillerAdvancedToken;
//
//public class ContractTest {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		try {
//			Web3j web3j = Web3j.build(new HttpService("https://ropsten.infura.io/CsS9shwaAab0z7B4LP2d"));
//
//			BigInteger gasPrice = BigInteger.ZERO;
//			BigInteger gasLimit = BigInteger.ZERO;
//			// EthGasPrice ethGasPrice = web3j.ethGasPrice().send();
//			// gasPrice = ethGasPrice.getGasPrice().multiply(BigInteger.valueOf(10));
//			// gasLimit = gasPrice.multiply(BigInteger.valueOf(900000));
//
//			gasPrice = DefaultGasProvider.GAS_PRICE;
//			gasLimit = DefaultGasProvider.GAS_LIMIT;
//
//			// gasPrice = ManagedTransaction.GAS_PRICE;
//
//			// gasLimit = Contract.GAS_LIMIT;
//
//			// gasPrice = Convert.toWei(BigDecimal.valueOf(1), Convert.Unit.GWEI).toBigInteger();
//			// gasLimit = BigInteger.valueOf(80400L);
//
//			System.out.println("gasPrice: " + gasPrice.toString());
//			System.out.println("gasLimit: " + gasLimit.toString());
//
//			// Credentials credentials = WalletUtils.loadCredentials("", walletfile);
//
//			// NetkillerAdvancedToken contract = NetkillerAdvancedToken.deploy(web3j, credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT).send();
//			String contractAddress = "0xc3360650b863f259691a8a8cd4c2d741aefd437a";
//			Credentials credentials = Credentials.create("166970EC717B3ECCADABF68AC066558537228DB022FFBDE13A06790967F2BC3A");
////			NetkillerAdvancedToken contract = NetkillerAdvancedToken.load(contractAddress, web3j, credentials, gasPrice, gasLimit);
//
//			System.out.println(contract.isValid());
//			System.out.println(contract.name().send());
//			System.out.println(contract.symbol().send());
//			System.out.println(contract.decimals().send());
//
//			System.out.println("---");
//			// String contractAddress = contract.getContractAddress();
//			System.out.println(contract.balanceOf("0xC0EB01Fda5343f4822BB48bFA23437d36AbA8a08").send());
//			System.out.println("---");
//			TransactionReceipt hash = contract.transfer("0xC0EB01Fda5343f4822BB48bFA23437d36AbA8a08", BigInteger.valueOf(1000)).send();
//			System.out.println(hash.getTransactionHash());
//			System.out.println("---");
//			System.out.println(contract.balanceOf("0xC0EB01Fda5343f4822BB48bFA23437d36AbA8a08").send());
//			//
//
//			if (contract.isValid()) {
//
//				System.out.println("---");
//			} else {
//				System.out.println("Deploy ERROR !!!");
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//}
