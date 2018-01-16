package fun.thereisno.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fun.thereisno.entity.Link;

public interface LinkRepository extends JpaRepository<Link, Integer>{

	@Query(value = "select * from t_link where name like ?1 order by sort limit ?2, ?3", nativeQuery = true)
	List<Link> list(String name, Integer start, Integer rows);
	
	@Query(value = "select count(*) from t_link where name like ?1", nativeQuery = true)
	Integer count(String name);
	
}
