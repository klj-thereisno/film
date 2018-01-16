package fun.thereisno.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import fun.thereisno.entity.Link;
import fun.thereisno.entity.PageBean;
import fun.thereisno.repository.LinkRepository;
import fun.thereisno.service.LinkService;

@Service
public class LinkServiceImpl implements LinkService{

	@Resource
	private LinkRepository linkRepository;
	
	@Override
	public void save(Link link) {
		linkRepository.save(link);
	}

	@Override
	public List<Link> list(String name, PageBean pageBean) {
		return linkRepository.list(name, pageBean.getStart(), pageBean.getRows());
	}

	@Override
	public Integer count(String name) {
		return linkRepository.count(name);
	}

	@Override
	public void delete(List<Link> list) {
		linkRepository.delete(list);
	}

	

}
