package fun.thereisno.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import fun.thereisno.util.CustomUtil;

@Entity
@Table(name = "t_film")
public class Film {

	@Id
	@GeneratedValue
	private Integer id;
	
	@NotEmpty(message = "请输入要搜索的电影名")
	@Column(length = 20)
	private String name;
	
	@Column(length = 200)
	private String title;
	
	@Lob
	@Column(columnDefinition = "text")
	private String content;
	
	@Column(length = 100)
	private String imageName;
	
	private boolean hot;
	
	private Date publishDate;
	
	@Column(length = 1000, nullable = false)
	private String url;
	
	@ManyToOne
	@JoinColumn(name = "site_id")
	private WebSite webSite;

	
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public boolean isHot() {
		return hot;
	}

	public void setHot(boolean hot) {
		this.hot = hot;
	}

	@JsonSerialize(using = CustomUtil.class)
	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public WebSite getWebSite() {
		return webSite;
	}

	public void setWebSite(WebSite webSite) {
		this.webSite = webSite;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
