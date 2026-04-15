package cn.netkiller.wallet.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class UserToken {
	@EmbeddedId
	@Column(unique = true, nullable = false, insertable = true, updatable = false)
	private UserTokenPrimaryKey primaryKey;

	private String name;
	private String symbol;
	private int decimals;
	
	private boolean status;

	public UserToken() {
		// TODO Auto-generated constructor stub
	}

	public UserTokenPrimaryKey getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(UserTokenPrimaryKey primaryKey) {
		this.primaryKey = primaryKey;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "UserToken [primaryKey=" + primaryKey + ", name=" + name + ", symbol=" + symbol + ", decimals=" + decimals + "]";
	}

	@Embeddable
	public static class UserTokenPrimaryKey implements Serializable {

		private static final long serialVersionUID = 1242827922377178368L;
		private String address;
		private String contractAddress;

		public UserTokenPrimaryKey() {
		}

		public UserTokenPrimaryKey(String address, String contractAddress) {
			this.address = address;
			this.contractAddress = contractAddress;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getContractAddress() {
			return contractAddress;
		}

		public void setContractAddress(String contractAddress) {
			this.contractAddress = contractAddress;
		}

		@Override
		public String toString() {
			return "UserTokenPrimaryKey [address=" + address + ", contractAddress=" + contractAddress + "]";
		}

	}

}
