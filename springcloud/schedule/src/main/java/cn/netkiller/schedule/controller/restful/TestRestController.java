package cn.netkiller.schedule.controller.restful;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.netkiller.common.domain.Article;
import cn.netkiller.common.pojo.ResponseRestful;
import cn.netkiller.schedule.repository.ArticleRepository;
import cn.netkiller.schedule.service.Kafka;

@RestController
public class TestRestController {

	@Value("${spring.kafka.bootstrap_servers}")
	private String test;

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private Kafka kafka;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final Logger logger = LoggerFactory.getLogger(TestRestController.class);

	@GetMapping("/test/config")
	public void config() {
		logger.info(test);
	}

	@GetMapping("/test/redis")
	public String redis() {
		String test = "test";
		redisTemplate.opsForValue().set(test, "test");
		return redisTemplate.opsForValue().get(test);

	}

	@GetMapping("/test/jdbc")
	public void jdbc() throws JsonProcessingException {
		String sql = "select * from cms.article limit 5";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		logger.debug(list.toString());
	}

	@GetMapping("/test/jpa")
	public void jpa() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();

		Iterable<Article> articles = articleRepository.findBySiteId(23);
		int i = 0;
		for (Article article : articles) {
			logger.info(article.toString());

			ResponseRestful responseRestful = new ResponseRestful(false, 0, "TEST", articles);
			String jsonString = mapper.writeValueAsString(responseRestful);
			kafka.send("test", jsonString);

			i++;
			if (i > 10) {
				return;
			}
		}

	}

	@GetMapping("/test/jpa/{id}")
	public void jpa(@PathVariable("id") int id) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();

		Article article = articleRepository.findOne(id);

		ResponseRestful responseRestful = new ResponseRestful(false, 0, "TEST", article);
		String jsonString = mapper.writeValueAsString(responseRestful);
		kafka.send("test", jsonString);
		logger.info(jsonString);

	}

	@PostMapping("/test/cf/post")
	public void cfpost(@RequestBody String raw) {
		logger.info(raw);
	}

	@GetMapping("/test/kafka/{topic}")
	public void kafka(@PathVariable("topic") String topic) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		ResponseRestful responseRestful = new ResponseRestful(false, 0, "TEST", null);
		String jsonString = mapper.writeValueAsString(responseRestful);
		kafka.send(topic, jsonString);
		logger.info(jsonString);
	}
}
