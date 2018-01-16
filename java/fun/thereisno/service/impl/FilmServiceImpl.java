package fun.thereisno.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import fun.thereisno.entity.Film;
import fun.thereisno.entity.PageBean;
import fun.thereisno.repository.FilmRepository;
import fun.thereisno.service.FilmService;

@Service
public class FilmServiceImpl implements FilmService{

	@Resource
	private FilmRepository filmRepository;
	
	@Override
	public void save(Film film) {
		filmRepository.save(film);
	}

	/*@Override
	public List<Film> listFilm(Film film, PageBean pageBean, Date s_b_date, Date s_e_date) {
		return filmRepository.listFilm("%" + film.getName() + "%", s_b_date, s_e_date, pageBean.getStart(), pageBean.getRows());
	}

	@Override
	public Integer count(Film film, Date s_b_date, Date s_e_date) {
		return filmRepository.count("%" + film.getName() + "%", s_b_date, s_e_date);
	}*/

	@Override
	public List<Film> list(Film film, PageBean pageBean, Date s_b_date, Date s_e_date) {
		Pageable pageable=new PageRequest(pageBean.getPage() - 1, pageBean.getRows(), Sort.Direction.DESC,"publishDate");
		return filmRepository.findAll(new Specification<Film>() {
			
				@Override
				public Predicate toPredicate(Root<Film> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					Predicate predicate = cb.conjunction();  
					if(film != null){
						if(film.getName() != null && !film.getName().trim().equals(""))
							predicate.getExpressions().add(cb.like(root.get("name"), "%" + film.getName() + "%"));
						if(film.isHot())
							predicate.getExpressions().add(cb.equal(root.get("hot"), true));
						if(film.getWebSite() != null && film.getWebSite().getId() != null)
							predicate.getExpressions().add(cb.equal(root.get("webSite").get("id"), film.getWebSite().getId()));
					}
					if(s_b_date != null){
						predicate.getExpressions().add(cb.greaterThanOrEqualTo(root.get("publishDate"), s_b_date));
					}
					if(s_e_date != null){
						predicate.getExpressions().add(cb.lessThanOrEqualTo(root.get("publishDate"), s_e_date));
					}
	                return predicate;
				}
			}, pageable).getContent();
	}

	@Override
	public Integer count(Film film, Date s_b_date, Date s_e_date) {
		return (int) filmRepository.count(new Specification<Film>() {
			
			@Override
			public Predicate toPredicate(Root<Film> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				/*if(film != null)
					if(film.getName() != null && !film.getName().trim().equals(""))
						predicate.getExpressions().add(cb.like(root.get("name"), "%" + film.getName() + "%"));
					if(film.getHot() != null && film.getHot() != 0)
						predicate.getExpressions().add(cb.equal(root.get("hot"), 1));  
				try {
					predicate.getExpressions().add(cb.lessThanOrEqualTo(root.get("publishDate"), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-1-18 22:22:22")));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				List<Predicate> list = new ArrayList<Predicate>();  
				if(film != null){
					if(film.getName() != null && !film.getName().trim().equals(""))
						list.add(cb.like(root.get("name"), "%" + film.getName() + "%"));
					if(film.isHot())
						list.add(cb.equal(root.get("hot"), 1));  // == true
					if(film.getWebSite() != null && film.getWebSite().getId() != null)
						list.add(cb.equal(root.get("webSite").get("id"), film.getWebSite().getId()));
			
				}
				if(s_b_date != null)
					list.add(cb.greaterThanOrEqualTo(root.get("publishDate"), s_b_date));
				if(s_e_date != null)
					list.add(cb.lessThanOrEqualTo(root.get("publishDate"), s_e_date));
				// 注意此处的处理  
				// Predicate[] p = new Predicate[list.size()];  
				// return cb.and(list.toArray(p));
				return cb.and(list.toArray(new Predicate[0]));
			}
		});
	}

	@Override
	public Film get(Integer id) {
		return filmRepository.findOne(id);
	}
	
	@Override
	public void delete(Integer id){
		filmRepository.delete(id);
	}

	@Transactional
	@Override
	public void updateOther() {
		filmRepository.updateOther();
	}

	@Transactional
	@Override
	public void updateRe(Integer id) {
		filmRepository.updateRe(id);
	}

	@Override
	public List<Film> lastAndNext(Integer id) {
		List<Film> list = new ArrayList<>();
		list.add(filmRepository.findLastById(id));
		list.add(filmRepository.findNextById(id));
		return list;
	}
	
	@Override
	public List<Film> listByRand() {
		return filmRepository.findByRandom();
	}

	@Override
	public boolean existName(String name) {
		if(filmRepository.findByName(name) > 0)
			return true;
		return false;
	}
}
