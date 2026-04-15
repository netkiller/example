package cn.netkiller.common.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.netkiller.common.pojo.ResponseRestful;

@FeignClient("restful-api-service")
public interface ArticleFeignClient {

	@RequestMapping(value = "/article/favorites/add/{memberId}/{articleId}", method = RequestMethod.GET)
	public ResponseRestful favoritesAdd(@PathVariable("memberId") int memberId, @PathVariable("articleId") int articleId);

	@RequestMapping(value = "/article/favorites/list/{memberId}/{page}/{size}", method = RequestMethod.GET)
	public ResponseRestful favoritesList(@PathVariable("memberId") int memberId, @PathVariable("page") int page, @PathVariable("size") int size);

	@RequestMapping(value = "/article/favorites/delete/{memberId}/{articleId}", method = RequestMethod.GET)
	public ResponseRestful favoritesDelete(@PathVariable("memberId") int memberId, @PathVariable("articleId") int articleId);

}
