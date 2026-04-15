package cn.netkiller.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.netkiller.wallet.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findOneByUsername(String username);

	User findByName(String name);

}
