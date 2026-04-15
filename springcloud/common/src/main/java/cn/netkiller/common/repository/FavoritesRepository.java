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
import cn.netkiller.common.domain.Favorites;

@Repository
public interface FavoritesRepository extends CrudRepository<Favorites, Integer> {
    
    @Query("SELECT f FROM Favorites f WHERE f.member.id = ?1")
	Page<Favorites> findByMemberIdOrderByIdDesc(int memberId, Pageable pageable);

	@Query("SELECT count(id) FROM Favorites f WHERE f.member.id = ?1")
	int countByMemberId(int memberId);

//	@Transactional
//	@Modifying
//	@Query("DELETE FROM Favorites f WHERE f.member.id = ?1 AND f.articleId = ?2")
//	void deleteByMemberIdAndArticleId(int memberId, int articleId);

//	@Transactional
//	@Modifying
//	@Query("delete from Favorites f where f.member.id = :memberId")
//	public void deleteByMemberId(@Param("memberId") int memberId);
//
//	@Query("SELECT count(id) FROM Favorites f WHERE f.member.id = ?1 and f.article.id = ?2.id")
//	int countByMemberIdAndArticle(int memberId, Article article);
}
