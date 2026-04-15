package cn.netkiller.common.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import cn.netkiller.common.domain.Article;
import cn.netkiller.common.domain.Comment;
import cn.netkiller.common.domain.Favorites;
import cn.netkiller.common.domain.Member;
import cn.netkiller.common.domain.RecentRead;
import cn.netkiller.common.domain.Statistics;
import cn.netkiller.common.pojo.ResponseRestful;
import cn.netkiller.common.repository.CommentRepository;
import cn.netkiller.common.repository.FavoritesRepository;
import cn.netkiller.common.repository.MemberRepository;
import cn.netkiller.common.repository.RecentReadRepostitory;
import cn.netkiller.common.repository.StatisticsRepository;
import cn.netkiller.common.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private RecentReadRepostitory recentReadRepostitory;

    @Autowired
    private CommentRepository commentRepository;

//    @Autowired
//    private FavoritesRepository favoritesRepository;

    @Autowired
    private MemberRepository memberRepository;

//    @Autowired
//    private StatisticsRepository statisticsRepostitory;

//    @Autowired
//    private StatisticsService statisticsService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PersistenceContext
    private EntityManager entityManager;

//    public ResponseEntity<ResponseRestful> recentAdd(RecentRead recentRead) {
//        if (recentReadRepostitory.countByMemberIdAndArticleId(recentRead.getMemberId(), recentRead.getArticleId()) == 0) {
//            recentReadRepostitory.save(recentRead);
//        }
//        int count = recentReadRepostitory.countByMemberId(recentRead.getMemberId());
//        return new ResponseEntity<ResponseRestful>(new ResponseRestful(true, 1, "添加数据成功", count), HttpStatus.OK);
//    }
//
//    public ResponseRestful recentList(int memberId, Pageable pageable) {
//        Page<RecentRead> recentRead = recentReadRepostitory.findByMemberIdOrderByIdDesc(memberId, pageable);
//        if (recentRead == null) {
//            return new ResponseRestful(false, 1, "未查找到数据", null);
//        }
//        return new ResponseRestful(true, 1, "查找到数据", recentRead);
//    }

//    public ResponseRestful recentDelete(int memberId, int articleId) {
//        if (articleId == 0) {
//            recentReadRepostitory.deleteByMemberId(memberId);
//        } else {
//        		Article article = new Article();
//        		article.setId(articleId);
//            recentReadRepostitory.deleteByMemberIdAndArticle(memberId, article);
//        }
//        int count = recentReadRepostitory.countByMemberId(memberId);
//        return new ResponseRestful(true, 1, "删除数据成功", count);
//    }

    public ResponseRestful commentAdd(Comment comment) {
        // if(commentRepository.countByMemberIdAndArticleId(comment.getMember().getId(),
        // comment.getArticleId()) == 0) {
        commentRepository.save(comment);
        // }
        // Page<Comment> commentList = commentRepository.findByArticleId(comment.getArticleId());
        return new ResponseRestful(true, 1, "添加数据成功", null);
    }

    public ResponseRestful commentList(int articleId, Pageable pageable) {
    		Article article = new Article();
		article.setId(articleId);
        Page<Comment> comment = commentRepository.findByArticle(article, pageable);
        if (comment == null) {
            return new ResponseRestful(false, 1, "未查找到数据", null);
        }
        return new ResponseRestful(true, 1, "查找到数据", comment);
    }

    /**
     * 添加收藏
     */
//    public ResponseRestful favoritesAdd(int memberId, int articleId) {
//    		Article article = new Article();
//    		article.setId(articleId);
//        if (favoritesRepository.countByMemberIdAndArticle(memberId, article) == 0) {
//            Favorites favorites = new Favorites();
//            Article article1 = new Article();
//            article.setId(articleId);
//            favorites.setArticle(article1);
//
//            // 查询文章详细信息
//            Map<String, Object> map = new HashMap<>();
//            try {
//                String sql = "select t.title, t.source, t.image from cms.article t where t.id=?";
//                map = jdbcTemplate.queryForMap(sql, articleId);
//            } catch (Exception e) {
//                return new ResponseRestful(false, 1, "文章不存在", null);
//            }
//            favorites.setTitle((String) map.get("title"));
//            favorites.setSource((String) map.get("source"));
//            favorites.setImage((String) map.get("image"));
//
//            // 查询用户详细信息
//            Member member = memberRepository.findById(memberId);
//            if (null != member) {
//                favorites.setMember(member);
//            } else {
//                return new ResponseRestful(false, 1, "用户不存在", null);
//            }
//
//            favoritesRepository.save(favorites);
//
//            // 收藏数量+1
//            Statistics statistics = statisticsRepostitory.findByArticleId(articleId);
//            statistics.setFavorites(statistics.getFavorites() + 1);
//            statisticsRepostitory.save(statistics);
//
//        }
//        int count = favoritesRepository.countByMemberId(memberId);
//
//        return new ResponseRestful(true, 1, "收藏成功", count);
//    }

    /**
     * 查询收藏列表
     */
//    @Override
//    public ResponseRestful favoritesList(int memberId, int page, int size) {
//
//        Sort sort = new Sort(Sort.Direction.DESC, "id");
//        Pageable pageable = new PageRequest(page, size, sort);
//
//        Page<Favorites> favorites = favoritesRepository.findByMemberIdOrderByIdDesc(memberId, pageable);
//        if (favorites == null || favorites.getContent().isEmpty()) {
//            return new ResponseRestful(true, 1, "未查找到数据", null);
//        } else {
//            // 根据文章id查询文章统计状态
//            List<Integer> articleIdList = new ArrayList<>();
//            for (Favorites f : favorites.getContent()) {
//                articleIdList.add(f.getArticle().getId());
//            }
//
//            List<Statistics> statisticsList = statisticsService.findByArticleIds(articleIdList);
//            for (Favorites f : favorites.getContent()) {
//                for (Statistics statistics : statisticsList) {
//                    if (f.getArticle().getId() == statistics.getArticle().getId()) {
//                        f.setBrowses(statistics.getBrowses());
//                        f.setLikes(statistics.getLikes());
//                        f.setComments(statistics.getComments());
//                        f.setFavorites(statistics.getFavorites());
//                    }
//                }
//            }
//
//        }
//
//        return new ResponseRestful(true, 1, "查找到数据", favorites.getContent());
//    }

    /**
     * 删除收藏
     */
//    public ResponseRestful favoritesDelete(int memberId, int articleId) {
//        if (articleId == -1) {
//            favoritesRepository.deleteByMemberId(memberId);
//        } else {
//        		Article article = new Article();
//        		article.setId(articleId);
//            favoritesRepository.countByMemberIdAndArticle(memberId, article);
//        }
//
//        // 收藏数量-1
//        Statistics statistics = statisticsRepostitory.findByArticleId(articleId);
//        statistics.setFavorites(statistics.getFavorites() - 1);
//        statisticsRepostitory.save(statistics);
//
//        int count = favoritesRepository.countByMemberId(memberId);
//        return new ResponseRestful(true, 1, "取消收藏成功", count);
//    }

}
