package fun.thereisno.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fun.thereisno.entity.WebSite;

public interface WebSiteRepository extends JpaRepository<WebSite, Integer>{

	@Query(value = "select * from t_web_site where name like ?1 order by sort limit ?2, ?3", nativeQuery = true)
	List<WebSite> list(String name, Integer start, Integer rows);
	
	@Query(value = "select count(*) from t_web_site where name like ?1", nativeQuery = true)
	Integer count(String name);
	
	@Query
	List<WebSite> findByNameLike(String name);
	
	/**
	 * 右连接网址
	 * @return
	 */
	@Query(value = "SELECT s.`id`, s.`name`, COUNT(f.`id`) url, sort FROM t_film f RIGHT JOIN t_web_site s ON site_id = s.id GROUP BY s.`id` ORDER BY url DESC limit ?1, ?2", nativeQuery = true)
	List<WebSite> list(Integer start, Integer rows);
	
	@Query
	WebSite findById(Integer id);
}
