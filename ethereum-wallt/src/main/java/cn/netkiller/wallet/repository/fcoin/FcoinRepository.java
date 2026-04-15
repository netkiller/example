package cn.netkiller.wallet.repository.fcoin;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import cn.netkiller.wallet.domain.fcoin.Fcoin;;

public interface FcoinRepository extends CrudRepository<Fcoin, String> {

	Fcoin findOneByAddress(String address);

	int countByAirdropFalse();

	List<Fcoin> findByAirdrop(boolean airdrop, Pageable pageable);

}
