package cn.netkiller.common.repository;

import org.springframework.data.repository.CrudRepository;

import cn.netkiller.common.domain.StatisticsHistory;
import cn.netkiller.common.domain.StatisticsHistory.StatisticsType;

public interface StatisticsHistoryRepository extends CrudRepository<StatisticsHistory, Integer> {

	public int countByMemberIdAndArticleIdAndType(int memberId, int articleId, StatisticsType type);

	public int countByMemberIdAndArticleId(int memberId, int articleId);

}
