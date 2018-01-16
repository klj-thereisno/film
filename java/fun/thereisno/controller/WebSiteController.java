package fun.thereisno.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fun.thereisno.entity.Film;
import fun.thereisno.entity.PageBean;
import fun.thereisno.entity.WebSite;
import fun.thereisno.service.FilmService;
import fun.thereisno.service.WebSiteService;
import fun.thereisno.util.NavUtil;
import fun.thereisno.util.PageUtil;

@Controller
@RequestMapping("site")
public class WebSiteController {

	@Resource
	private WebSiteService webSiteService;
	
	@Resource
	private FilmService filmService;
	
	@GetMapping("list")
	public ModelAndView listSite(@RequestParam(defaultValue = "1") Integer page){
		PageBean p = new PageBean(page, 10);
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("mainPage", "site/list");
		mv.addObject("siteList", webSiteService.findAllAndCount(p));
		Integer total = webSiteService.count("%");
		mv.addObject("pageCode", PageUtil.genPagi("", total, page, p.getRows()));
		return mv;
	}
	
	@GetMapping("{id}")
	public ModelAndView siteList(@RequestParam(defaultValue = "1") Integer page, @PathVariable Integer id){
		PageBean p = new PageBean(page, 8);
		Film f = new Film();
		WebSite s = webSiteService.findById(id);
		f.setWebSite(s);
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("mainPage", "site/show");
		mv.addObject("navCode", NavUtil.genNav(s, null));
		mv.addObject("filmList", filmService.list(f, p, null, null));
		Integer total = filmService.count(f, null, null);
		mv.addObject("pageCode", PageUtil.genPagi("", total, page, p.getRows()));
		return mv;
	}
}
