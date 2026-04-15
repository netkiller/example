package cn.netkiller.common.service;

import java.util.List;
import java.util.Map;

public interface CmsService {

	/**
	 * 查询文章评论数量
	 * 
	 * @param articleIds
	 *            文章id列表
	 * @return
	 */
	Map<Integer, Long> findCommentNum(List<Integer> articleIds);

	String financeList(Map<String, String> param);

	String financeDetail(String dataId);

	String newsContent(String contentId);

}
