package cn.netkiller.common.repository;

import java.io.Serializable;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface ViewRepository<T, ID extends Serializable> extends Repository<T, ID>{
	T findOne(ID id);
	boolean exists(ID id);
	Iterable<T> findAll();
	long count();
}
