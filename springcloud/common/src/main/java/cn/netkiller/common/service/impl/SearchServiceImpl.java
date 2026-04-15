package cn.netkiller.common.service.impl;
//package api.service.impl;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import org.elasticsearch.action.search.SearchRequestBuilder;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.action.search.SearchType;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.search.sort.SortOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//
//import com.google.gson.Gson;
//
//import api.service.CmsService;
//import api.service.SearchService;
//
//@Service
//public class SearchServiceImpl implements SearchService {
//
//    @Autowired
//    private TransportClient client;
//    
//    @Autowired
//    private SearchService searchService;
//
//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;
//    
//    @Autowired
//    private CmsService cmsService;
//
//    private String key = "FASTNEWS_DATA_STORE_KEY";
//    
//    @Override
//    public List<Map<String, Object>> listTagsBySiteIdAndTags(String siteId, List<String> tags) {
//        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//        for (String tag : tags) {
//            SearchRequestBuilder searchRequestBuilder = client.prepareSearch("information").setTypes("article").setSearchType(SearchType.DFS_QUERY_THEN_FETCH).addSort("ctime", SortOrder.DESC);
//
//            searchRequestBuilder.setFetchSource(new String[]{"id", "title", "description", "image", "ctime", "source"}, null);
//
//            searchRequestBuilder.setQuery(QueryBuilders.matchQuery("tags", tag));
//            searchRequestBuilder.setPostFilter(QueryBuilders.termQuery("site_id", siteId)).setFrom(0).setSize(1).setExplain(true);
//
//            SearchResponse response = searchRequestBuilder.get();
//            SearchHit[] hits = response.getHits().getHits();
//            if(hits.length > 0){
//                final SearchHit hit = hits[0];
//                hit.getSourceAsMap().put("tag", tag);
//                list.add(hit.getSourceAsMap());
//            }else{
//                list.add(new HashMap<>());
//            }
//
//        }
//
//        return list;
//    }
//
//    @Override
//    public List<Map<String, Object>> listTypeBySiteIdAndTypes(String siteId, List<String> types) {
//        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//        Gson gson = new Gson();
//        for (String type : types) {
//            Map<String, Object> oMap = new HashMap<>();
//            if ("快讯".equals(type)) {
//                Set<String> newsSet = this.redisTemplate.opsForZSet().reverseRange(this.key, 0, 0);
//                HashMap<String, String> map = new HashMap<String, String>();
//                for (String news : newsSet) {
//                    map = gson.fromJson(news, map.getClass());
//                }
//                oMap.put(type, map);
//                list.add(oMap);
//            } else if ("财经日历".equals(type)) {
//                String str = cmsService.financeList(new HashMap<>());
//                Map<String, Object> map = new HashMap<>();
//                map = gson.fromJson(str, map.getClass());
//                Map<String, Object> data = (Map<String, Object>)map.get("data");
//                List<Map<String, Object>> financeData = (List<Map<String, Object>>)data.get("financeData");
//                Map<String, Object> tMap = null;
//                if(null != financeData && !financeData.isEmpty()){
//                    tMap = financeData.get(0);
//                }
//
//                oMap.put(type, tMap);
//                list.add(oMap);
//            } else {
//                List<String> tags = new ArrayList<>();
//                tags.add(type);
//                List<Map<String, Object>> oList = searchService.listTagsBySiteIdAndTags(siteId, tags);
//                Map<String, Object> tMap = oList.get(0);
//                oMap.put(type, tMap);
//                list.add(oMap);
//            }
//        }
//        return list;
//    }
//    
//    
//}
