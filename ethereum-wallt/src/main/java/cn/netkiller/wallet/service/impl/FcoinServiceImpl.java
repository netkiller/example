package cn.netkiller.wallet.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;

import cn.netkiller.wallet.pojo.TokenTransaction;
import cn.netkiller.wallet.pojo.TokenTransactionResponse;
import cn.netkiller.wallet.repository.fcoin.CrawlerRepository;
import cn.netkiller.wallet.repository.fcoin.FcoinRepository;
import cn.netkiller.wallet.service.FcoinService;
import cn.netkiller.wallet.domain.fcoin.Crawler;
import cn.netkiller.wallet.domain.fcoin.Fcoin;

@Service
public class FcoinServiceImpl implements FcoinService {
	private static final Logger logger = LoggerFactory.getLogger(FcoinServiceImpl.class);

	@Autowired
	private Web3j web3j;
	@Autowired
	FcoinRepository fcoinRepository;

	@Autowired
	CrawlerRepository crawlerRepository;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Async
	public void crawler() {

		Iterable<Crawler> crawlers = crawlerRepository.findAll();
		logger.info(crawlers.toString());
		for (Crawler crawler : crawlers) {
			this.task(crawler);

		}

	}

	@Async
	public Future<String> task(Crawler crawler) {

		long start = System.currentTimeMillis();

		String contractAddress = crawler.getContractAddress();
		String address = crawler.getAddress();
		String symbol = crawler.getSymbol();

		logger.info("Task " + symbol + " started.");

		int page = 1;

		String key = String.format("crawler:page:%s:%s", contractAddress, address);

		while (true) {
			if (stringRedisTemplate.hasKey(key)) {
				page = Integer.valueOf(stringRedisTemplate.opsForValue().get(key));
			} else {
				page = 1;
			}

			TokenTransactionResponse tokenTransactionResponse = this.getTokenTransactionsHistory(contractAddress, address, page, 100);

			if (tokenTransactionResponse.getStatus().equals("1")) {
				List<TokenTransaction> tokenTransactions = tokenTransactionResponse.getResult();

				for (TokenTransaction token : tokenTransactions) {

					boolean isSave = false;
					// logger.info(token.getContractAddress() + " " + token.getTo() + " " + token.getTokenSymbol() + " " + token.getTokenDecimal() + " " + token.getValue());
					// if (Integer.valueOf(token.getTokenDecimal()) == 18) {
					if (token.getValue().equals("1000000000000000000")) {
						isSave = true;
					}
					if (symbol.equals("BEAUTY")) {
						isSave = true;
					} else if (symbol.equals("MOF")) {
						isSave = true;
					} else if (symbol.equals("VLC")) {
						isSave = true;
					}

					// }
					if (isSave) {
						// logger.info(token.toString());
						logger.info(symbol + " " + token.getContractAddress() + " " + token.getTo() + " " + token.getTokenSymbol() + " " + token.getTokenDecimal() + " " + token.getValue());

						Fcoin fcoin = fcoinRepository.findOneByAddress(token.getTo());
						if (fcoin == null) {
							fcoin = new Fcoin();
							fcoin.setAddress(token.getTo());
							fcoin.setContractAddress(contractAddress);
							fcoin.setHash(token.getHash());
							fcoin.setBlockNumber(token.getBlockNumber());
							fcoin.setGasPrice(token.getGasPrice());
							fcoin.setGas(token.getGas());
							fcoin.setGasUsed(token.getGasUsed());
							fcoin.setName(token.getTokenName());
							fcoin.setSymbol(token.getTokenSymbol());
							fcoin.setDecimals(token.getTokenDecimal());
							fcoinRepository.save(fcoin);
						}
					}
				}

				page++;
				stringRedisTemplate.opsForValue().set(key, String.valueOf(page));

			} else {
				break;
			}
		}

		long end = System.currentTimeMillis();
		logger.info("Task " + symbol + " finished, time elapsed: {} ms.", end - start);

		return new AsyncResult<>("Task " + symbol + " accomplished!");
	}

	private TokenTransactionResponse getTokenTransactionsHistory(String address, int startblock, int endblock) {

		final String url = "https://api.etherscan.io/api?module={module}&action={action}&address={address}&startblock={startblock}&endblock={endblock}&sort={sort}&apikey={apikey}";

		Map<String, String> params = new HashMap<String, String>();
		params.put("module", "account");
		params.put("action", "tokentx");

		params.put("address", address);
		params.put("startblock", String.valueOf(startblock));
		params.put("endblock", String.valueOf(endblock));
		params.put("sort", "asc");
		params.put("apikey", "RT5JW37AKEZVSW3C91Z86IGI2FF7JDPF1N");
		RestTemplate restTemplate = new RestTemplate();
		TokenTransactionResponse result = restTemplate.getForObject(url, TokenTransactionResponse.class, params);
		// logger.info(params.toString());
		logger.info(result.toString());
		return result;
	}

	private TokenTransactionResponse getTokenTransactionsHistory(String contractaddress, String address, int page, int offset) {

		// final String url = "https://api.etherscan.io/api?module={module}&action={action}&address={address}&startblock={startblock}&endblock={endblock}&sort={sort}&apikey={apikey}";
		// https://api.etherscan.io/api?module=account&action=tokentx&contractaddress=0x9f8f72aa9304c8b593d555f12ef6589cc3a579a2&page=1&offset=100&sort=asc&apikey=YourApiKeyToken
		final String url = "https://api.etherscan.io/api?module=account&action=tokentx&contractaddress={contractaddress}&address={address}&page={page}&offset={offset}&sort=asc&apikey={apikey}";

		// https://api.etherscan.io/api?module=account&action=tokentx&contractaddress=0x81E74a3eA4BaB2277aA3b941E9D9F37B08Ac5374&address=0x7a292aee4d7d529883feca0ebe57ed92353a7478&page=1&offset=100&sort=asc&apikey=YourApiKeyToken

		Map<String, String> params = new HashMap<String, String>();
		// params.put("module", "account");
		// params.put("action", "tokentx");

		params.put("contractaddress", contractaddress);
		params.put("address", address);
		// params.put("startblock", String.valueOf(startblock));
		// params.put("endblock", String.valueOf(endblock));
		// params.put("sort", "asc");
		params.put("page", String.valueOf(page));
		params.put("offset", String.valueOf(offset));
		params.put("apikey", "RT5JW37AKEZVSW3C91Z86IGI2FF7JDPF1N");
		RestTemplate restTemplate = new RestTemplate();
		TokenTransactionResponse result = restTemplate.getForObject(url, TokenTransactionResponse.class, params);
		// logger.info(params.toString());
		logger.info(result.toString());
		return result;
	}

	@Async
	public void send(int page, int size) {

//		try {
//			BigInteger gasPrice = BigInteger.ZERO;
//			BigInteger gasLimit = BigInteger.ZERO;
//			gasPrice = DefaultGasProvider.GAS_PRICE;
//			gasLimit = DefaultGasProvider.GAS_LIMIT;
//
//			String contractAddress = "0xf083f40599741bc576173b492268972ce661e5ec";
//			String privateKey = "166970EC717B3ECCADABF68AC066558537228DB022FFBDE13A06790967F2BC3A";
//
//			Credentials credentials = Credentials.create(privateKey);
//			NetkillerAdvancedToken contract = NetkillerAdvancedToken.load(contractAddress, web3j, credentials, gasPrice, gasLimit);
//
//			System.out.println(contract.isValid());
//
//			System.out.println(contract.name().send());
//			System.out.println(contract.symbol().send());
//			System.out.println(contract.decimals().send());
//
//			List<Fcoin> fcoins = fcoinRepository.findByAirdrop(false, PageRequest.of(page, size));
//			System.out.println(fcoins);
//			List<String> toAddress = new ArrayList<String>();
//
//			for (Fcoin fcoin : fcoins) {
//				toAddress.add(fcoin.getAddress());
//			}
//
//			System.out.println("---");
//			// String contractAddress = contract.getContractAddress();
//			System.out.println(contract.balanceOf("0x09bd48243AE8E6dF4f14d33921DaEc7827032681").send());
//			System.out.println("---");
//			BigInteger value = Convert.toWei(BigDecimal.valueOf(1), Convert.Unit.ETHER).toBigInteger();
//			TransactionReceipt hash = contract.transferBatch(toAddress, value).sendAsync().get();
//			System.out.println(hash.getTransactionHash());
//			System.out.println("---");
//			System.out.println(contract.balanceOf("0x09bd48243AE8E6dF4f14d33921DaEc7827032681").send());
//			if (hash.getTransactionHash() != null) {
//				for (Fcoin fcoin : fcoins) {
//					fcoin.setAirdrop(true);
//					fcoinRepository.save(fcoin);
//				}
//
//			}
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
