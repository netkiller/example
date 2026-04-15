package cn.netkiller.wallet.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.netkiller.wallet.domain.TransactionPostion;

@Repository
public interface TransactionPostionRepository extends CrudRepository<TransactionPostion, Integer> {

	TransactionPostion findOneByAddress(String address);

}
