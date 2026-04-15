package cn.netkiller.oauth2.jdbc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.netkiller.oauth2.jdbc.entity.User;

/**
 * 
 * @author Neo Chen
 * 
 *         UserRepository with custom methods for finding a User by username or email
 *
 */

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	User findByEmail(String email);

}