package cn.netkiller.example;

public class IpAddress {

	public IpAddress() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IpAddress ipaddr = new IpAddress();
		String ip = "175.100.206.38, 47.90.18.244,xx.x.xx.";
		
		//String ip = "175.100.206.38";
		System.out.println(ipaddr.getFirstIpAddress(ip));
	}

	public String getFirstIpAddress(String ipaddr) {
		if (ipaddr.indexOf(",") != -1) {
			return ipaddr.substring(0, ipaddr.indexOf(","));
		} else {
			return ipaddr;
		}
	}
}
