package fun.thereisno.service;

import java.util.Date;
import java.util.List;

import fun.thereisno.entity.Film;
import fun.thereisno.entity.PageBean;

public interface FilmService {

	void save(Film film);

	List<Film> list(Film film, PageBean pageBean, Date s_b_date, Date s_e_date);

	Integer count(Film film, Date s_b_date, Date s_e_date);
	
	Film get(Integer id);
	
	void delete(Integer id);
	
	void updateOther();
	
	void updateRe(Integer id);
	
	List<Film> lastAndNext(Integer id);
	
	List<Film> listByRand();
	
	boolean existName(String name);
}
