package cn.netkiller.wallet.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class TransactionPostion {

	@Id
	private String address;
	private int ethPostion = 0;
	private int TokenPostion = 0;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getEthPostion() {
		return ethPostion;
	}

	public void setEthPostion(int ethPostion) {
		this.ethPostion = ethPostion;
	}

	public int getTokenPostion() {
		return TokenPostion;
	}

	public void setTokenPostion(int tokenPostion) {
		TokenPostion = tokenPostion;
	}

	@Override
	public String toString() {
		return "TransactionPostion [address=" + address + ", ethPostion=" + ethPostion + ", TokenPostion=" + TokenPostion + "]";
	}

}
