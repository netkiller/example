package cn.netkiller.json;

public class Member {
	private String name;

	public Member() {
		// TODO Auto-generated constructor stub
	}

	public Member(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Member [name=" + name + "]";
	}

}
