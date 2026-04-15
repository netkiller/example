package cn.netkiller.wallet.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cn.netkiller.wallet.domain.Token;
import cn.netkiller.wallet.pojo.TokenResponse;
import cn.netkiller.wallet.repository.TokenRepository;
import cn.netkiller.wallet.service.EthereumWallet;

@RestController
public class TokenRestController {

	@Autowired
	EthereumWallet ethereumWallet;

	@Autowired
	TokenRepository tokenRepository;

	public TokenRestController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/token/{contractAddress}")
	public TokenResponse token(@PathVariable String contractAddress) {

		return ethereumWallet.getToken(contractAddress);
	}

	@GetMapping("/token/add/{contractAddress}")
	public TokenResponse addToken(@PathVariable String contractAddress) {

		TokenResponse tokenResponse = ethereumWallet.getToken(contractAddress);

		Token token = new Token();
		token.setContractAddress(contractAddress);
		token.setName(tokenResponse.getName());
		token.setSymbol(tokenResponse.getSymbol());
		token.setDecimals(tokenResponse.getDecimals());

		tokenRepository.save(token);

		return tokenResponse;
	}
}
