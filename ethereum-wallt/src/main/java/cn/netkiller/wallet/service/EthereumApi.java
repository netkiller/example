package cn.netkiller.wallet.service;

public interface EthereumApi {

	void syncTokenTransactionHistory(String address);

	void syncTransactionHistory(String address);

}
