package cn.netkiller.wallet.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.netkiller.wallet.domain.Token;

@Repository
public interface TokenRepository extends CrudRepository<Token, Integer> {

	Token findOneByContractAddress(String contractAddress);

}
