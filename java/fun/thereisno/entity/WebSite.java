package fun.thereisno.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "t_web_site")
@JsonIgnoreProperties(value = "filmList")
public class WebSite {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(length = 10)
	private String name;
	
	@Column(length = 100)
	private String url;

	@OneToMany
	@JoinColumn(name = "site_id")
	private List<Film> filmList;
	
	private Integer sort;
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Film> getFilmList() {
		return filmList;
	}

	public void setFilmList(List<Film> filmList) {
		this.filmList = filmList;
	}

	public WebSite() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WebSite(Integer id) {
		super();
		this.id = id;
	}
}
