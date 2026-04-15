package cn.netkiller.example.ethereum.account;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.NewAccountIdentifier;
import org.web3j.protocol.admin.methods.response.PersonalListAccounts;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
//import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.ipc.UnixIpcService;

public class AccountTest {
	private static Admin admin;

	public AccountTest() {
		// TODO Auto-generated constructor stub
		// admin = Admin.build(new HttpService("http://127.0.0.1:8545"));
		admin = Admin.build(new UnixIpcService("/Users/neo/Library/Ethereum/geth.ipc"));

	}

	private void createAccount() throws IOException {
		String password = "12345678";
		NewAccountIdentifier newAccountIdentifier = admin.personalNewAccount(password).send();
		String address = newAccountIdentifier.getAccountId();
		System.out.println("New account address: " + address);
	}

	private void getAccountList() throws IOException {
		PersonalListAccounts personalListAccounts = admin.personalListAccounts().send();
		List<String> addressList = personalListAccounts.getAccountIds();
		System.out.println("Account count: " + addressList.size());
		for (String address1 : addressList) {
			System.out.println(address1);
		}
	}

	private void unlockAccount() {
		String address = "0xf56b81a2bcb964D2806071e9Be4289A5559BB0fA";
		String password = "12345678";
		// 账号解锁持续时间 单位秒 缺省值300秒
		BigInteger unlockDuration = BigInteger.valueOf(60L);
		try {
			PersonalUnlockAccount personalUnlockAccount = admin.personalUnlockAccount(address, password, unlockDuration).send();
			Boolean isUnlocked = personalUnlockAccount.accountUnlocked();
			System.out.println("Account unlock " + isUnlocked);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		AccountTest account = new AccountTest();
		account.createAccount();
		account.getAccountList();
		account.unlockAccount();
	}

}
