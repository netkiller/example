package cn.netkiller.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import cn.netkiller.common.domain.Statistics;

public interface StatisticsRepository extends CrudRepository<Statistics, Integer>{

//	Statistics findByArticleId(int articleId);
//
//	@Query("select s from Statistics s where s.articleId in (:articleIds)")
//    List<Statistics> findByArticleIds(@Param("articleIds") List<Integer> articleIds);
//	

}
