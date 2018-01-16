package fun.thereisno.run;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import fun.thereisno.entity.Film;
import fun.thereisno.entity.PageBean;
import fun.thereisno.service.FilmService;
import fun.thereisno.service.LinkService;
import fun.thereisno.service.WebSiteService;

@Component
public class InitRun implements CommandLineRunner, ServletContextListener{

	private ServletContext application;
	
	@Resource
	private FilmService filmService;
	
	@Resource
	private LinkService linkService;
	
	@Resource
	private WebSiteService webSiteService;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		application = sce.getServletContext();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run(String... args) throws Exception {
		PageBean p = new PageBean(1,10);
		application.setAttribute("linkList", linkService.list("%", p)); // 10条排序链接
		application.setAttribute("newFilm", filmService.list(null, p, null, null)); // 10条最新电影信息
		Film f = new Film();
		f.setHot(true);
		application.setAttribute("newHot", filmService.list(f, p, null, null)); // 10条热门电影信息
		p.setRows(32);
		application.setAttribute("newHotShow", filmService.list(f, p, null, null)); // 32条热门电影信息
		p.setRows(30);
		application.setAttribute("siteList", webSiteService.list("%", p)); // 30条网站信息
	}

}
