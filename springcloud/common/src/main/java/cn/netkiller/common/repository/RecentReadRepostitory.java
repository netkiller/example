package cn.netkiller.common.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.netkiller.common.domain.Article;
import cn.netkiller.common.domain.RecentRead;

@Repository
public interface RecentReadRepostitory extends CrudRepository<RecentRead, Integer> {

//	Page<RecentRead> findByMemberIdOrderByIdDesc(int memberId, Pageable pageable);

//	int countByMemberId(int memberId);
	
//	@Transactional
//	@Modifying
//	@Query("DELETE FROM RecentRead r WHERE r.memberId = ?1 AND r.article_id = ?2.id")
//	void deleteByMemberIdAndArticle(int memberId, Article article);
//	void deleteByMemberIdAndArticleId(int memberId, int articleId);
	
//	@Transactional
//	@Modifying
//	@Query("delete from RecentRead where member_id = :member_id")
//	public void deleteByMemberId(@Param("member_id") int memberId);

//	int countByMemberIdAndArticleId(int memberId, int articleId);

	

}
