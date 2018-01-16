package fun.thereisno.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fun.thereisno.entity.Film;
import fun.thereisno.entity.PageBean;
import fun.thereisno.service.FilmService;
import fun.thereisno.util.NavUtil;
import fun.thereisno.util.PageUtil;
import fun.thereisno.util.UpDownUtil;

@Controller
@RequestMapping("film")
public class FilmController {

	@Resource
	private FilmService filmService;
	
	@GetMapping("{id}")
	public ModelAndView show(@PathVariable Integer id){
		ModelAndView mv = new ModelAndView("index");
		Film f = filmService.get(id);
		mv.addObject("film", f);
		mv.addObject("mainPage", "film/show");
		mv.addObject("navCode", NavUtil.genNav(f.getWebSite(), f.getTitle()));
		mv.addObject("pageCode", UpDownUtil.genFooter(filmService.lastAndNext(id)));
		mv.addObject("randList", filmService.listByRand());
		return mv;
	}
	
	@GetMapping("list")
	public ModelAndView list(@RequestParam(defaultValue = "1") Integer page){
		Film f = new Film();
		PageBean p = new PageBean(page, 12);
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("mainPage", "film/list");
		mv.addObject("filmList", filmService.list(f, p, null, null));
		Integer total = filmService.count(f, null, null);
		mv.addObject("pageCode", PageUtil.genPagi("list", total, page, p.getRows()));
		return mv;
	}
	
	@GetMapping("about")
	public ModelAndView about(){
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("mainPage", "about");
		return mv;
	}
	
	@PostMapping("search")
	public ModelAndView search(@RequestParam(defaultValue = "1") Integer page, @Valid Film f, BindingResult r){
		ModelAndView mv = new ModelAndView("index");
		if(r.hasFieldErrors("name")){
			mv.addObject("error", r.getFieldError().getDefaultMessage());
			return mv;
		}
		PageBean p = new PageBean(page, 12);
		mv.addObject("mainPage", "film/search");
		mv.addObject("name", f.getName());
		mv.addObject("nameDeco", "<span style='color:red'>" + f.getName() + "</span>");
		mv.addObject("filmList", filmService.list(f, p, null, null));
		Integer total = filmService.count(f, null, null);
		mv.addObject("total", total);
		mv.addObject("totalDeco", "<span style='color:red'>" + total + "</span>");
		if(f !=null && f.getName() != null)
			mv.addObject("pageCode", PageUtil.genPagi("search?name=" + f.getName(), total, page, p.getRows()));
		else
			mv.addObject("pageCode", PageUtil.genPagi("search", total, page, p.getRows()));
		return mv;
	}
}
