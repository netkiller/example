package feign.restful;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.netkiller.common.feign.Cms;
import cn.netkiller.common.model.Comment;
import cn.netkiller.common.pojo.ResponseRestful;

@RestController
@RequestMapping("/cms")
public class CmsRestController {
	@Autowired
	private Cms cms;

	@RequestMapping("/tag/{siteId}")
	public ResponseRestful tag(@PathVariable("siteId") int siteId) {
		return cms.tag(siteId);
	}

	@RequestMapping(value = "/article/count/{siteId}/{tagId}/{date}", method = RequestMethod.GET, produces = { "application/xml", "application/json" })
	public ResponseRestful countUpdateBySiteIdTagIdDate(@PathVariable("siteId") int siteId, @PathVariable("tagId") int tagId, @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date) {
		return cms.countUpdateBySiteIdTagIdDate(siteId, tagId, date);
	}

	@RequestMapping("/comment/list/{articleId}")
	public ResponseRestful commentList(@PathVariable("articleId") int articleId) {
		return cms.commentList(articleId);
	}

	@RequestMapping("/comment/{commentId}")
	public ResponseRestful comment(@PathVariable("commentId") int commentId) {
		return cms.comment(commentId);
	}

	@RequestMapping(value = "/comment/add/{siteId}/{articleId}", method = RequestMethod.POST)
	public ResponseRestful commentAdd(@PathVariable("siteId") int siteId, @PathVariable("articleId") int articleId, @RequestBody Comment comment) {
		return cms.commentAdd(siteId, articleId, comment);
	}

	@RequestMapping("/synchronizer/{siteId}")
	public ResponseRestful synchronizerBySiteId(@PathVariable("siteId") int siteId) {
		return cms.synchronizerBySiteId(siteId);
	}

	@RequestMapping(value = "/synchronizer/delete/{id}", method = RequestMethod.DELETE)
	public ResponseRestful synchronizerDelete(@PathVariable("id") int id) {
		return cms.synchronizerDelete(id);
	}

	@PostMapping("/finance/list")
	public String financeList(@RequestBody Map<String, String> map) {
		return cms.financeList(map);
	}

	@GetMapping("/finance/detail/{dataId}")
	public String financeDetail(@PathVariable("dataId") String dataId) {
		return cms.financeDetail(dataId);
	}
}
