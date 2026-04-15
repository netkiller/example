package cn.netkiller.wallet.service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import cn.netkiller.wallet.pojo.TokenResponse;

public interface EthereumWallet {

	String getUrl();

	TokenResponse getToken(String contractAddress);

	public void addToken(String address, String contractAddress);

	public Map<String, String> getAllBalance(String address) throws IOException, InterruptedException, ExecutionException;

}
