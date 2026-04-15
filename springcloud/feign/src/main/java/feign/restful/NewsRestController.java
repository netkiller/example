package feign.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.netkiller.common.feign.News;
import cn.netkiller.common.pojo.ResponseRestful;

import java.util.Date;

@RestController
@RequestMapping("/news")
public class NewsRestController {

	@Autowired
	private News news;

	@RequestMapping(value = "/flash/{from}/{to}")
	public ResponseRestful flash(@PathVariable("from") long from, @PathVariable("to") long to) {
		return news.flash(from, to);
	}

	@RequestMapping(value = "/flash/count")
	public ResponseRestful count() {
		return news.count();
	}

	@RequestMapping(value = "/flash/page/{page}/{size}")
	public ResponseRestful pageable(@PathVariable("page") long page, @PathVariable("size") long size) {
		return news.pageable(page, size);
	}

	@RequestMapping(value = "/flash/more/{size}")
	public ResponseRestful flash(@PathVariable("size") long size){
		return news.flash(size, new Date());
	}

	@RequestMapping(value = "/flash/more/{size}/{date}")
	public ResponseRestful flash(@PathVariable("size") long size, @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date){
		return news.flash(size, date);
	}

}
