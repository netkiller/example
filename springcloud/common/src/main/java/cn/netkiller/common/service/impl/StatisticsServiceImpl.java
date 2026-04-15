package cn.netkiller.common.service.impl;
//package api.service.impl;
//
//import java.util.List;
//
//import javax.transaction.Transactional;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import api.service.StatisticsService;
//import common.domain.Article;
//import common.domain.Member;
//import common.domain.Statistics;
//import common.domain.StatisticsHistory;
//import common.domain.StatisticsHistory.StatisticsType;
//import common.pojo.ResponseRestful;
//import common.repository.FavoritesRepository;
//import common.repository.StatisticsHistoryRepository;
//import common.repository.StatisticsRepository;
//
//@Service
//public class StatisticsServiceImpl implements StatisticsService {
//    private static final Logger logger = LoggerFactory.getLogger(StatisticsService.class);
//
//    @Autowired
//    private StatisticsRepository statisticsRepostitory;
//
//    @Autowired
//    private StatisticsHistoryRepository statisticsHistoryRepostitory;
//    
//    @Autowired
//    private  FavoritesRepository favoritesRepository;
//    
//
//    public ResponseRestful get(int memberId, int articleId) {
//        Statistics statistics = statisticsRepostitory.findByArticleId(articleId);
//        if (statistics == null) {
//            statistics = new Statistics();
//            return new ResponseRestful(true, 0, "文章ID不存在", statistics);
//        }
//
//        // 判断用户是否已经点过赞
//        if (memberId > 0 && statisticsHistoryRepostitory.countByMemberIdAndArticleIdAndType(memberId, articleId, StatisticsType.LIKE) > 0) {
//            statistics.setLikeFlag(1);
//        }
//        
//        // 判断用户是否已收藏
////        if (memberId > 0 && favoritesRepository.countByMemberIdAndArticleId(memberId, articleId) > 0) {
////            statistics.setFavoriteFlag(1);
////        }
//
//        return new ResponseRestful(true, 1, "查找到数据", statistics);
//    }
//
//    @Transactional
//    public ResponseRestful set(StatisticsHistory.StatisticsType type, int memberId, int articleId) {
//        int count = statisticsHistoryRepostitory.countByMemberIdAndArticleIdAndType(memberId, articleId, type);
//        Statistics statistics = statisticsRepostitory.findByArticleId(articleId);
//        logger.info("set statisticsHistoryRepostitory count: {}", count);
//        if (count >= 1) {
//            return new ResponseRestful(false, 0, "请勿重复操作", statistics);
//        }
//        if (statistics == null) {
//            statistics = new Statistics();
//            Article article = new Article();
//            article.setId(articleId);
//            statistics.setArticle(article);
//        }
//
//        // 设置请求类型，方便前端判断取值
//        statistics.setType(type);
//
//        if (type == StatisticsHistory.StatisticsType.LIKE) {
//            // 点赞数量+1
//            statistics.setLikes(statistics.getLikes() + 1);
//        } else if (type == StatisticsHistory.StatisticsType.BROWSE) {
//            // 浏览数量+1
//            statistics.setBrowses(statistics.getBrowses() + 1);
//        } else if (type == StatisticsHistory.StatisticsType.COMMENT) {
//            // 评论数量+1
//            statistics.setComments(statistics.getComments() + 1);
//        } else if (type == StatisticsHistory.StatisticsType.FAVORITE) {
//            // 收藏数量+1
//            statistics.setFavorites(statistics.getFavorites() + 1);
//        }
//        statisticsRepostitory.save(statistics);
//
//        StatisticsHistory statisticsHistory = new StatisticsHistory();
//        statisticsHistory.setMember(new Member(memberId));
//        statisticsHistory.setArticleId(articleId);
//        statisticsHistory.setType(type);
//        statisticsHistoryRepostitory.save(statisticsHistory);
//
//        return new ResponseRestful(true, 1, "数据更新成功", statistics);
//    }
//
////	@Override
////	public List<Statistics> findByArticleIds(List<Integer> articleIdList) {
////		// TODO Auto-generated method stub
////		return null;
////	}
//
////    @Override
////    public List<Statistics> findByArticleIds(List<Integer> articleIdList) {
////        return statisticsRepostitory.findByArticleIds(articleIdList);
////    }
//    
//    
//}
