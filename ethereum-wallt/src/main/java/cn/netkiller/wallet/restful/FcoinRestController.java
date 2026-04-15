package cn.netkiller.wallet.restful;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.netkiller.wallet.domain.fcoin.Crawler;
import cn.netkiller.wallet.repository.fcoin.CrawlerRepository;
import cn.netkiller.wallet.repository.fcoin.FcoinRepository;
import cn.netkiller.wallet.service.FcoinService;

@RestController
@RequestMapping("/fcoin")
public class FcoinRestController {
	private static final Logger logger = LoggerFactory.getLogger(FcoinRestController.class);

	@Autowired
	FcoinService fcoinService;

	@Autowired
	FcoinRepository fcoinRepository;

	@Autowired
	CrawlerRepository crawlerRepository;

	// http://localhost:8080/fcoin/crawler/0x81E74a3eA4BaB2277aA3b941E9D9F37B08Ac5374/0xf14cec230bf6887828252d139361a1c394431cef

	@GetMapping("/add/{contractAddress}/{address}")
	public String add(@PathVariable String contractAddress, @PathVariable String address) {

		Crawler crawler = new Crawler();
		crawler.setAddress(address);
		crawler.setContractAddress(contractAddress);
		crawlerRepository.save(crawler);

		return "Add OK";
	}

	@GetMapping("/crawler")
	public String crawler() {

		Iterable<Crawler> crawlers = crawlerRepository.findAll();
		logger.info(crawlers.toString());
		for (Crawler crawler : crawlers) {
			fcoinService.task(crawler);

		}

		// fcoinService.crawler();

		return "Crawler OK";
	}

	@GetMapping("/init")
	public String init() {

		// List<Map<String, String>> datas = new ArrayList<Map<String, String>>();
		// for (Map<String, String> data : datas) {
		// }

		crawlerRepository.deleteAll();
		// IFOOD
		// this.save("0x80dfecd1b90a8cf6b5722ebfca1a000128835b46", "0xf14cec230bf6887828252d139361a1c394431cef", "IFOOD");

		// Drink
		// this.save("0x0089659f609933d16a5cd6c2be1a5dca1abe24ad", "0x65f51b384fbe92cb1ac7d9304a51323a8346c339", "Drink");

		// 3DB
		// this.save("0x11016adcb85b65a0da8d8ddc3ade69dbad6bbca4", "0xc4e52187091d45f27ea529ac222da1daa1fd64bd", "3DB");

		// FOTA
		// this.save("0x15ad855526598282e173921e62b0cfe4743f5f6f", "0x7c8a33815412fb1b6016f5b7403099a175edfc6c", "FOTA");

		// VCT
		// this.save("0x9746953f5b1324a78132895cfd263f417b0faae3", "0x617e21273ba1de5032a9c032e7953679e301501b", "VCT");
		// https://api.etherscan.io/api?module=account&action=tokentx&contractaddress=0x9746953f5b1324a78132895cfd263f417b0faae3&address=0x617e21273ba1de5032a9c032e7953679e301501b&page=1&offset=100&sort=desc&apikey=YourApiKeyToken

		// TKT
		// this.save("0x13e9ec660d872f55405d70e5c52d872136f0970c", "0xe0a22d8f528048ea7dc39fd660d2ac1f1c959569", "TKT");

		// BRM
		// this.save("0xd7732e3783b0047aa251928960063f863ad022d8", "0xbe78a3f305aedad45dcf268c8d8d6d4c9d5451e5", "BRM");

		// SHOW
		// this.save("0xf41861f194e7ba8de95144a89e0c6ed16ee0b3a0", "0xe33530770cbf1fb78e76505d93fed5bb234d4767", "SHOW");

		// PAI
		// this.save("0xb9bb08ab7e9fa0a1356bd4a39ec0ca267e03b0b3", "0x41efb7f2fa8e27a5c089082e8a61f15f748f7e0b", "PAI");

		// CPS
		this.save("0xef6d51c4bb5e197bc2005fe49ba475bda5a302c6", "0x146ce796754c754183176f5493acb37a4cfe7fec", "CPS");

		// Fair
		this.save("0x9b20dabcec77f6289113e61893f7beefaeb1990a", "0x146ce796754c754183176f5493acb37a4cfe7fec", "FAIR");

		// WTE
		this.save("0xb53ac311087965d9e085515efbe1380b2ca4de9a", "0x068ed08839f26a1cb12343bdcb90537dcc45170f", "WTE");

		// OAS
		this.save("0x89885fc1f76c3f4cc719640e33c315227da7003a", "0xe0a22d8f528048ea7dc39fd660d2ac1f1c959569", "OAS");

		// 3X
		this.save("0x25d0ca87c1cc4947f6ccbc01ea50ea7a969bf065", "0x54054ad0406a1fbc5e8ea28bbbc7e3708714f56f", "3X");

		// IHT
		this.save("0xeda8b016efa8b1161208cf041cd86972eee0f31e", "0xcd1b741391c1d360ef3bc876500f4758ec8b0bfd", "IHT");
		// EJOY
		this.save("0xac0741127cac11e4455c7943b654bcced7fdd5a4", "0xe0a22d8f528048ea7dc39fd660d2ac1f1c959569", "EJOY");
		// LOVC
		this.save("0x49592d97be49033615a7fbc02c6853e4c58eb9bc", "0x15ad855526598282e173921e62b0cfe4743f5f6f", "LOVC");
		// DATG
		// this.save("0x56d1ae30c97288da4b58bc39f026091778e4e316", "0x67b8b2ba66bc6997e9ed5e4fdb5ff7a7a1371ba1", "DATG");

		// MAYA
		this.save("0x581bd3322a90866e5da8060b9d64d7904394ad42", "0xe0a22d8f528048ea7dc39fd660d2ac1f1c959569", "MAYA");
		// this.save("0x584b44853680ee34a0f337b712a8f66d816df151", "0x66407a0ae7efa8d193cd941782c3e68540a53b14", "AIDOC");
		// this.save("0xa159656d4d32d4cdc40bc795978142e4b676c198", "0xcac00d55c89a2baaf37768e548d9c7ccd9fb8786", "BCG");

		this.save("0xe1521029d2591ba2a0f92b6e744a825f665f748b", "0x6b37680a1ed4a0d303699c64d20fa119f9b3269a", "IDXE");
		this.save("0xa7D81c86F9934b56Dd00FA826C319330D628d31F", "0xf70898885c1a3d10099f3f0432ea54da26175c39", "BEAUTY");
		// this.save("0x653430560be843c4a3d143d0110e896c2ab8ac0d", "0xde5ad1404323b769a96b44d4bba9aefb316d0a6a", "MOF");
		this.save("0xff603f43946a3a28df5e6a73172555d8c8b02386", "0xad780b437c42ee1e2c6173ac1291e6c004136500", "RNT");
		this.save("0xcc771a11d368a76e6fa34b3aab8227297f48fe41", "0x873f593476861d1cfdb7e4290e44a009d1886509", "DHT");
		this.save("0x8f7b0b40e27e357540f90f187d90ce06366ac5a5", "0xf58f1a340c0fab8cd00881912999226e93325711", "VLC");
		return "Init OK";
	}

	private void save(String contractAddress, String address, String symbol) {

		Crawler crawler = new Crawler();
		crawler.setAddress(address);
		crawler.setContractAddress(contractAddress);
		crawler.setSymbol(symbol);
		crawlerRepository.save(crawler);

	}

	@GetMapping("/send/{loop}")
	public String send(@PathVariable int loop) {
		// , @PathVariable String address

		for (int i = 0; i <= loop; i++) {
			int count = fcoinRepository.countByAirdropFalse();
			System.out.println(count);

			fcoinService.send(i, 10);
			// System.out.println(status);
		}

		return "Send OK";
	}
}