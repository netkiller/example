package cn.netkiller.wallet.pojo;

import java.io.Serializable;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;

//@Entity
//@Table(name = "transactions")
public class Transaction implements Serializable {

	private static final long serialVersionUID = 6710992220657056861L;
//	@Id
//	@Column(name = "blockNumber", unique = true, nullable = false, insertable = true, updatable = false)
	private int blockNumber;
	private String timeStamp;
	private String hash;
	private String nonce;
	private String blockHash;
	private String transactionIndex;
//	@Column(name = "from_address")
	private String from;
//	@Column(name = "to_address")
	private String to;
	private String value;
	private String gas;
	private String gasPrice;
	private String isError;
	private String txreceipt_status;
//	@Column(columnDefinition = "TEXT")
	private String input;
	private String contractAddress;
	private String cumulativeGasUsed;
	private String gasUsed;
	private String confirmations;

	public Transaction() {
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

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getBlockHash() {
		return blockHash;
	}

	public void setBlockHash(String blockHash) {
		this.blockHash = blockHash;
	}

	public String getTransactionIndex() {
		return transactionIndex;
	}

	public void setTransactionIndex(String transactionIndex) {
		this.transactionIndex = transactionIndex;
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

	public String getTxreceipt_status() {
		return txreceipt_status;
	}

	public void setTxreceipt_status(String txreceipt_status) {
		this.txreceipt_status = txreceipt_status;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}

	public String getCumulativeGasUsed() {
		return cumulativeGasUsed;
	}

	public void setCumulativeGasUsed(String cumulativeGasUsed) {
		this.cumulativeGasUsed = cumulativeGasUsed;
	}

	public String getGasUsed() {
		return gasUsed;
	}

	public void setGasUsed(String gasUsed) {
		this.gasUsed = gasUsed;
	}

	public String getConfirmations() {
		return confirmations;
	}

	public void setConfirmations(String confirmations) {
		this.confirmations = confirmations;
	}

	@Override
	public String toString() {
		return "Transactions [blockNumber=" + blockNumber + ", timeStamp=" + timeStamp + ", hash=" + hash + ", nonce=" + nonce + ", blockHash=" + blockHash + ", transactionIndex=" + transactionIndex + ", from=" + from + ", to=" + to + ", value=" + value + ", gas=" + gas + ", gasPrice=" + gasPrice + ", isError=" + isError + ", txreceipt_status=" + txreceipt_status + ", input=" + input + ", contractAddress=" + contractAddress + ", cumulativeGasUsed=" + cumulativeGasUsed + ", gasUsed=" + gasUsed + ", confirmations=" + confirmations + "]";
	}

}
