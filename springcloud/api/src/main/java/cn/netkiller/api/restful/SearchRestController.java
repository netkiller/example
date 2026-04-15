package cn.netkiller.api.restful;
//package api.restful;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import org.elasticsearch.action.get.GetResponse;
//import org.elasticsearch.action.search.SearchRequestBuilder;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.action.search.SearchType;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.search.sort.SortOrder;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import api.service.CmsService;
//import api.service.SearchService;
//import common.pojo.ResponseRestful;
//
//@RestController
//@RequestMapping("/search")
//public class SearchRestController {
//    private static final Logger logger = LoggerFactory.getLogger(SearchRestController.class);
//
//    @Autowired
//    private TransportClient client;
//
//    @Autowired
//    private CmsService cmsService;
//
//    @Autowired
//    private SearchService searchService;
//
//    @RequestMapping(value = "/article/{articleId}")
//    public ResponseRestful read(@PathVariable String articleId) {
//        GetResponse response = client.prepareGet("information", "article", articleId).get();
//        return new ResponseRestful(true, 1, "返回文章内容", response);
//    }
//
//    @RequestMapping(value = "/article/list")
//    public ResponseRestful list() {
//        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//        // SearchRequestBuilder searchRequestBuilder = new SearchRequestBuilder(client,
//        // null);
//        SearchResponse response = client.prepareSearch("information").setTypes("article").setFetchSource(new String[] { "id", "title", "description", "image", "ctime", "source" }, null).setSearchType(SearchType.DFS_QUERY_THEN_FETCH).addSort("ctime", SortOrder.DESC)
//                // .addFields("_source","title", "description")
//                // .setQuery(QueryBuilders.termQuery("title", "description")) // Query
//                // .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18)) // Filter
//                .setFrom(0).setSize(20).setExplain(true).get();
//        for (final SearchHit hit : response.getHits().getHits()) {
//            // System.out.println(hit.getSourceAsString());
//            hit.getSourceAsMap().remove("content");
//            list.add(hit.getSourceAsMap());
//        }
//        return new ResponseRestful(true, list.size(), "返回文章列表", list);
//    }
//
//    /*
//     * 范例：/restful/search/article/list/23/0/20.json?tags=美国
//     */
//    @RequestMapping(value = "/article/list/{siteId}/{from}/{size}")
//    public ResponseRestful listBySiteIdAndTags(@PathVariable("siteId") String siteId, @PathVariable("from") int from, @PathVariable("size") int size, @RequestParam(value = "tags", required = false) String tags) {
//        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//
//        SearchRequestBuilder searchRequestBuilder = client.prepareSearch("information").setTypes("article").setSearchType(SearchType.DFS_QUERY_THEN_FETCH).addSort("ctime", SortOrder.DESC);
//
//        searchRequestBuilder.setFetchSource(new String[] { "id", "title", "description", "image", "ctime", "source" }, null);
//        if (tags != null && !tags.equals("")) {
//            // logger.info(tags);
//            searchRequestBuilder.setQuery(QueryBuilders.matchQuery("tags", tags));
//        }
//        searchRequestBuilder.setPostFilter(QueryBuilders.termQuery("site_id", siteId)).setFrom(from).setSize(size).setExplain(true);
//
//        // logger.info(searchRequestBuilder.toString());
//        SearchResponse response = searchRequestBuilder.get();
//
//        for (final SearchHit hit : response.getHits().getHits()) {
//            list.add(hit.getSourceAsMap());
//        }
//        logger.info(tags);
//        return new ResponseRestful(true, list.size(), "返回文章列表", list);
//    }
//
//    @RequestMapping(value = "/article/list/comment/{siteId}/{from}/{size}")
//    public ResponseRestful listCommentBySiteIdAndTags(@PathVariable("siteId") String siteId, @PathVariable("from") int from, @PathVariable("size") int size, @RequestParam(value = "tags", required = false) String tags) {
//        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//
//        SearchRequestBuilder searchRequestBuilder = client.prepareSearch("information").setTypes("article").setSearchType(SearchType.DFS_QUERY_THEN_FETCH).addSort("ctime", SortOrder.DESC);
//
//        searchRequestBuilder.setFetchSource(new String[] { "id", "title", "description", "image", "ctime", "source" }, null);
//        if (tags != null && !tags.equals("")) {
//            // logger.info(tags);
//            searchRequestBuilder.setQuery(QueryBuilders.matchQuery("tags", tags));
//        }
//        searchRequestBuilder.setPostFilter(QueryBuilders.termQuery("site_id", siteId)).setFrom(from).setSize(size).setExplain(true);
//
//        // logger.info(searchRequestBuilder.toString());
//        SearchResponse response = searchRequestBuilder.get();
//
//        List<Integer> articleIds = new ArrayList<>();
//        for (final SearchHit hit : response.getHits().getHits()) {
//            // hit.getSourceAsMap().remove("content");
//            Integer articleId = (Integer) hit.getSourceAsMap().get("id");
//            articleIds.add(articleId);
//        }
//
//        // 查询文章评论数量
//        Map<Integer, Long> oMap = cmsService.findCommentNum(articleIds);
//
//        for (final SearchHit hit : response.getHits().getHits()) {
//            // logger.info(hit.getSourceAsString());
//            hit.getSourceAsMap().put("comments", oMap.get(hit.getSourceAsMap().get("id")) == null ? 0 : oMap.get(hit.getSourceAsMap().get("id")));
//            list.add(hit.getSourceAsMap());
//        }
//        logger.info(tags);
//        return new ResponseRestful(true, list.size(), "返回文章列表", list);
//    }
//
//    // 取出每个标签中的最新一条数据
//    // curl -H "Content-Type: application/json" -X POST -d '["Neo","Netkiller"]' http://localhost:8440/search/article/list/tags/22.json
//    @PostMapping(value = "/article/list/tags/{siteId}")
//    public ResponseRestful listTagsBySiteIdAndTags(@PathVariable("siteId") String siteId, @RequestBody List<String> tags) {
//        List<Map<String, Object>> list = searchService.listTagsBySiteIdAndTags(siteId, tags);
//        return new ResponseRestful(true, list.size(), "返回每个标签中的最新一条数据", list);
//    }
//
//    // 取出每个类型中的最新的一条数据
//    @PostMapping(value = "/article/list/type/{siteId}")
//    public ResponseRestful listTypeBySiteIdAndTypes(@PathVariable("siteId") String siteId, @RequestBody List<String> types) {
//        List<Map<String, Object>> list = searchService.listTypeBySiteIdAndTypes(siteId, types);
//        return new ResponseRestful(true, list.size(), "返回每个类型中的最新一条数据", list);
//    }
//
//}
