package cn.netkiller.api.restful;
//package api.restful;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import common.domain.StatisticsHistory;
//import common.pojo.ResponseRestful;
//import api.service.StatisticsService;
//
//@RestController
//@RequestMapping("/article/statistics")
//public class StatisticsRestController {
//	@Autowired
//	private StatisticsService statisticsService;
//
//	@RequestMapping("/get/{memberId}/{articleId}")
//	public ResponseRestful getStatistics(@PathVariable("memberId") int memberId, @PathVariable("articleId") int articleId) {
//		return statisticsService.get(memberId, articleId);
//	}
//
//	@RequestMapping("/set/{type}/{uid}/{id}")
//	public ResponseRestful setLikeCount(@PathVariable("type") StatisticsHistory.StatisticsType type, @PathVariable("uid") int uid, @PathVariable("id") int id) {
//		return statisticsService.set(type, uid, id);
//	}
//}
