package cn.netkiller.wallet.restful;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import cn.netkiller.wallet.domain.TransactionHistory;
import cn.netkiller.wallet.domain.TransactionPostion;
import cn.netkiller.wallet.pojo.JsonRpc;
import cn.netkiller.wallet.pojo.TokenTransaction;
import cn.netkiller.wallet.pojo.TokenTransactionResponse;
import cn.netkiller.wallet.pojo.Transaction;
import cn.netkiller.wallet.pojo.TransactionResponse;
import cn.netkiller.wallet.repository.TransactionHistoryRepository;
import cn.netkiller.wallet.repository.TransactionPostionRepository;
import cn.netkiller.wallet.service.EthereumApi;
import cn.netkiller.wallet.service.EthereumWallet;

@RestController
public class HistoryRestController {
	private static final Logger logger = LoggerFactory.getLogger(HistoryRestController.class);

	@Autowired
	private EthereumApi ethereumApi;
	
	@Autowired
	TransactionHistoryRepository transactionHistoryRepository;

	public HistoryRestController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/transaction/refresh/{address}")
	public String refresh(@PathVariable String address) {
		ethereumApi.syncTokenTransactionHistory(address);
		return "ok";
	}

	@GetMapping("/transaction/{address}")
	public Page<TransactionHistory> transaction(@PathVariable String address, @PageableDefault(sort = { "block_number" }) Pageable pageable) {
		Page<TransactionHistory> transactionHistory;
		int pageNumber = pageable.getPageNumber();
		System.out.println(pageNumber);
		ethereumApi.syncTransactionHistory(address);
		transactionHistory = transactionHistoryRepository.findByAddressAndContractAddressIsNull(address, pageable);

		return transactionHistory;
	}

	@GetMapping("/transaction/{address}/{symbol}")
	public Page<TransactionHistory> transactionToken(@PathVariable String address, @PathVariable String symbol, @PageableDefault(sort = { "block_number" }) Pageable pageable) {
		Page<TransactionHistory> transactionHistory;
		ethereumApi.syncTokenTransactionHistory(address);
		transactionHistory = transactionHistoryRepository.findTokenByAddressAndContactAddress(address, symbol, pageable);

		return transactionHistory;
	}
}
