package cn.netkiller.wallet.pojo;

import java.util.ArrayList;
import java.util.List;

public class TokenTransactionResponse {
	private String status;
	private String message;
	private List<TokenTransaction> result = new ArrayList<TokenTransaction>();

	public TokenTransactionResponse() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "TokenTransactionResponse [status=" + status + ", message=" + message + ", result=" + result + "]";
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

	public List<TokenTransaction> getResult() {
		return result;
	}

	public void setResult(List<TokenTransaction> result) {
		this.result = result;
	}

}
