package fun.thereisno.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import fun.thereisno.entity.Film;

public interface FilmRepository extends JpaRepository<Film, Integer>,JpaSpecificationExecutor<Film>{

	/*@Query(value = "select * from t_film where name like :name and publish_date between :s_b_date and :s_e_date limit :start,:rows", nativeQuery = true)
	List<Film> listFilm(@Param("name")String name, @Param("s_b_date")Date s_b_date, @Param("s_e_date")Date s_e_date, Integer start, Integer rows);
	
	@Query(value = "select count(*) from t_film where name like :name and publish_date between :s_b_date and :s_e_date", nativeQuery = true)
	Integer count(@Param("name")String name, @Param("s_b_date")Date s_b_date, @Param("s_e_date")Date s_e_date);*/
	
	@Modifying
	@Query(value = "update t_film set site_id = (select id from t_web_site where name = '其他') where site_id is null", nativeQuery = true)
	void updateOther();
	
	@Modifying
	@Query(value = "update t_film set site_id = ?1 where site_id is null", nativeQuery = true)
	void updateRe(Integer id);
	
	@Query(value = "SELECT * FROM t_film WHERE id < ?1 ORDER BY id DESC LIMIT 1", nativeQuery = true)
	Film findLastById(Integer id);
	
	@Query(value = "SELECT * FROM t_film WHERE id > ?1 ORDER BY id ASC LIMIT 1", nativeQuery = true)
	Film findNextById(Integer id);
	
	@Query(value = "SELECT * FROM t_film order by rand() LIMIT 8", nativeQuery = true)
	List<Film> findByRandom();
	
	@Query(value = "SELECT count(*) FROM t_film where name = ?1", nativeQuery = true)
	Integer findByName(String name);
}
