package cn.netkiller.schedule.task;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.rest.RestStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.netkiller.common.domain.elasticsearch.ElasticsearchTrash;
import cn.netkiller.common.repository.elasticsearch.ElasticsearchTrashRepository;

@Component
public class ScheduledTasks {
	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

	@Autowired
	private TransportClient client;

	@Autowired
	private ElasticsearchTrashRepository alasticsearchTrashRepository;
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	public ScheduledTasks() {
	}

	@Scheduled(fixedRate = 1000 * 60) // 60秒运行一次调度任务
	public void cleanTrash() {
		for (ElasticsearchTrash elasticsearchTrash : alasticsearchTrashRepository.findAll()) {
			DeleteResponse response = client.prepareDelete("information", "article", elasticsearchTrash.getId() + "").get();
			RestStatus status = response.status();
//			logger.info("delete {} {}", elasticsearchTrash.getId(), status.toString());
			if (status == RestStatus.OK || status == RestStatus.NOT_FOUND) {
				alasticsearchTrashRepository.delete(elasticsearchTrash);
			}
		}
	}
	@Scheduled(cron = "0 0 23 * * ?")
	private void cleanNewFlash() {
		long begin = System.currentTimeMillis();
        
		redisTemplate.delete("FASTNEWS_DATA_STORE_KEY"); // 删除快讯
    
        long end = System.currentTimeMillis();
		logger.info("Schedule clean redis {} 耗时 {} 秒", "cleanNewFlash()", (end-begin) / 1000 );
	}
}
