package cn.netkiller.swagger2;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "User", description = "通用用户对象")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "用户名", name = "username", example = "neo")
	private String username = "Neo";
	private String password = "passw0rd";
	private String nickname = "netkiller";
	@ApiModelProperty(value = "状态", name = "state", example = "false", required = true)
	private boolean state = false;

	@ApiModelProperty(value = "字符串数组", hidden = true)
	private String[] array;
	private List<String> list;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String[] getArray() {
		return array;
	}

	public void setArray(String[] array) {
		this.array = array;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

}