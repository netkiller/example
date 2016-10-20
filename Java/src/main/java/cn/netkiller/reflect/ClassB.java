package cn.netkiller.reflect;

public class ClassB extends ClassA{

	public ClassB() {
		// TODO Auto-generated constructor stub
	}
	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "ClassB [address=" + address + "]";
	}
	
}
