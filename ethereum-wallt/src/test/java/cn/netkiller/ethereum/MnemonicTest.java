package cn.netkiller.ethereum;

import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;

import org.bitcoinj.crypto.MnemonicCode;
import org.bitcoinj.crypto.MnemonicException.MnemonicChecksumException;
import org.bitcoinj.crypto.MnemonicException.MnemonicLengthException;
import org.bitcoinj.crypto.MnemonicException.MnemonicWordException;

import cn.netkiller.wallet.ethereum.Mnemonic;

public class MnemonicTest {

	public static void main(String[] args) throws IOException, MnemonicLengthException, MnemonicWordException, MnemonicChecksumException {
		// TODO Auto-generated method stub
		Mnemonic mnemonic = new Mnemonic();
		String code = mnemonic.getMnemonic();
		System.out.println(code);

		Mnemonic mnemonic1 = new Mnemonic("chen");
		String code1 = mnemonic1.getMnemonic();
		System.out.println(code1);

		// InputStream stream =
		// MnemonicCode.class.getResourceAsStream("mnemonic/wordlist/chinese_simplified.txt");
		InputStream stream = Mnemonic.class.getResourceAsStream("/mnemonic/wordlist/chinese_simplified.txt");
		MnemonicCode mnemonicCode = new MnemonicCode(stream, null);
		System.out.println(mnemonicCode.getWordList());
//		byte[] seed = mnemonicCode.toSeed(mnemonicCode.getWordList(),"");
//		SecureRandom secureRandom = new SecureRandom();
//		System.out.println(mnemonicCode.toMnemonic(secureRandom));

	}

}
