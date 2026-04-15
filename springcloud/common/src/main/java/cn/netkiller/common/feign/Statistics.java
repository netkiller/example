package cn.netkiller.common.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.netkiller.common.domain.StatisticsHistory;
import cn.netkiller.common.pojo.ResponseRestful;

@FeignClient("restful-api-service")
public interface Statistics {
	@RequestMapping("/article/statistics/get/{memberId}/{articleId}")
	public ResponseRestful getStatistics(@PathVariable("memberId") int memberId, @PathVariable("articleId") int articleId);
	
	@RequestMapping("/article/statistics/set/{type}/{uid}/{id}")
	public ResponseRestful setLikeCount(@PathVariable("type") StatisticsHistory.StatisticsType type, @PathVariable("uid") int uid, @PathVariable("id") int id);
}
