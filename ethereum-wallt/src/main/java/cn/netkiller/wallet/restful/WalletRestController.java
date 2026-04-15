package cn.netkiller.wallet.restful;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.bitcoinj.wallet.UnreadableWalletException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.netkiller.wallet.ethereum.Wallet;
import cn.netkiller.wallet.ethereum.Wallet.WalletResponse;
import cn.netkiller.wallet.pojo.RestfulResponse;
import cn.netkiller.wallet.service.EthereumWallet;

@RestController
@RequestMapping("/wallet")
public class WalletRestController {
	private static final Logger logger = LoggerFactory.getLogger(WalletRestController.class);

	Wallet wallet = null;

	@Autowired
	private EthereumWallet ethereumWallet;

	public WalletRestController() {
		this.wallet = new Wallet();
	}

	@GetMapping("/mnemonic")
	public RestfulResponse version() throws IOException, UnreadableWalletException {
		WalletResponse mnemonic = this.wallet.createMnemonic();
		logger.info(mnemonic.toString());
		return new RestfulResponse(true, 0, null, mnemonic);
	}

	@GetMapping("/balance/{address}")
	public RestfulResponse balance(@PathVariable String address) throws IOException, InterruptedException, ExecutionException {
		Map<String, String> balances = ethereumWallet.getAllBalance(address);
		// logger.info(mnemonic.toString());
		return new RestfulResponse(true, 0, null, balances);
	}
}
