package fun.thereisno.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import fun.thereisno.entity.PageBean;
import fun.thereisno.entity.WebSite;
import fun.thereisno.repository.WebSiteRepository;
import fun.thereisno.service.FilmService;
import fun.thereisno.service.WebSiteService;

@Service
public class WebSiteServiceImpl implements WebSiteService{

	@Resource
	private WebSiteRepository webSiteRepository;
	
	@Resource
	private FilmService filmSiteService;
	
	@Override
	public void save(WebSite webSite) {
		webSiteRepository.save(webSite);
	}

	@Override
	public List<WebSite> findAll() {
		return webSiteRepository.findAll(new Sort(Direction.ASC, "sort"));
	}

	@Override
	public List<WebSite> list(String name, PageBean pageBean) {
		return webSiteRepository.list(name, pageBean.getStart(), pageBean.getRows());
	}

	@Override
	public Integer count(String name) {
		return webSiteRepository.count(name);
	}

	@Override
	public void delete(List<WebSite> list) {
		webSiteRepository.delete(list);
		filmSiteService.updateOther();
	}

	@Override
	public List<WebSite> findByNameLike(String name) {
		return webSiteRepository.findByNameLike(name);
	}

	@Override
	public List<WebSite> findAllAndCount(PageBean pageBean) {
		return webSiteRepository.list(pageBean.getStart(), pageBean.getRows());
	}

	@Override
	public WebSite findById(Integer id) {
		return webSiteRepository.findById(id);
	}

}
