package cn.netkiller.wallet.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import cn.netkiller.wallet.domain.User;

public interface UserService {
	public User loadUserByUsername(String username) throws UsernameNotFoundException;
}
