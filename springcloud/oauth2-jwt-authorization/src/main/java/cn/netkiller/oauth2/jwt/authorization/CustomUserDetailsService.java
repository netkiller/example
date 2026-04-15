package cn.netkiller.oauth2.jwt.authorization;

import java.util.Collection;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	PasswordEncoder passwordEncoder;

	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		if ("admin".equalsIgnoreCase(name)) {
			Collection<GrantedAuthority> authorities = new HashSet<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			// PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
			String password = passwordEncoder.encode("chen");
			User user = new User("admin", password, authorities);
			return user;
		}
		return null;
	}

	// private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);
	// @Autowired
	// private MemberRepository memberRepository;
	//
	// @Override
	// public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	// Member member = memberRepository.findOneByUsername(username);
	// if (member == null) {
	// log.error("用户不存在");
	// throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
	// }
	// return new UserRepositoryUserDetails(member);
	// }
	//
	// /**
	// * 注意该类的层次结构，继承了Member并实现了UserDetails接口，继承是为了使用Member的username和password信息
	// */
	// private final static class UserRepositoryUserDetails extends Member implements UserDetails {
	// private static final long serialVersionUID = 1L;
	//
	// private UserRepositoryUserDetails(Member member) {
	// super(member);
	// }
	//
	// @Override
	// public Collection<? extends GrantedAuthority> getAuthorities() {
	// Role role = new Role();
	// return role.getRoles();
	// }
	//
	// @Override
	// public String getUsername() {
	// return super.getUsername();
	// }
	//
	// @Override
	// public boolean isAccountNonExpired() {
	// return true;
	// }
	//
	// @Override
	// public boolean isAccountNonLocked() {
	// return true;
	// }
	//
	// @Override
	// public boolean isCredentialsNonExpired() {
	// return true;
	// }
	//
	// @Override
	// public boolean isEnabled() {
	// return true;
	// }
	//
	// }
}