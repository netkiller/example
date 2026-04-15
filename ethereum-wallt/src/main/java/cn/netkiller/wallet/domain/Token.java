package cn.netkiller.wallet.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Token {
	@Id
	@Column(name = "contractAddress", unique = true, nullable = false, insertable = true, updatable = false)
	private String contractAddress;
	private String name;
	private String symbol;
	private int decimals;

	public Token() {
		// TODO Auto-generated constructor stub
	}

	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
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

	public int getDecimals() {
		return decimals;
	}

	public void setDecimals(int decimals) {
		this.decimals = decimals;
	}

	@Override
	public String toString() {
		return "Token [contractAddress=" + contractAddress + ", name=" + name + ", symbol=" + symbol + ", decimals=" + decimals + "]";
	}

}
