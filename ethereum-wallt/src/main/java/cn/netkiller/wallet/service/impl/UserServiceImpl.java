package cn.netkiller.wallet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cn.netkiller.wallet.domain.User;
import cn.netkiller.wallet.repository.UserRepository;
import cn.netkiller.wallet.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	public User loadUserByUsername(String username) throws UsernameNotFoundException {

		return userRepository.findOneByUsername(username);
	}

}
