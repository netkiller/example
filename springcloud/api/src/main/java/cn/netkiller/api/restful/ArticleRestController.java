package cn.netkiller.api.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.netkiller.common.domain.Article;
import cn.netkiller.common.domain.Comment;
import cn.netkiller.common.domain.RecentRead;
import cn.netkiller.common.pojo.ResponseRestful;
import cn.netkiller.common.service.ArticleService;

@RestController
@RequestMapping("/article")
public class ArticleRestController {

	@Autowired
	private ArticleService articleService;

	// @Autowired
	// private ArticleRepository articleRepository;

//	@RequestMapping(value = "/recent/read/add", method = RequestMethod.POST, produces = { "application/xml", "application/json" })
//	public ResponseEntity<ResponseRestful> recentAdd(@RequestBody RecentRead recentRead) {
//		return articleService.recentAdd(recentRead);
//	}
//
//	@RequestMapping(value = "/recent/read/list/{memberId}", method = RequestMethod.GET)
//	public ResponseRestful getEntryByPageable(@PathVariable int memberId, @PageableDefault(value = 15, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable) {
//		return articleService.recentList(memberId, pageable);
//	}

//	@RequestMapping(value = "/recent/read/delete/{memberId}/{articleId}", produces = { "application/xml", "application/json" })
//	public ResponseRestful recentList(@PathVariable int memberId, @PathVariable int articleId) {
//		return articleService.recentDelete(memberId, articleId);
//	}

	@RequestMapping(value = "/comment/add", method = RequestMethod.POST, produces = { "application/xml", "application/json" })
	public ResponseRestful commentAdd(@RequestBody Comment comment) {
		return articleService.commentAdd(comment);
	}

	@RequestMapping(value = "/comment/list/{articleId}", method = RequestMethod.GET)
	public ResponseRestful commentList(@PathVariable int articleId, @PageableDefault(value = 15, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable) {
		return articleService.commentList(articleId, pageable);
	}

	// @RequestMapping(value = "/list", method = RequestMethod.GET)
	// public Iterable<Article> articleList(@PageableDefault(sort = { "id" },
	// direction = Sort.Direction.DESC) Pageable pageable) {
	// return articleRepository.findAll();
	// }

	/**
	 * 添加收藏
	 * 
	 * @param favorites
	 * @return
	 */
//	@GetMapping(value = "/favorites/add/{memberId}/{articleId}")
//	public ResponseRestful favoritesAdd(@PathVariable("memberId") int memberId, @PathVariable("articleId") int articleId) {
//		return articleService.favoritesAdd(memberId, articleId);
//	}

	/**
	 * 查询收藏列表
	 * 
	 * @param memberId
	 * @param pageable
	 * @return
	 */
//	@GetMapping(value = "/favorites/list/{memberId}/{page}/{size}")
//	public ResponseRestful favoritesList(@PathVariable("memberId") int memberId, @PathVariable("page") int page, @PathVariable("size") int size) {
//		return articleService.favoritesList(memberId, page, size);
//	}

	/**
	 * 删除收藏
	 * 
	 * @param memberId
	 * @param articleId
	 * @return
	 */
//	@GetMapping(value = "/favorites/delete/{memberId}/{articleId}")
//	public ResponseRestful favoritesDelete(@PathVariable("memberId") int memberId, @PathVariable("articleId") int articleId) {
//		Article article = new Article();
//		article.setId(articleId);
//		return articleService.favoritesDelete(memberId, article);
//	}
	
	
}
