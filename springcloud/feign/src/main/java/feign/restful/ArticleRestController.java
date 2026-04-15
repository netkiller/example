package feign.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.netkiller.common.domain.Favorites;
import cn.netkiller.common.feign.ArticleFeignClient;
import cn.netkiller.common.pojo.ResponseRestful;

@RestController
@RequestMapping("/article")
public class ArticleRestController {
	@Autowired
	private ArticleFeignClient articleFeignClient ;
	
	/**
	 * 添加收藏
	 * @param favorites
	 * @return
	 */
	@GetMapping("/favorites/add/{memberId}/{articleId}")
    public ResponseRestful favoritesAdd(@PathVariable("memberId") int memberId, @PathVariable("articleId") int articleId) {
		return articleFeignClient.favoritesAdd(memberId, articleId);
	}
	
	
	/**
	 * 查询收藏列表
	 * @param memberId
	 * @param pageable
	 * @return
	 */
	@GetMapping(value = "/favorites/list/{memberId}/{page}/{size}")
    public ResponseRestful favoritesList(@PathVariable("memberId") int memberId, @PathVariable("page") int page, @PathVariable("size") int size){
		return articleFeignClient.favoritesList(memberId, page, size);
	}

	/**
	 * 删除收藏
	 * @param memberId
	 * @param articleId
	 * @return
	 */
	@GetMapping("/favorites/delete/{memberId}/{articleId}")
	public ResponseRestful favoritesDelete(@PathVariable("memberId") int memberId, @PathVariable("articleId") int articleId) {
		return articleFeignClient.favoritesDelete(memberId, articleId);
	}
	
}
