package cn.netkiller.wallet.restful;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.netkiller.wallet.ethereum.IBAN;
import cn.netkiller.wallet.ethereum.IBAN.Iban;
import cn.netkiller.wallet.pojo.RestfulResponse;

@RestController
public class IbanRestController {
	private static final Logger logger = LoggerFactory.getLogger(IbanRestController.class);

	public IbanRestController() {
		// TODO Auto-generated constructor stub
	}

	/* IBAN 编码 */
	@PostMapping(value = "/iban/decode")
	public RestfulResponse ibanDecode(@RequestParam String uri) {

		IBAN iban = new IBAN();
		Iban data = iban.decode(uri);
		return new RestfulResponse(true, 0, null, data);

	}

	/* IBAN 解码 */
	@PostMapping(value = "/iban/encode")
	public RestfulResponse ibanEncode(@RequestParam String address, @RequestParam String symbol, @RequestParam String amount) {

		IBAN iban = new IBAN();
		try {
			String ibanString = iban.encode(address, symbol, amount);
			return new RestfulResponse(true, 0, null, ibanString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return new RestfulResponse(false, 0, null, e.getMessage());
		}

	}

}
