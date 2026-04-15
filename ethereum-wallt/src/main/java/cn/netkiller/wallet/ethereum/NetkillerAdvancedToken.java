package cn.netkiller.wallet.ethereum;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Utf8String;

public class NetkillerAdvancedToken {

	public NetkillerAdvancedToken(String contractAddress, String privateKey) throws IOException {
//		super(contractAddress, privateKey);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Function function = new Function("setName", Arrays.asList(new Utf8String("Netkiller")), Collections.emptyList());

		String encodedFunction = FunctionEncoder.encode(function);
		System.out.println("encodedFunction: " + encodedFunction);

	}

}
