package api.controller;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import api.domain.h5.Gather;
import api.repository.h5.GatherRepository;

@Controller
@RequestMapping("/h5/gather")
public class GatherController {
	@Autowired
	private GatherRepository gatherRepository;

	public GatherController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/index")
	@ResponseBody
	public String index() {
		return "Helloworld!!!";

	}

	@GetMapping("/hello")
	// 如果此处使用 @ResponseBody，将会返回 "hello" 字符串，而不是模板
	public String test() {
		return "hello";

	}

	@RequestMapping("/browse")
	public ModelAndView browse(Pageable pageable) {
		Page<Gather> gathers = gatherRepository.findAll(pageable);
//		System.out.println(gathers.toString());
		ModelAndView mv = new ModelAndView();
		mv.addObject("gathers", gathers.getContent());
		mv.addObject("number", gathers.getNumber());
		mv.addObject("size", gathers.getSize());
		mv.addObject("totalPages", gathers.getTotalPages()); 
		mv.addObject("totalElements",gathers.getTotalElements());
		mv.setViewName("table");

		return mv;
	}

	@RequestMapping("/list/{page}")
	public ModelAndView list(@PathVariable(required = false) int page) {

		// Sort sort = new Sort(Direction.DESC, "id");
		// Page<Gather> gathers = gatherRepository.listAllByPage(PageRequest.of(page - 1, 20, sort));

		ModelAndView mv = new ModelAndView();
		// mv.addObject("gathers", gathers.getContent());
		// mv.addObject("number", gathers.getNumber());
		// mv.addObject("size", gathers.getSize());
		// mv.addObject("totalPages", gathers.getTotalPages());
		// mv.setViewName("table");

		return mv;
	}

	@GetMapping("/download")
	@ResponseBody
	public void list(HttpServletResponse response) throws IOException {
		response.setContentType("application/csv");
		// response.setContentType("application/csv;charset=gb18030");
		response.setHeader("Content-Disposition", "attachment; filename=\"file.csv\"");

		BufferedWriter writer = new BufferedWriter(response.getWriter());

		// 需要写入 utf8bom 头否则会出现中文乱码
		// byte[] uft8bom = { (byte) 0xef, (byte) 0xbb, (byte) 0xbf };
		String bom = new String(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF });
		writer.write(bom);

		writer.write("\"ID\",\"提供方式\",\"征购意向金额\",\"藏品时期\",\"藏品描述\",\"真实来源\",\"姓名\",\"联系方式\",\"地区\",\"详细地址\",\"藏品图片\"");

		writer.newLine();
		gatherRepository.findAll().forEach(gather -> {
//			System.out.println(gather.toString());
			try {

				String picture = "";
				if (gather.getPicture() != null) {
					for (String pic : gather.getPicture().split(",")) {
						picture += String.format("https://www.bitvaluebk.com/upload/%s ", pic);
					}
				}
				String tmp = String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"", gather.getId(), gather.getMethod(), gather.getMoney(), gather.getAge(), gather.getDescription(), gather.getSource(), gather.getName(), gather.getTel(), gather.getArea(), gather.getAddress(), picture);

				writer.write(tmp);
				writer.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

		writer.flush();
		writer.close();
	}
}
