package cn.netkiller.example.bitcoin;

import java.math.BigInteger;

import org.bitcoinj.core.Address;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.TestNet3Params;

public class PrivateKey {

	public PrivateKey() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String privateKey = "114975678953706493006078288858395390389542766334692100030647313125562885805365";
		BigInteger privateKeyBigInteger = new BigInteger(privateKey);
		NetworkParameters params = TestNet3Params.get();
		// 构造初始拥有者key
		ECKey ecKey = ECKey.fromPrivate(privateKeyBigInteger);
		Address address = ecKey.toAddress(params);
		System.out.println(address.toString());
	}

}
