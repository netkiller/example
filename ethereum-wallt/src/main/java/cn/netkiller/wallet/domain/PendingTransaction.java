package cn.netkiller.wallet.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(indexes = { @Index(name = "address", columnList = "from_address,to_address"), @Index(name = "contractAddress", columnList = "contractAddress") })
public class PendingTransaction implements Serializable {

	private static final long serialVersionUID = 7398901907371493919L;

	@Id
	@Column(name = "hash", unique = true, nullable = false, insertable = true, updatable = false)
	private String hash;
	@Column(name = "from_address")
	private String from;
	@Column(name = "to_address")
	private String to;
	private String value;
	private String gas;
	private String contractAddress;
	private String symbol;

	public PendingTransaction() {
		// TODO Auto-generated constructor stub
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

	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return "PendingTransaction [hash=" + hash + ", from=" + from + ", to=" + to + ", value=" + value + ", gas=" + gas + ", contractAddress=" + contractAddress + ", symbol=" + symbol + "]";
	}

}
