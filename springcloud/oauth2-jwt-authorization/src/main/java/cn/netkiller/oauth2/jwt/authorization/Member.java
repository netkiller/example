//package cn.netkiller.oauth2.jwt.authorization;
//
//import java.io.Serializable;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "member")
//public class Member implements Serializable {
//
//	private static final long serialVersionUID = 7746927284625787594L;
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long id;
//	private String username;
//	private String password;
//
//	public Member(Member member) {
//		super();
//		this.username = member.getUsername();
//		this.password = member.getPassword();
//	}
//
//	public Member() {
//
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//}
//
