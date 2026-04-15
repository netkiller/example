package cn.netkiller.wallet.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(indexes = { @Index(name = "address", columnList = "from_address,to_address"), @Index(name = "contractAddress", columnList = "contractAddress") })

public class TransactionHistory implements Serializable {
	private static final long serialVersionUID = 6710992220657056861L;
	@Id
	@Column(name = "blockNumber", unique = true, nullable = false, insertable = true, updatable = false)
	private int blockNumber;
	@Column(name = "timestamp")
	private String timeStamp;
	private String hash;
	@Column(name = "from_address")
	private String from;
	@Column(name = "to_address")
	private String to;
	private String value;
	private String gas;
	private String gasPrice;
	private String isError;
	private String contractAddress;
	private String gasUsed;
	private String name;
	private String symbol;
	private int decimals;

	public TransactionHistory() {
		// TODO Auto-generated constructor stub
	}

	public int getBlockNumber() {
		return blockNumber;
	}

	public void setBlockNumber(int blockNumber) {
		this.blockNumber = blockNumber;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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

	public String getIsError() {
		return isError;
	}

	public void setIsError(String isError) {
		this.isError = isError;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
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
		return "TransactionHistory [blockNumber=" + blockNumber + ", timeStamp=" + timeStamp + ", hash=" + hash + ", from=" + from + ", to=" + to + ", value=" + value + ", gas=" + gas + ", gasPrice=" + gasPrice + ", isError=" + isError + ", contractAddress=" + contractAddress + ", gasUsed=" + gasUsed + ", name=" + name + ", symbol=" + symbol + ", decimals=" + decimals + "]";
	}

}
