package cn.netkiller.api.restful;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.netkiller.common.domain.CmsTrash;
import cn.netkiller.common.model.Comment;
import cn.netkiller.common.model.Tag;
import cn.netkiller.common.pojo.ResponseRestful;
import cn.netkiller.common.repository.CmsTrashRepository;
import cn.netkiller.common.service.CmsService;

@RestController
@RequestMapping("/cms")
public class CmsRestController {
    private static final Logger logger = LoggerFactory.getLogger(CmsRestController.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${cms.site_id}")
    private int siteId;

    @Autowired
    private CmsTrashRepository cmsTrashRepository;

    @Autowired
    private CmsService cmsService;

    @RequestMapping(value = "/article/count/update/{tagId}/{articleId}", method = RequestMethod.GET, produces = { "application/xml", "application/json" })
    public ResponseRestful updateCount(@PathVariable int tagId, @PathVariable int articleId) {
        String sql = "";
        int count = 0;
        if (tagId == 0) {
            sql = "SELECT count(*) FROM cms.article WHERE id > ?";
            count = jdbcTemplate.queryForObject(sql, new Object[] { articleId }, Integer.class);
        } else {
            sql = "SELECT count(*) FROM cms.article a, cms.article_has_tag t WHERE a.id = t.article_id AND t.tag_id = ? AND id > ?";
            count = jdbcTemplate.queryForObject(sql, new Object[] { tagId, articleId }, Integer.class);
        }
        logger.info("updateCount {}, {} SQL: {}", tagId, articleId, sql);

        return new ResponseRestful(true, 1, "文章更新", count);
    }

    // 获取文章更新数量
    @RequestMapping(value = "/article/count/latest/{tagId}/{date}", method = RequestMethod.GET, produces = { "application/xml", "application/json" })
    public ResponseRestful countLatest(@PathVariable int tagId, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date) {
        int count = this.countBySiteIdAndTagIdAndCdate(this.siteId, tagId, date);
        logger.info("countLatest {}, {}", tagId, date);
        return new ResponseRestful(true, 1, "文章更新", count);
    }

    // 根据站点ID和标签ID获取，指定日期指当前时间的文章更新数量
    @RequestMapping(value = "/article/count/{siteId}/{tagId}/{date}", method = RequestMethod.GET, produces = { "application/xml", "application/json" })
    public ResponseRestful countUpdateBySiteIdTagIdDate(@PathVariable("siteId") int siteId, @PathVariable("tagId") int tagId, @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date) {
        int count = this.countBySiteIdAndTagIdAndCdate(siteId, tagId, date);
        logger.info("countLatest {}, {}", tagId, date);
        return new ResponseRestful(true, 1, "文章更新", count);
    }

    private int countBySiteIdAndTagIdAndCdate(int siteId, int tagId, Date cDate) {
        Timestamp timestamp = new Timestamp(cDate.getTime());
        String sql = "";
        int count = 0;
        if (tagId == 0) {
            sql = "SELECT count(*) FROM cms.article WHERE site_id = ? AND ctime > ?";
            count = jdbcTemplate.queryForObject(sql, new Object[] { siteId, timestamp }, Integer.class);
        } else {
            sql = "SELECT count(*) FROM cms.article a, cms.article_has_tag t WHERE a.site_id = ? AND a.id = t.article_id AND t.tag_id = ? AND a.ctime > ?";
            count = jdbcTemplate.queryForObject(sql, new Object[] { siteId, tagId, timestamp }, Integer.class);
        }
        logger.info("countBySiteIdAndTagIdAndCdate({}, {}, {} ) SQL: {}", siteId, tagId, timestamp, sql);
        return (count);
    }

    @RequestMapping("/tag/{siteId}")
    public ResponseRestful tag(@PathVariable("siteId") int siteId) {
        List<Tag> tags = new ArrayList<Tag>();
        List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();

        String sql = "";
        if (siteId == 0) {
            sql = "SELECT id,name,site_id FROM cms.tag";
            rows = jdbcTemplate.queryForList(sql);
        } else {
            sql = "SELECT id,name,site_id FROM cms.tag WHERE site_id = ?";
            rows = jdbcTemplate.queryForList(sql, new Object[] { siteId });
        }

        for (Map<String, Object> row : rows) {
            Tag tag = new Tag();
            tag.setId((Integer) row.get("id"));
            tag.setName((String) row.get("name"));
            tag.setSiteId((Integer) row.get("site_id"));
            tags.add(tag);
        }
        logger.info("tag {} SQL: {}", siteId, sql);
        return new ResponseRestful(true, tags.size(), "标签", tags);
    }

    @RequestMapping("/comment/list/{articleId}")
    public ResponseRestful commentList(@PathVariable("articleId") int articleId) {
        List<Comment> comments = new ArrayList<Comment>();
        List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();

        String sql = "SELECT * FROM cms.comment WHERE article_id = ?";
        rows = jdbcTemplate.queryForList(sql, new Object[] { articleId });

        if (rows == null) {
            return new ResponseRestful(false, 0, "无数据", null);
        }

        for (Map<String, Object> row : rows) {
            Comment comment = new Comment();
            comment.setId((Integer) row.get("id"));
            comment.setArticleId((Integer) row.get("article_id"));
            comment.setMemberId((Integer) row.get("member_id"));
            comment.setUsername((String) row.get("username"));
            comment.setNickname((String) row.get("nickname"));
            comment.setPicture((String) row.get("picture"));
            comment.setCtime((Date) row.get("ctime"));
            comments.add(comment);
        }
        logger.info("articleId {} SQL: {}", articleId, sql);
        return new ResponseRestful(true, comments.size(), "返回评论列表", comments);
    }

    @RequestMapping("/comment/{commentId}")
    public ResponseRestful comment(@PathVariable("commentId") int commentId) {

        String sql = "SELECT * FROM cms.comment WHERE id = ?";

        Comment comment = jdbcTemplate.queryForObject(sql, new Object[] { commentId }, (resultSet, i) -> {
            Comment tmp = new Comment();
            tmp.setId(resultSet.getInt("id"));
            tmp.setArticleId(resultSet.getInt("article_id"));
            tmp.setMemberId(resultSet.getInt("member_id"));
            tmp.setUsername(resultSet.getString("username"));
            tmp.setNickname(resultSet.getString("nickname"));
            tmp.setPicture(resultSet.getString("picture"));
            tmp.setCtime(resultSet.getDate("ctime"));
            return tmp;
        });

        logger.info("commentId {} SQL: {}", commentId, sql);

        if (comment == null) {
            return new ResponseRestful(false, 0, "没有找到数据", null);
        }
        return new ResponseRestful(true, 1, "返回评论数据", comment);
    }

    @RequestMapping(value = "/comment/add/{siteId}/{articleId}", method = RequestMethod.POST)
    public ResponseRestful commentAdd(@PathVariable("siteId") int siteId, @PathVariable("articleId") int articleId, @RequestBody Comment comment) {
        String sql = "insert into cms.jc_comment(" + "content_id, " + "site_id," + "create_time," + "text," + "cms_user_id," + "cms_user_name," + "nickname," + "picture" + ") values(?,?,now(),?,?,?,?,?)";

        int count = jdbcTemplate.update(sql, comment.getArticleId(), siteId, comment.getContent(), comment.getMemberId(), comment.getUsername(), comment.getNickname(), comment.getPicture());

        return new ResponseRestful(true, count, "评论添加成功", comment);
    }

    /* 根据articleId查询评论数量,多个id用都好隔开 */
    @RequestMapping("/comment/count/{articleIds}")
    public ResponseRestful commentCountByArticleIds(@PathVariable("articleIds") String articleIds) {
        String[] articleIdArr = articleIds.split(",");
        List<Integer> articleIdList = new ArrayList<>();
        for (String articleId : articleIdArr) {
            if (null != articleId && !articleId.trim().equals("")) {

                articleIdList.add(Integer.parseInt(articleId));
            }
        }
        Map<Integer, Long> map = cmsService.findCommentNum(articleIdList);
        if (map == null) {
            return new ResponseRestful(false, 0, "无数据", null);
        }
        return new ResponseRestful(true, map.size(), "返回评论列表", map);
    }

    /* 创富需求，解决CMS删除，更新后通告 */
    @RequestMapping("/synchronizer/{siteId}")
    public ResponseRestful synchronizerBySiteId(@PathVariable("siteId") int siteId) {
        List<CmsTrash> trash = cmsTrashRepository.findBySiteId(siteId);
        return new ResponseRestful(true, trash.size(), "搜索引擎同步时状态", trash);
    }

    @RequestMapping(value = "/synchronizer/delete/{id}", method = RequestMethod.DELETE)
    public ResponseRestful synchronizerDelete(@PathVariable("id") int id) {
        try {
            CmsTrash cmsTrash = new CmsTrash();
            cmsTrash.setId(id);
            cmsTrashRepository.delete(cmsTrash);
        } catch (Exception e) {
            return new ResponseRestful(false, 0, e.getMessage(), null);
        }
        return new ResponseRestful(true, 1, "删除成功", null);
    }

    /* 返回财经日历列表 */
    /* % curl -H "Content-Type: application/json" -X POST -d '{"releaseTime":"2017-11-08"}' http://localhost:8440/cms/finance/list.json */
    @PostMapping("/finance/list")
    public String financeList(@RequestBody Map<String, String> map) {
        return cmsService.financeList(map);
    }

    @GetMapping("/finance/detail/{dataId}")
    public String financeDetail(@PathVariable("dataId") String dataId) {
        return cmsService.financeDetail(dataId);
    }

    @GetMapping("/newsContent/{contentId}")
    public String newsContent(@PathVariable("contentId") String contentId) {
        return cmsService.newsContent(contentId);
    }

}