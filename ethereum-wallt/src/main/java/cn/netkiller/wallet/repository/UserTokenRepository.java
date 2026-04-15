package cn.netkiller.wallet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.netkiller.wallet.domain.UserToken;
import cn.netkiller.wallet.domain.UserToken.UserTokenPrimaryKey;;

public interface UserTokenRepository extends JpaRepository<UserToken, UserTokenPrimaryKey> {

	UserToken findOneByPrimaryKey(UserTokenPrimaryKey primaryKey);

	@Query("select ut from UserToken ut where ut.primaryKey.address=:address")
	List<UserToken> getByAddress(@Param("address") String address);

	@Query("select ut from UserToken ut where ut.primaryKey.address=:address and ut.primaryKey.contractAddress=:contractAddress")
	List<UserToken> findByPrimaryKey(@Param("address") String address, @Param("contractAddress") String contractAddress);

	List<UserToken> findAllByPrimaryKeyAddress(String address);
}
