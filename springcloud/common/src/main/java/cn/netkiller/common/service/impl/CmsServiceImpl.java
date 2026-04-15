package cn.netkiller.common.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import cn.netkiller.common.service.CmsService;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

@RefreshScope
@Service
public class CmsServiceImpl implements CmsService {

	private final Logger logger = LoggerFactory.getLogger(CmsServiceImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value("${cms.api.url}")
	private String url;

	@Value("${cms.api.appid}")
	private String appId;

	@Value("${cms.api.appkey}")
	private String appKey;

	private String getTimestamp() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(new Date());

	}

	@Override
	public Map<Integer, Long> findCommentNum(List<Integer> articleIds) {
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("articleIds", articleIds);
		String sql = "select article_id, count(article_id) as count from comment GROUP BY article_id HAVING article_id in (:articleIds)";
		List<Map<String, Object>> list = namedParameterJdbcTemplate.queryForList(sql, parameters);

		Map<Integer, Long> result = new HashMap<>();
		for (Map<String, Object> map : list) {
			System.out.println((String) map.get("article_id"));
			System.out.println((Long) map.get("count"));
			result.put((Integer) map.get("article_id"), (Long) map.get("count"));
		}

		return result;
	}

	private String token() {
		String token = "";
		try {
			token = DatatypeConverter.printHexBinary(MessageDigest.getInstance("MD5").digest(String.format("%s&%s", this.appKey, this.getTimestamp()).getBytes("UTF-8"))).toLowerCase();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return token;
	}

	public String financeList(Map<String, String> param) {

		final String uri = this.url + "/api/finance/list.jspx";
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		try {

			map.add("appId", this.appId);

			if (param.containsKey("releaseTime")) {
				map.add("releaseTime", param.get("releaseTime"));
			} else {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				map.add("releaseTime", dateFormat.format(new Date()));
			}

			if (param.containsKey("country")) {
				map.add("country", param.get("country"));
			}
			if (param.containsKey("status")) {
				map.add("status", param.get("status"));
			}
			if (param.containsKey("level")) {
				map.add("level", param.get("level"));
			}
			if (param.containsKey("kinds")) {
				map.add("kinds", param.get("kinds"));
			}

			map.add("timeStamp", this.getTimestamp());
			map.add("token", this.token());

		} catch (Exception e) {
			e.printStackTrace();
		}

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.postForEntity(uri, map, String.class);
		logger.debug(map.toString());
		return response.getBody().replace("{\"code\":0,\"message\":\"ok\",\"response\":{", "{\"status\":true,\"code\":0,\"reason\":\"ok\",\"data\":{");
	}

	public String financeDetail(String dataId) {
		final String uri = this.url + "/api/finance/detail.jspx";
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();

		map.add("appId", this.appId);
		map.add("dataId", dataId);
		map.add("timeStamp", this.getTimestamp());
		map.add("token", this.token());

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.postForEntity(uri, map, String.class);
		logger.debug(map.toString());
		return response.getBody().replace("{\"code\":0,\"message\":\"ok\",\"response\":{", "{\"status\":true,\"code\":0,\"reason\":\"ok\",\"data\":{");
	}

	@Override
	public String newsContent(String contentId) {
		
		final String uri = this.url + "/api/newsContent/detail.jspx";
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();

		map.add("appId", this.appId);
		map.add("contentId", contentId);
		map.add("timeStamp", this.getTimestamp());
		map.add("token", this.token());

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.postForEntity(uri, map, String.class);
		logger.debug(map.toString());

		return response.getBody().replace("{\"code\":0,\"message\":\"ok\",\"response\":{", "{\"status\":true,\"code\":0,\"reason\":\"ok\",\"data\":{");
	}
	
}
