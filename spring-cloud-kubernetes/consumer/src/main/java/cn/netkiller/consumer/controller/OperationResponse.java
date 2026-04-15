package cn.netkiller.consumer.controller;

public class OperationResponse {

	public boolean status = false;
	public String data = "";

	public static OperationResponse builder() {
		// TODO Auto-generated method stub
		return new OperationResponse();
	}

	public OperationResponse status(boolean status) {
		this.status = status;
		return this;
	}

	public OperationResponse data(String data) {
		this.data = data;
		return this;
	}

	public String build() {
		return String.format("Status: %s, Data: %s", this.status, this.data);
	}
}
