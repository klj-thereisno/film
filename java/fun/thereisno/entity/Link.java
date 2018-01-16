package fun.thereisno.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_link")
public class Link {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(length = 10)
	private String name;
	
	@Column(length = 50)
	private String url;
	
	private Integer sort;
	
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

	public Link() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Link(Integer id) {
		super();
		this.id = id;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}
