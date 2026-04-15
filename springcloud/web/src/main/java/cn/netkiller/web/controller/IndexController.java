package cn.netkiller.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	// @Autowired
	// private BannerService bannerService;

	// private Iterable<Banner> banners;

	public IndexController() {

	}

	@RequestMapping("/ping")
	@ResponseBody
	public String ping() {
		return "Pong";
	}

	@RequestMapping({ "/", "/index" })
	public ModelAndView index() {

		ModelMap model = new ModelMap();
		// this.banners = bannerService.findByPosition("home");
		// model.addAttribute("banners", this.banners);

		return new ModelAndView("index", model);
	}

	@RequestMapping("/home")
	public ModelAndView home() {
		return new ModelAndView("index");
	}
}
