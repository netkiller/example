package cn.netkiller.common.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "article")
public class Article implements Serializable {
	private static final long serialVersionUID = 7603772682950271321L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, insertable = true, updatable = false)
	public int id;
	public String title;
	@Column(name = "short")
	public String shortTitle;
	public String description;
	public String author;
	public int star;
	public String tag;
	public boolean share;
	public boolean status;
	public String content;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	private Category category;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "site_id", referencedColumnName = "id")
	private Site site;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "member_id", referencedColumnName = "id")
	private Member member;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
	public Date ctime;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(columnDefinition = "TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间'")
	public Date mtime;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "article", fetch = FetchType.EAGER)
	private Set<Comment> comment;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "article", fetch = FetchType.EAGER)
	private Set<Favorites> favorites;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "article", orphanRemoval = true)
	private Set<Statistics> statistics;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public boolean isShare() {
		return share;
	}

	public void setShare(boolean share) {
		this.share = share;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Set<Comment> getComment() {
		return comment;
	}

	public void setComment(Set<Comment> comment) {
		this.comment = comment;
	}

	public Set<Favorites> getFavorites() {
		return favorites;
	}

	public void setFavorites(Set<Favorites> favorites) {
		this.favorites = favorites;
	}

	public Set<Statistics> getStatistics() {
		return statistics;
	}

	public void setStatistics(Set<Statistics> statistics) {
		this.statistics = statistics;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public Date getMtime() {
		return mtime;
	}

	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", shortTitle=" + shortTitle + ", description=" + description
				+ ", author=" + author + ", star=" + star + ", tag=" + tag + ", share=" + share + ", status=" + status
				+ ", content=" + content + ", category=" + category + ", site=" + site + ", member=" + member
				+ ", ctime=" + ctime + ", mtime=" + mtime + ", comment=" + comment + ", favorites=" + favorites
				+ ", statistics=" + statistics + "]";
	}

}
