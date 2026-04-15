package feign.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.netkiller.common.feign.Search;
import cn.netkiller.common.pojo.ResponseRestful;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchRestController {

    @Autowired
    private Search search;

    @RequestMapping("/list")
    public ResponseRestful list() {
        return search.list();
    }

    @RequestMapping("/article/list/{siteId}/{from}/{size}")
    public ResponseRestful listBySiteIdAndTags(@PathVariable("siteId") String siteId, @PathVariable("from") int from, @PathVariable("size") int size, @RequestParam(value = "tags", required = false) String tags) {
        return search.listBySiteIdAndTags(siteId, from, size, tags);
    }

    @RequestMapping("/article/list/comment/{siteId}/{from}/{size}")
    public ResponseRestful listCommentBySiteIdAndTags(@PathVariable("siteId") String siteId, @PathVariable("from") int from, @PathVariable("size") int size, @RequestParam(value = "tags", required = false) String tags) {
        return search.listCommentBySiteIdAndTags(siteId, from, size, tags);
    }

    @RequestMapping("/article/{articleId}")
    public ResponseRestful read(@PathVariable String articleId) {
        return search.read(articleId);
    }

    @RequestMapping(value = "/article/list/tags/{siteId}", method = RequestMethod.POST)
    public ResponseRestful listTagsBySiteIdAndTags(@PathVariable("siteId") String siteId, @RequestBody List<String> tags) {
        return search.listTagsBySiteIdAndTags(siteId, tags);
    }

    //取出每个类型中的最新的一条数据
    @PostMapping(value = "/article/list/type/{siteId}")
    public ResponseRestful listTypeBySiteIdAndTypes(@PathVariable("siteId") String siteId, @RequestBody List<String> types) {
        return search.listTypeBySiteIdAndTypes(siteId, types);
    }
}
