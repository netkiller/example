package cn.netkiller.common.feign;

import java.util.Date;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.netkiller.common.model.Comment;
import cn.netkiller.common.pojo.ResponseRestful;

@FeignClient("restful-api-service")
public interface Cms {

	@RequestMapping("/cms/tag/{siteId}")
	public ResponseRestful tag(@PathVariable("siteId") int siteId);

	@RequestMapping("/cms/article/count/{siteId}/{tagId}/{date}")
	public ResponseRestful countUpdateBySiteIdTagIdDate(@PathVariable("siteId") int siteId, @PathVariable("tagId") int tagId, @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date);

	@RequestMapping("/cms/comment/list/{articleId}")
	public ResponseRestful commentList(@PathVariable("articleId") int articleId);

	@RequestMapping("/cms/comment/{commentId}")
	public ResponseRestful comment(@PathVariable("commentId") int commentId);

	@RequestMapping(value = "/cms/comment/add/{siteId}/{articleId}", method = RequestMethod.POST)
	public ResponseRestful commentAdd(@PathVariable("siteId") int siteId, @PathVariable("articleId") int articleId, @RequestBody Comment comment);

	@RequestMapping("/cms/synchronizer/{siteId}")
	public ResponseRestful synchronizerBySiteId(@PathVariable("siteId") int siteId);

	@RequestMapping(value = "/cms/synchronizer/delete/{id}", method = RequestMethod.DELETE)
	public ResponseRestful synchronizerDelete(@PathVariable("id") int id);

	@PostMapping("/cms/finance/list")
	public String financeList(@RequestBody Map<String, String> map);

	@GetMapping("/cms/finance/detail/{dataId}")
	public String financeDetail(@PathVariable("dataId") String dataId);
	
	@GetMapping("/cms/newsContent/{contentId}")
    public String newsContent(@PathVariable("contentId") String contentId);
	
}
