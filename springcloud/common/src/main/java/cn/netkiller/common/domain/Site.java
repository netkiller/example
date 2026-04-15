package cn.netkiller.common.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Site {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, insertable = true, updatable = false)
	public int id;
	public String name;
	public String description;
	public String url;
	@Column(columnDefinition = "enum('Enabled','Disabled') DEFAULT 'Enabled' COMMENT '状态'")
	public String status;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
	public Date ctime;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(columnDefinition = "TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间'")
	public Date mtime;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "site", fetch = FetchType.EAGER)
	private Set<Member> member;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "site", orphanRemoval = true)
	private Set<Article> article;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "site", orphanRemoval = true)
	private Set<Favorites> favorites;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "site", orphanRemoval = true)
	private Set<Comment> comment;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "site", orphanRemoval = true)
	private Set<Statistics> statistics;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "site", orphanRemoval = true)
	private Set<Tag> tag;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "site", orphanRemoval = true)
	private Set<Banner> banner;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "site", orphanRemoval = true)
	private Set<ItemPool> itemPool;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Date getMtime() {
		return mtime;
	}

	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}

}