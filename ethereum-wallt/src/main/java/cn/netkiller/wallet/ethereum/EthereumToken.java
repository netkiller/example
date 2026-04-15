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
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

//public class EthereumToken extends Ethereum {
//
//	private String contractAddress = null;
//	private String privateKey = null;
//	// private BigInteger gasLimit = BigInteger.ZERO;
//	private BigInteger gasPrice = BigInteger.ZERO;
//	private String fromAddress;
//
//	public EthereumToken() {
//	}
//
//	public EthereumToken(Web3j web3j) {
//		web3 = web3j;
//	}
//
//	public EthereumToken(String url) {
//		web3 = Web3j.build(new HttpService(url));
//		super.web3 = web3;
//	}
//
//
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		// Token token = new Token();
//		// BigDecimal val = token.formatBalance(BigInteger.valueOf(1000200), 4);
//		// System.out.println(val);
//	}
//
//}
