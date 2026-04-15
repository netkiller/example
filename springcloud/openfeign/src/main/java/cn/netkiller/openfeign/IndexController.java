package cn.netkiller.openfeign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
	@Autowired
	TestFeign testFeign;

	@RequestMapping("/feign")
	public String testFeign(@RequestParam(name = "name") String name) {
		return testFeign.hi(name);
	}
}
