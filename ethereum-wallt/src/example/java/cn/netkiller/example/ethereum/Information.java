package cn.netkiller.example.ethereum;

import java.io.IOException;
import java.math.BigInteger;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthCoinbase;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthHashrate;
import org.web3j.protocol.core.methods.response.EthMining;
import org.web3j.protocol.core.methods.response.EthProtocolVersion;
import org.web3j.protocol.core.methods.response.EthSyncing;
import org.web3j.protocol.core.methods.response.NetPeerCount;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

public class Information {
	private static Web3j web3j;

	public Information() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		web3j = Web3j.build(new HttpService("https://rinkeby.infura.io/CsS9shwaAab0z7B4LP2d"));
		try {
			// 客户端版本
			Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
			String clientVersion = web3ClientVersion.getWeb3ClientVersion();
			System.out.println("客户端版本: " + clientVersion);
			// 协议版本
			EthProtocolVersion ethProtocolVersion = web3j.ethProtocolVersion().send();
			String protocolVersion = ethProtocolVersion.getProtocolVersion();
			System.out.println("协议版本" + protocolVersion);
			// 区块数量
			EthBlockNumber ethBlockNumber = web3j.ethBlockNumber().send();
			BigInteger blockNumber = ethBlockNumber.getBlockNumber();
			System.out.println("当前区块：" + blockNumber);

			// 矿工账户
			EthCoinbase ethCoinbase = web3j.ethCoinbase().send();
			String coinbase = ethCoinbase.getAddress();
			System.out.println("矿工账号：" + coinbase);

			// 是否在同步区块
			EthSyncing ethSyncing = web3j.ethSyncing().send();
			boolean isSyncing = ethSyncing.isSyncing();
			System.out.println("同步状态：" + isSyncing);
			// 是否在挖矿
			EthMining ethMining = web3j.ethMining().send();
			boolean isMining = ethMining.isMining();
			System.out.println("挖矿状态：" + isMining);
			// 当前gas price
			EthGasPrice ethGasPrice = web3j.ethGasPrice().send();
			BigInteger gasPrice = ethGasPrice.getGasPrice();
			System.out.println("Gas 价格：" + gasPrice);
			// 挖矿速度
			EthHashrate ethHashrate = web3j.ethHashrate().send();
			BigInteger hashRate = ethHashrate.getHashrate();
			System.out.println("挖矿速度：" + hashRate);

			// 连接的节点数
			NetPeerCount netPeerCount = web3j.netPeerCount().send();
			BigInteger peerCount = netPeerCount.getQuantity();
			System.out.println("节点数量：" + peerCount);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
