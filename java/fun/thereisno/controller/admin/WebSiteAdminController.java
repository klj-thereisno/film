package fun.thereisno.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fun.thereisno.entity.PageBean;
import fun.thereisno.entity.WebSite;
import fun.thereisno.run.InitRun;
import fun.thereisno.service.FilmService;
import fun.thereisno.service.WebSiteService;
import fun.thereisno.util.StringUtil;

@RestController
@RequestMapping("admin/site")
public class WebSiteAdminController {

	@Resource
	private WebSiteService webSiteService;
	
	@Resource
	private FilmService filmSiteService;
	
	@Resource
	private InitRun initRun;
	
	@GetMapping
	public List<WebSite> listAll(String q){
		if(q != null && !q.trim().equals(""))
			return webSiteService.findByNameLike("%" + q + "%");
		return  webSiteService.findAll();
	}
	
	@PostMapping("{id}")
	public String save(WebSite webSite,String id){
		webSiteService.save(webSite);
		if(webSite.getId() != 0)
			filmSiteService.updateRe(webSite.getId());
		try {
			initRun.run("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	@PostMapping("list")
	public Map<String, Object> list(@RequestParam(defaultValue = "")String name, PageBean pageBean){
		Map<String, Object> map = new HashMap<>();
		name = StringUtil.formatString(name);
		map.put("total", webSiteService.count(name));
		map.put("rows", webSiteService.list(name, pageBean));
		return map;
	}
	
	@PostMapping("delete")
	public String delete(String ids){
		String[] idArr = ids.split(",");
		List<WebSite> list = new ArrayList<>();
		for (String id : idArr)
			list.add(new WebSite(Integer.valueOf(id)));
		webSiteService.delete(list);
		try {
			initRun.run("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
}
