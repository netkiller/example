package cn.netkiller.common.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.netkiller.common.domain.Member;

@Repository
public interface MemberRepository extends CrudRepository<Member, Integer> {

	Member findByMobile(String mobile);

	Member findByWechat(String wechat);

	Member findById(int id);

}
