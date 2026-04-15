package cn.netkiller.common.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.netkiller.common.pojo.ResponseRestful;

import java.util.Date;

@FeignClient("restful-api-service")
public interface News {
	@RequestMapping(value = "/news/flash/{from}/{to}")
	public ResponseRestful flash(@PathVariable("from") long from, @PathVariable("to") long to);

	@RequestMapping(value = "/news/flash/count")
	public ResponseRestful count();

	@RequestMapping(value = "/news/flash/page/{page}/{size}")
	public ResponseRestful pageable(@PathVariable("page") long page, @PathVariable("size") long size);

	@RequestMapping(value = "/news/flash/more/{size}/{date}")
	public ResponseRestful flash(@PathVariable("size") long size, @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date);
}
