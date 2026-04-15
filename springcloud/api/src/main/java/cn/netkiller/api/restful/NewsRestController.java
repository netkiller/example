package cn.netkiller.api.restful;
//package api.restful;
//
//import com.google.gson.Gson;
//import common.pojo.ResponseRestful;
//import org.apache.http.client.utils.DateUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//@RestController
//@RequestMapping("/news")
//public class NewsRestController {
//
//    private final Logger logger = LoggerFactory.getLogger(NewsRestController.class);
//
//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;
//
//    private String key = "FASTNEWS_DATA_STORE_KEY";
//
//    // 指定开始位置与结束位置
//    @RequestMapping(value = "/flash/{from}/{to}")
//    public ResponseRestful flash(@PathVariable("from") long from, @PathVariable("to") long to) {
//
//        Set<String> newsSet = this.redisTemplate.opsForZSet().reverseRange(this.key, from, to);
//
//        List<HashMap<String, String>> newsList = new ArrayList<HashMap<String, String>>();
//
//        for (String news : newsSet) {
//            Gson gson = new Gson();
//            Map<String, String> map = new HashMap<String, String>();
//            map = gson.fromJson(news, map.getClass());
//
//            String date = map.get("pubDate");
//            String title = map.get("title");
//
//            HashMap<String, String> newMap = new HashMap<String, String>();
//            newMap.put("date", date);
//            newMap.put("title", title);
//            newsList.add(newMap);
//        }
//
//        if (newsSet == null) {
//            return new ResponseRestful(false, 10, "没有查询到结果", newsSet);
//        }
//        return new ResponseRestful(true, 0, "返回数据: " + newsList.size() + " 条", newsList);
//    }
//
//    // 获取页数
//    @RequestMapping(value = "/flash/count")
//    public ResponseRestful count() {
//        long count = this.redisTemplate.opsForZSet().size(this.key);
//        return new ResponseRestful(true, 0, "返回数据: " + count + " 条", count);
//    }
//
//    // 翻页
//    @RequestMapping(value = "/flash/page/{page}/{size}")
//    public ResponseRestful pageable(@PathVariable("page") long page, @PathVariable("size") long size) {
//        page = page * size;
//        size = page + (size - 1);
//        return this.flash(page, size);
//    }
//
//    @RequestMapping(value = "/flash/more/{size}")
//    public ResponseRestful flash(@PathVariable("size") long size) {
//        return this.flash(size, new Date());
//    }
//
//    // 加载更多,第一次调用date传当前时间，非第一次传上一次请求的最后一条数据的date字段的值
//    @RequestMapping(value = "/flash/more/{size}/{date}")
//    public ResponseRestful flash(@PathVariable("size") long size, @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date) {
//        //构造返回的list
//        List<HashMap<String, String>> newsList = new ArrayList<HashMap<String, String>>();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        for (int i = 0; ; i++) {
//            Set<String> newsSet = this.redisTemplate.opsForZSet().reverseRange(this.key, i * (size * 5), (i + 1) * (size * 5) - 1);
//            for (String news : newsSet) {
//                Gson gson = new Gson();
//                Map<String, String> map = new HashMap<String, String>();
//                map = gson.fromJson(news, map.getClass());
//
//                String title = map.get("title");
//                try {
//                    Date pubDate = sdf.parse(map.get("pubDate"));
//                    if (date.compareTo(pubDate) == 1) {//上一次请求数据最后一条的发布时间要大于本条数据的发布时间
//                        HashMap<String, String> newMap = new HashMap<String, String>();
//                        newMap.put("date", map.get("pubDate"));
//                        newMap.put("title", title);
//                        newsList.add(newMap);
//                    }
//
//                    //返回条数已满，直接返回
//                    if (newsList.size() >= size) {
//                        return new ResponseRestful(true, 0, "返回数据: " + newsList.size() + " 条", newsList);
//                    }
//                } catch (ParseException e) {
//                    logger.info("【查询快讯接口】日期格式转换错误");
//                    e.printStackTrace();
//                }
//            }
//
//            //redis查询出来的数据量小于size，说明已经是最后一页，结束循环
//            if (newsSet.size() < size) {
//                return new ResponseRestful(true, 0, "返回数据: " + newsList.size() + " 条", newsList);
//            }
//        }
//    }
//
//}
