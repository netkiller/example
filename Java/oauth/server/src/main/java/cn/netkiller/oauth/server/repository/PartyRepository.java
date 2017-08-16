package cn.netkiller.oauth.server.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.netkiller.oauth.server.model.Party;

@Repository
public interface PartyRepository extends CrudRepository<Party, Long> {
	
	Collection<Party> findAll();

}
