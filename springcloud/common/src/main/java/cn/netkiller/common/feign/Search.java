package cn.netkiller.common.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import cn.netkiller.common.pojo.ResponseRestful;

import java.util.List;

@FeignClient("restful-api-service")
public interface Search {

    @RequestMapping("/search/article/list")
    ResponseRestful list();

    @RequestMapping(value = "/search/article/list/{siteId}/{from}/{size}")
    ResponseRestful listBySiteIdAndTags(@PathVariable("siteId") String siteId, @PathVariable("from") int from, @PathVariable("size") int size, @RequestParam(value = "tags", required = false) String tags);

    @RequestMapping("/search/article/{articleId}")
    ResponseRestful read(@PathVariable("articleId") String articleId);

    @RequestMapping(value = "/search/article/list/tags/{siteId}", method = RequestMethod.POST)
    ResponseRestful listTagsBySiteIdAndTags(@PathVariable("siteId") String siteId, @RequestBody List<String> tags);

    @RequestMapping("/search/article/list/comment/{siteId}/{from}/{size}")
    ResponseRestful listCommentBySiteIdAndTags(@PathVariable("siteId") String siteId, @PathVariable("from") int from, @PathVariable("size") int size, @RequestParam(value = "tags", required = false) String tags);

    @PostMapping("/search/article/list/type/{siteId}")
    ResponseRestful listTypeBySiteIdAndTypes(@PathVariable("siteId") String siteId, @RequestBody List<String> types);
}