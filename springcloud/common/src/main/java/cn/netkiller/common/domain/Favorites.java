package cn.netkiller.common.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "member_id", "article_id" }) })
public class Favorites implements Serializable {

	private static final long serialVersionUID = 5131334976079700642L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, insertable = true, updatable = false)
	private int id;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "site_id", referencedColumnName = "id")
	private Site site;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "member_id", referencedColumnName = "id")
	private Member member;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "article_id", referencedColumnName = "id")
	private Article article;

	// private int articleId;
	private String title;
	private String summary;
	private String source;
	private String image;
	private String count;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date ctime;

	@Transient
	private int browses;// 浏览数量
	@Transient
	private int likes;// 点赞数量
	@Transient
	private int comments;// 评论数量
	@Transient
	private int favorites;// 收藏数量

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public int getBrowses() {
		return browses;
	}

	public void setBrowses(int browses) {
		this.browses = browses;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getComments() {
		return comments;
	}

	public void setComments(int comments) {
		this.comments = comments;
	}

	public int getFavorites() {
		return favorites;
	}

	public void setFavorites(int favorites) {
		this.favorites = favorites;
	}

	@Override
	public String toString() {
		return "Favorites [id=" + id + ", site=" + site + ", member=" + member + ", article=" + article + ", title="
				+ title + ", summary=" + summary + ", source=" + source + ", image=" + image + ", count=" + count
				+ ", ctime=" + ctime + ", browses=" + browses + ", likes=" + likes + ", comments=" + comments
				+ ", favorites=" + favorites + "]";
	}

}
