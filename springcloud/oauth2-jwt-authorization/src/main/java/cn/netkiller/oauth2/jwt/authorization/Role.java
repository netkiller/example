package cn.netkiller.oauth2.jwt.authorization;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

//@Entity
//public class Role implements GrantedAuthority {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//
//	@Column(nullable = false)
//	private String name;
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	@Override
//	public String getAuthority() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	@Override
//	public String toString() {
//		return name;
//	}
//}

//// 默认角色
// public class Role implements GrantedAuthority {
//
// private static final long serialVersionUID = -2633659220734280260L;
//
// private Set<Role> roles = new HashSet<Role>();
//
// @Override
// public String getAuthority() {
// return "USER";
// }
//
// public Set<Role> getRoles() {
// return roles;
// }
//
// public void setRoles(Set<Role> roles) {
// this.roles = roles;
// }
//
// }