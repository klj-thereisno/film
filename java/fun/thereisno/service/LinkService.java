package fun.thereisno.service;

import java.util.List;

import fun.thereisno.entity.Link;
import fun.thereisno.entity.PageBean;

public interface LinkService {

	void save(Link link);
	
	List<Link> list(String name, PageBean pageBean);
	
	Integer count(String name);
	
	void delete(List<Link> list);
}
