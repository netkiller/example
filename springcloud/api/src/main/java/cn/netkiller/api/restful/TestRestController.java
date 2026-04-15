package cn.netkiller.api.restful;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.netkiller.common.domain.ItemPool;
import cn.netkiller.common.repository.ItemPoolRepository;

@RestController
public class TestRestController {

	private static final Logger logger = LoggerFactory.getLogger(TestRestController.class);
	@Autowired
	private ItemPoolRepository itemPoolRepository;

	@RequestMapping(value = "/test/list/{siteId}", method = RequestMethod.POST)
	public List<String> ping(@PathVariable("siteId") int siteId, @RequestBody List<String> tags) {
		System.out.println(String.format("%d, %s", siteId, tags));
		return (tags);
	}

	@PostMapping("/test/cf/post")
	public void cfpost(@RequestBody String raw) {
		logger.info(raw);
	}

	@GetMapping("/test/json/data/type")
	public void jsonType() {

		ItemPool itemPool = new ItemPool();
		itemPool.question = "Which is Operstion System?";
		Map<String, String> opt = new LinkedHashMap<String, String>();
		opt.put("A", "Linux");
		opt.put("B", "Java");
		itemPool.options = opt;
		itemPool.answer = "A";
		itemPoolRepository.save(itemPool);

		itemPool = null;
//		itemPool = itemPoolRepository.findOne(1);
		logger.info(itemPool.toString());
	}

}
