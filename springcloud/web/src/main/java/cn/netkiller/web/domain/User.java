package cn.netkiller.web.domain;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import cn.netkiller.web.annotation.Mobile;

public class User {

	private Long id;

	@NotNull(message = "用户账号不能为空")
	@Size(min = 6, max = 11, message = "账号长度必须是6-11个字符")
	private String username;

	@NotNull(message = "用户密码不能为空")
	@Size(min = 6, max = 8, message = "密码长度必须是6-8个字符")
	private String password;

	@NotNull(message = "用户邮箱不能为空")
	@Email(message = "邮箱格式不正确")
	private String email;

	// 这里是新添加的注解奥
	@Mobile(message = "手机号码格式错误！！！")
	private String phone;

	// 不允许为空，并且年龄的最小值为18
	@NotNull
	@Min(18)
	private Integer age;

	@Future
	private Date createTime;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", phone=" + phone + ", age=" + age + "]";
	}

}
