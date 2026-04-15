package cn.netkiller.web.restful;
//package web.restful;
//
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import common.feign.Cms;
//
//@RestController
//@RequestMapping("/restful")
//public class CmsRestController {
//	
//	@Autowired
//	private Cms cms;
//	
//	@PostMapping("/finance/list")
//	public String financeList(@RequestBody Map<String, String> map) {
//		return cms.financeList(map);
//	}
//
//	@GetMapping("/finance/detail/{dataId}")
//	public String financeDetail(@PathVariable("dataId") String dataId) {
//		return cms.financeDetail(dataId);
//	}
//	
//	@RequestMapping("/newsContent/{contentId}")
//    public String newsContent(@PathVariable String contentId) {
//        return cms.newsContent(contentId);
//    }
//}
