package cn.netkiller.wallet.pojo;

public class TokenTransaction {
	private int blockNumber;
	private String timeStamp;
	private String hash;
	private String nonce;
	private String blockHash;
	private String from;
	private String contractAddress;
	private String to;
	private String value;
	private String tokenName;
	private String tokenSymbol;
	private String tokenDecimal;
	private String transactionIndex;
	private String gas;
	private String gasPrice;
	private String gasUsed;
	private String cumulativeGasUsed;
	private String input;
	private String confirmations;

	public TokenTransaction() {
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

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
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

	public String getTokenName() {
		return tokenName;
	}

	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}

	public String getTokenSymbol() {
		return tokenSymbol;
	}

	public void setTokenSymbol(String tokenSymbol) {
		this.tokenSymbol = tokenSymbol;
	}

	public String getTokenDecimal() {
		return tokenDecimal;
	}

	public void setTokenDecimal(String tokenDecimal) {
		this.tokenDecimal = tokenDecimal;
	}

	public String getTransactionIndex() {
		return transactionIndex;
	}

	public void setTransactionIndex(String transactionIndex) {
		this.transactionIndex = transactionIndex;
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

	public String getGasUsed() {
		return gasUsed;
	}

	public void setGasUsed(String gasUsed) {
		this.gasUsed = gasUsed;
	}

	public String getCumulativeGasUsed() {
		return cumulativeGasUsed;
	}

	public void setCumulativeGasUsed(String cumulativeGasUsed) {
		this.cumulativeGasUsed = cumulativeGasUsed;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getConfirmations() {
		return confirmations;
	}

	public void setConfirmations(String confirmations) {
		this.confirmations = confirmations;
	}

	@Override
	public String toString() {
		return "TokenTransaction [blockNumber=" + blockNumber + ", timeStamp=" + timeStamp + ", hash=" + hash + ", nonce=" + nonce + ", blockHash=" + blockHash + ", from=" + from + ", contractAddress=" + contractAddress + ", to=" + to + ", value=" + value + ", tokenName=" + tokenName + ", tokenSymbol=" + tokenSymbol + ", tokenDecimal=" + tokenDecimal + ", transactionIndex=" + transactionIndex + ", gas=" + gas + ", gasPrice=" + gasPrice + ", gasUsed=" + gasUsed + ", cumulativeGasUsed=" + cumulativeGasUsed + ", input=" + input + ", confirmations=" + confirmations + "]";
	}

}
