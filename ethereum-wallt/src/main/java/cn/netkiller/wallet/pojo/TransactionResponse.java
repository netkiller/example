package cn.netkiller.wallet.pojo;

import java.util.ArrayList;
import java.util.List;

public class TransactionResponse {
	private String status;
	private String message;
	private List<Transaction> result = new ArrayList<Transaction>();

	public TransactionResponse() {
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Transaction> getResult() {
		return result;
	}

	public void setResult(List<Transaction> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "TransactionsResponse [status=" + status + ", message=" + message + ", result=" + result + "]";
	}

}
