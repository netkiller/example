package cn.netkiller.schedule.repository;

import org.springframework.stereotype.Repository;

import cn.netkiller.common.domain.Article;
import cn.netkiller.schedule.repository.ViewRepository;

@Repository
public interface ArticleRepository extends ViewRepository<Article, Integer> {

	Iterable<Article> findBySiteId(int siteId);

	Iterable<Article> findBySiteIdAndIdGreaterThanOrderByIdAsc(int siteId, int id);

}
