package cn.netkiller.wallet.pojo;

public class JsonRpc {
	private String jsonrpc;
	private int id;
	private String result;

	public JsonRpc() {
		// TODO Auto-generated constructor stub
	}

	public String getJsonrpc() {
		return jsonrpc;
	}

	public void setJsonrpc(String jsonrpc) {
		this.jsonrpc = jsonrpc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "JsonRpc [jsonrpc=" + jsonrpc + ", id=" + id + ", result=" + result + "]";
	}

}
