package cn.netkiller.wallet.domain.fcoin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Fcoin {
	@Id
	@Column(name = "address", unique = true, nullable = false, insertable = true, updatable = false)
	private String address;

	private int blockNumber;
	private String hash;
	private String gas;
	private String gasPrice;
	private String contractAddress;
	private String gasUsed;
	private String name;
	private String symbol;
	private String decimals;

	private boolean airdrop = false;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isAirdrop() {
		return airdrop;
	}

	public void setAirdrop(boolean airdrop) {
		this.airdrop = airdrop;
	}

	public int getBlockNumber() {
		return blockNumber;
	}

	public void setBlockNumber(int blockNumber) {
		this.blockNumber = blockNumber;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getGas() {
		return gas;
	}

	public void setGas(String gas) {
		this.gas = gas;
	}

	public String getGasPrice() {
		return gasPrice;
	}

	public void setGasPrice(String gasPrice) {
		this.gasPrice = gasPrice;
	}

	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}

	public String getGasUsed() {
		return gasUsed;
	}

	public void setGasUsed(String gasUsed) {
		this.gasUsed = gasUsed;
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

	public String getDecimals() {
		return decimals;
	}

	public void setDecimals(String decimals) {
		this.decimals = decimals;
	}

	@Override
	public String toString() {
		return "Fcoin [address=" + address + ", blockNumber=" + blockNumber + ", hash=" + hash + ", gas=" + gas + ", gasPrice=" + gasPrice + ", contractAddress=" + contractAddress + ", gasUsed=" + gasUsed + ", name=" + name + ", symbol=" + symbol + ", decimals=" + decimals + ", airdrop=" + airdrop + "]";
	}

}
