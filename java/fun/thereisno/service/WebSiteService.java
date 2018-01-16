package fun.thereisno.service;

import java.util.List;

import fun.thereisno.entity.PageBean;
import fun.thereisno.entity.WebSite;

public interface WebSiteService {

	void save(WebSite webSite);
	
	List<WebSite> findAll();
	
	List<WebSite> list(String name, PageBean pageBean);
	
	Integer count(String name);
	
	void delete(List<WebSite> list);
	
	List<WebSite> findByNameLike(String name);
	
	WebSite findById(Integer id);
	
	List<WebSite> findAllAndCount(PageBean pageBean);
}
