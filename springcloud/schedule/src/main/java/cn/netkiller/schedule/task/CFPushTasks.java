package cn.netkiller.schedule.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.netkiller.common.domain.Article;
import cn.netkiller.common.domain.CmsTrash;
import cn.netkiller.common.pojo.ResponseRestful;
import cn.netkiller.schedule.repository.ArticleRepository;
import cn.netkiller.schedule.repository.CmsTrashRepository;
import cn.netkiller.schedule.service.Kafka;

@Component
public class CFPushTasks {
	private static final Logger logger = LoggerFactory.getLogger(CFPushTasks.class);

	private static final String TOPIC = "chuangfu";
	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private CmsTrashRepository cmsTrashRepository;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Autowired
	private Kafka kafka;

	@Value("${cf.cms.site_id}")
	private int siteId;

	public CFPushTasks() {
	}

	private boolean setPostionDate(String key, Date value) {
		String cacheKey = String.format("schedule:CFPushTasks:PostionDate:%s", key);
		String date = simpleDateFormat.format(value);
		logger.debug("setPostion({},{})", cacheKey, date);
		redisTemplate.opsForValue().set(cacheKey, date);
		logger.debug("setPostionDate -> Check ({},{})", date, redisTemplate.opsForValue().get(cacheKey));
		if (date.equals(redisTemplate.opsForValue().get(cacheKey))) {
			return true;
		}
		return false;
	}

	private Date getPostionDate(String key) {
		String cacheKey = String.format("schedule:CFPushTasks:PostionDate:%s", key);
		Date date = null;
		if (redisTemplate.hasKey(cacheKey)) {
			try {
				date = simpleDateFormat.parse(redisTemplate.opsForValue().get(cacheKey));
			} catch (ParseException e) {
				e.printStackTrace();
				logger.warn(e.getMessage());
			}
		}
		logger.debug("getPostion({}) => {}", cacheKey, date);
		return date;
	}

	private boolean setPostionId(String key, int id) {
		String cacheKey = String.format("schedule:CFPushTasks:PostionId:%s", key);
		logger.debug("setPostionId({},{})", cacheKey, id);
		redisTemplate.opsForValue().set(cacheKey, String.valueOf(id));
		logger.debug("setPostionId -> Check ({},{})", id, Integer.valueOf(redisTemplate.opsForValue().get(cacheKey)));

		if (id == Integer.valueOf(redisTemplate.opsForValue().get(cacheKey))) {
			return true;
		}
		return false;
	}

	private int getPostionId(String key) {
		String cacheKey = String.format("schedule:CFPushTasks:PostionId:%s", key);
		int id = 0;
		if (redisTemplate.hasKey(cacheKey)) {
			id = Integer.valueOf(redisTemplate.opsForValue().get(cacheKey));
		}
		logger.debug("getPostionId({}) => {}", cacheKey, id);
		return id;
	}

	@Scheduled(fixedRate = 1000 * 60)
	public void schedule() {
		this.insert();
		this.update();
		this.delete();
	}

	// 推送新增数据
	public void insert() {
		Iterable<Article> articles = null;
		int id = this.getPostionId("insert");

		if (id == 0) {
			articles = articleRepository.findBySiteId(this.siteId);
			logger.debug("findBySiteId( {} ) -> id is {}", this.siteId, id);
		} else {
			articles = articleRepository.findBySiteIdAndIdGreaterThanOrderByIdAsc(this.siteId, id);
			logger.debug("findBySiteIdAndIdGreaterThanOrderByIdAsc( {} ) -> id is {}", this.siteId, id);
		}

		if (articles != null) {
			for (Article article : articles) {
				ResponseRestful responseRestful = new ResponseRestful(true, this.getPostionId("insert"), "INSERT", article);
				String jsonString;
				try {
					jsonString = mapper.writeValueAsString(responseRestful);
					kafka.send(TOPIC, jsonString);
					if (!this.setPostionId("insert", article.getId())) {
						return;
					}
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			}
		}

	}

	// 推送更新数据
	public void update() {
		Date date = this.getPostionDate("update");
		Iterable<CmsTrash> cmsTrashs;
		if (date == null) {
			cmsTrashs = cmsTrashRepository.findBySiteIdAndTypeOrderByCtime(this.siteId, "update");
		} else {
			cmsTrashs = cmsTrashRepository.findBySiteIdAndTypeAndCtimeGreaterThanOrderByCtime(this.siteId, "update", date);
		}
		if (cmsTrashs != null) {

			for (CmsTrash cmsTrash : cmsTrashs) {
				logger.debug(cmsTrash.toString());

				Article article = articleRepository.findOne(cmsTrash.getId());
				ResponseRestful responseRestful = new ResponseRestful(true, this.getPostionId("update"), "UPDATE", article);
				String jsonString;
				try {
					jsonString = mapper.writeValueAsString(responseRestful);
					kafka.send(TOPIC, jsonString);
					this.setPostionId("update", cmsTrash.getId());
					if (!this.setPostionDate("update", cmsTrash.getCtime())) {
						return;
					}
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}

			}
		}
	}

	// 推送删除数据
	public void delete() {
		Date date = this.getPostionDate("delete");
		Iterable<CmsTrash> cmsTrashs;
		if (date == null) {
			cmsTrashs = cmsTrashRepository.findBySiteIdAndTypeOrderByCtime(this.siteId, "delete");
		} else {
			cmsTrashs = cmsTrashRepository.findBySiteIdAndTypeAndCtimeGreaterThanOrderByCtime(this.siteId, "delete", date);
		}
		if (cmsTrashs != null) {
			for (CmsTrash cmsTrash : cmsTrashs) {
				ResponseRestful responseRestful = new ResponseRestful(true, this.getPostionId("delete"), "DELETE", cmsTrash);
				String jsonString;
				try {
					jsonString = mapper.writeValueAsString(responseRestful);
					kafka.send(TOPIC, jsonString);
					this.setPostionId("delete", cmsTrash.getId());
					if (!this.setPostionDate("delete", cmsTrash.getCtime())) {
						return;
					}
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*
	 * 
	 * private Date getDate() {
	 * 
	 * Calendar calendar = Calendar.getInstance(); calendar.add(Calendar.MINUTE,
	 * -1); Date date = calendar.getTime(); return date; }
	 * 
	 * private void cmstrash(String type) { Date date = this.getPostionDate(type);
	 * Iterable<CmsTrash> cmsTrashs; if (date == null) { cmsTrashs =
	 * cmsTrashRepository.findBySiteIdAndTypeOrderByCtime(this.siteId, type); } else
	 * { cmsTrashs =
	 * cmsTrashRepository.findBySiteIdAndTypeAndCtimeGreaterThanOrderByCtime(this.
	 * siteId, type, date); } if (cmsTrashs != null) {
	 * 
	 * for (CmsTrash cmsTrash : cmsTrashs) { ResponseRestful responseRestful = new
	 * ResponseRestful(true, this.getPostionId(type), type.toUpperCase(), cmsTrash);
	 * String jsonString; try { jsonString =
	 * mapper.writeValueAsString(responseRestful); kafka.send(TOPIC, jsonString);
	 * this.setPostionId(type, cmsTrash.getId()); if (!this.setPostionDate(type,
	 * cmsTrash.getCtime())) { return; } } catch (JsonProcessingException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); }
	 * 
	 * } }
	 * 
	 * }
	 * 
	 * private void post(ResponseRestful responseRestful) { RestTemplate
	 * restTemplate = new RestTemplate(); String response =
	 * restTemplate.postForObject("http://localhost:8440/test/cf/post.json",
	 * responseRestful, String.class);
	 * 
	 * // logger.info(article.toString()); if (response != null) {
	 * logger.info(response); } }
	 */
}
