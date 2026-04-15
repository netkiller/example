package cn.netkiller.wallet.ethereum.test;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.web3j.protocol.core.methods.response.TransactionReceipt;

import cn.netkiller.wallet.ethereum.Ethereum;

public class TokenTest {

	private Ethereum token;

	public TokenTest() {
		token = new Ethereum("https://ropsten.infura.io/v3/ee07e33cb6414781a72deaf3b303ca3b");
	}

	public void batch() throws InterruptedException, ExecutionException, IOException {
		token.setPrivateKey("189E9468B93459DCBD44183A15853C3C60226041C5A5BC21CB2E18E148B95CD6");
		token.setContractAddress("0x100d4759cb2c50f10e6c02bb06f308153719ac7b");
		List<String> toAddress = Arrays.asList("0xf56b81a2bcb964D2806071e9Be4289A5559BB0fA", "0x997e5CA600E19447D0B82aFBf9c7F00De2B39B16", "0x538b392D57d867A57eE8Eed05737cB08B4691302", "0xD5EEaE04932DbC2E65B948A76A6Cdfd44323A5Dd");
		String batchhash = token.transferBatch(toAddress, BigInteger.valueOf(1000));
		System.out.println("批量转账：" + batchhash);
	}

	public void erc20() throws InterruptedException, ExecutionException, IOException {

		Ethereum token1 = new Ethereum("https://ropsten.infura.io/v3/ee07e33cb6414781a72deaf3b303ca3b");
		// token.setContractAddress("0xC3360650B863f259691A8a8cD4C2d741AEfd437A");
		// token.setContractAddress("0x70682386d0dE84B1e549DC3c4305CCB2D261b2a8"); // Neo #
		// token.setContractAddress("0x5bb9fc41e90815246b9068639a6840409a857fbc"); // NKC #
		// token.setPrivateKey("166970EC717B3ECCADABF68AC066558537228DB022FFBDE13A06790967F2BC3A");
		// token.setPrivateKey("189E9468B93459DCBD44183A15853C3C60226041C5A5BC21CB2E18E148B95CD6");

		token.setContractAddress("0x5bb9fc41e90815246b9068639a6840409a857fbc");
		token.setPrivateKey("44F0059868FBE15586EBA60AF06AC455C12C6A1576D5985DD39D21F6C3D17123");

		String owner = token.getOwner();
		System.out.println("代币创建者：" + owner);

		String name = token.getName();
		System.out.println("代币名称：" + name);

		String symbol = token.getSymbol();
		System.out.println("代币符号：" + symbol);

		int decimal = token.getDecimals();
		System.out.println("小数位数：" + decimal);

		BigInteger totalSupply = token.getTotalSupply();
		System.out.println("发行总量：" + totalSupply);

		BigInteger tokenBalance = token.getTokenBalance("0xB94054c174995AE2A9E7fcf6c7924635FBa8ECF7");
		// BigInteger tokenBalance = token.getTokenBalance("0x6F56648fbD2306f843442f8dC61d5C8861Fac7C9");
		System.out.println("代币余额:" + tokenBalance);

		BigDecimal val = token.toBigDecimal(tokenBalance, decimal);
		System.out.println("格式化后：" + val);

		String transactionHash = token.sendTokenTransaction("0xb3cedc76e75fcd278c988b22963c2f35c99c10b7", BigInteger.valueOf(10));
		System.out.println("代币转账：" + transactionHash);

		TransactionReceipt transactionReceipt = token.getTransactionReceipt(transactionHash);
		System.out.println("转账状态：" + transactionReceipt.toString());

		String hash = token.setApprove("0xCdF0253d8362d6c3334c8F28A6BFd74c90d03d92", BigInteger.valueOf(100));
		System.out.println("设置授信：" + hash);

		BigInteger value = token.getAllowance("0x22c57F0537414FD95b9f0f08f1E51d8b96F14029", "0xCdF0253d8362d6c3334c8F28A6BFd74c90d03d92");
		System.out.println("查询授信：" + value);

		System.out.println("授信转出：" + token1.sendTransactionFrom("0x22c57F0537414FD95b9f0f08f1E51d8b96F14029", "0xCdF0253d8362d6c3334c8F28A6BFd74c90d03d92", BigInteger.valueOf(20)));

		System.out.println(token1.getAllowance("0x22c57F0537414FD95b9f0f08f1E51d8b96F14029", "0xCdF0253d8362d6c3334c8F28A6BFd74c90d03d92"));

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// System.out.println(Transaction.DEFAULT_GAS);
		// System.out.println(Contract.GAS_LIMIT);

		try {

			TokenTest tt = new TokenTest();
			tt.batch();

		} catch (InterruptedException | ExecutionException | IOException e) {
			e.printStackTrace();

		}

	}

}
