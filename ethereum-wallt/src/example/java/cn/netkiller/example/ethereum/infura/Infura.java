package cn.netkiller.example.ethereum.infura;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

public class Infura {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Web3j web3 = Web3j.build(new HttpService("https://rinkeby.infura.io/CsS9shwaAab0z7B4LP2d"));
			Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().send();
			System.out.println(web3ClientVersion.getWeb3ClientVersion());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
