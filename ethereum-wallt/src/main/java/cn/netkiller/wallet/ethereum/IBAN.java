package cn.netkiller.wallet.ethereum;

import java.math.BigInteger;
import static java.lang.Integer.parseInt;

public class IBAN {

	public static class Iban {
		private String address;
		private String token;
		private String amount;

		public Iban(String address, String token, String amount) {
			this.address = address;
			this.token = token;
			this.amount = amount;
		}

		public String getAmount() {
			return amount;
		}

		public String getAddress() {
			return address;
		}

		public String getToken() {
			return token;
		}

		public void setAmount(String amount) {
			this.amount = amount;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public void setToken(String token) {
			this.token = token;
		}

		@Override
		public String toString() {
			return "Iban [address=" + address + ", token=" + token + ", amount=" + amount + "]";
		}
	}

	private static boolean validateIBAN(String iban) {
		int len = iban.length();
		if (len < 4 || !iban.matches("[0-9A-Z]+"))
			return false;

		iban = iban.substring(4) + iban.substring(0, 4);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++)
			sb.append(Character.digit(iban.charAt(i), 36));

		BigInteger bigInt = new BigInteger(sb.toString());

		return bigInt.mod(BigInteger.valueOf(97)).intValue() == 1;
	}

	public IBAN() {
		// TODO Auto-generated constructor stub
	}

	public Iban decode(String result) {
		int ibanEndpoint = result.indexOf("?");
		String iban = result.substring(5, ibanEndpoint < 0 ? result.length() : ibanEndpoint);
		String address = this.toAddress(iban);
		String query = result.substring(ibanEndpoint + 1, result.length());
		String[] params = query.split("&");
		String token = null;
		String amount = null;
		for (String param : params) {
			if (param.startsWith("token=")) {
				token = param.substring(6);
				continue;
			}
			if (param.startsWith("amount=")) {
				amount = param.substring(7);
			}
		}
		return new Iban(address, token, amount);
	}

	public String encode(String address, String token, String amount) throws Exception {
		return String.format("iban:%s?token=%s&amount=%s", this.toIban(address), token, amount);
	}

	private String toAddress(String iban) {
		String base36 = iban.substring(4);
		StringBuilder base16 = new StringBuilder(new BigInteger(base36, 36).toString(16));
		while (base16.length() < 20) {
			base16.insert(0, "0");
		}
		return "0x" + base16.toString().toLowerCase();
	}

	public String toIban(String address) throws Exception {
		if(address.length() != 42) {
			throw new Exception("The length of address is 42.");
		}
		address = address.toLowerCase().substring(2);
		BigInteger value = new BigInteger(address, 16);
		StringBuilder bban = new StringBuilder(value.toString(36).toUpperCase());
		while (bban.length() < 15 * 2) {
			bban.insert(0, '0');
		}
		String iban = "XE00" + bban;

		iban = iban.substring(4) + iban.substring(0, 4);
		StringBuilder code = new StringBuilder();
		for (int i = 0; i < iban.length(); i++) {
			char chr = iban.charAt(i);
			if (chr >= 'A' && chr <= 'Z') {
				int temp = chr - 'A' + 10;
				code.append(String.valueOf(temp));
			} else {
				code.append(String.valueOf((chr - '0')));
			}
		}

		String remainder = code.toString();
		String block;
		while (remainder.length() > 2) {
			int endPoint = remainder.length() >= 9 ? 9 : remainder.length();
			block = remainder.substring(0, endPoint);
			remainder = parseInt(block, 10) % 97 + remainder.substring(block.length());
		}

		int checkNum = parseInt(remainder, 10) % 97;
		String checkDigit = ("0" + (98 - checkNum));
		checkDigit = checkDigit.substring(checkDigit.length() - 2);

		String ibanAddress = "XE" + checkDigit + bban;
		if (validateIBAN(ibanAddress)) {
			return ibanAddress;
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		IBAN iban = new IBAN();
		// TODO Auto-generated method stub
		
		System.out.println(iban.toAddress("XE12MJF1GRTRXKF29KH9IY8YWTGSOS7UH5T"));
		
		String address = iban.toAddress("XE039RBH0XKV9FZMTH2701Q37FLX10NTWXU");
		System.out.println("IBAN to Address: " + address);

		String ibanAddress = iban.toIban("0x538b392D57d867A57eE8Eed05737cB08B4691302");
		System.out.println("Address to IBAN: " + ibanAddress);

		Iban ibanObj = iban.decode("iban:XE039RBH0XKV9FZMTH2701Q37FLX10NTWXU?token=ETH&amount=5");
		System.out.println("IBAN decode: " + ibanObj.toString());

		String ibanString = iban.encode("0x538b392D57d867A57eE8Eed05737cB08B4691302", "NBRC", "5");
		System.out.println("IBAN encode: " + ibanString);
		System.out.println("0x538b392d57d867a57ee8eed05737cb08b4691302".equals("0x538b392d57d867a57ee8eed05737cb08b4691302"));
	}
}
