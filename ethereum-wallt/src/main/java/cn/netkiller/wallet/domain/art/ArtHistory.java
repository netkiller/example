package cn.netkiller.wallet.domain.art;

import java.util.Date;

public class ArtHistory {

	private String id;
	private Date ctime;
	private String message;

	public ArtHistory() {
		// TODO Auto-generated constructor stub
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ArtHistory [ctime=" + ctime + ", message=" + message + "]";
	}

}
