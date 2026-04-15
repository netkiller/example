package cn.netkiller.wallet.ethereum;

public class TokenType {

	public String name;
	public String symbol;
	public int decimals;
	public String contractAddress;

	public TokenType() {
		// TODO Auto-generated constructor stub
	}

	public TokenType(String name, String symbol, int decimals, String contractAddress) {
		// TODO Auto-generated constructor stub
	}

	public int getDecimals() {
		return decimals;
	}

	public void setDecimals(int decimals) {
		this.decimals = decimals;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}

	@Override
	public String toString() {
		return "Token [decimals=" + decimals + ", name=" + name + ", symbol=" + symbol + ", contractAddress=" + contractAddress + "]";
	}

}
