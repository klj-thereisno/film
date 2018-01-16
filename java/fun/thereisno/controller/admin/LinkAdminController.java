package fun.thereisno.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fun.thereisno.entity.Link;
import fun.thereisno.entity.PageBean;
import fun.thereisno.run.InitRun;
import fun.thereisno.service.LinkService;
import fun.thereisno.util.StringUtil;

@RestController
@RequestMapping("admin/link")
public class LinkAdminController {

	@Resource
	private LinkService linkService;
	
	@Resource
	private InitRun initRun;
	
	@PostMapping("{id}")
	public String save(Link link){
		linkService.save(link);
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
		map.put("total", linkService.count(name));
		map.put("rows", linkService.list(name, pageBean));
		return map;
	}
	
	@PostMapping("delete")
	public String delete(String ids){
		String[] idArr = ids.split(",");
		List<Link> list = new ArrayList<>();
		for (String id : idArr) 
			list.add(new Link(Integer.valueOf(id)));
		linkService.delete(list);
		try {
			initRun.run("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
}
