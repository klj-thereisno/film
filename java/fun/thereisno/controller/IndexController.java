package fun.thereisno.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	
	@RequestMapping("")
	public ModelAndView root(){
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	
	@GetMapping("login")
	public String login(){
		return "login";
	}
	
	@GetMapping("admin")
	public String admin(){
		return "admin/main";
	}
	
}
