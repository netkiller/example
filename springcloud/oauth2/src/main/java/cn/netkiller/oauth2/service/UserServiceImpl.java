//package cn.netkiller.oauth2.service;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service(value = "userService")
//public class UserServiceImpl implements UserDetailsService {
//
//	public UserServiceImpl() {
//		// TODO Auto-generated constructor stub
//	}
//
//	@Autowired
//	private SysAccountRepository repository;
//
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		SysAccount user = repository.findByUserAccount(username);
//		if (user == null) {
////			log.info("登录用户【" + username + "】不存在.");
//			throw new UsernameNotFoundException("登录用户【" + username + "】不存在.");
//		}
//		return new org.springframework.security.core.userdetails.User(user.getUserAccount(), user.getUserPwd(), getAuthority());
//	}
//
//	private List getAuthority() {
//		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
//	}
//}
