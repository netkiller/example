package cn.netkiller.common.domain;

import java.io.Serializable;

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

@Entity
@Table(name = "statistics")
public class Statistics implements Serializable {

	private static final long serialVersionUID = -4522883851891476819L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, insertable = true, updatable = false)
	private int id;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "site_id", referencedColumnName = "id")
	private Site site;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "article_id", referencedColumnName = "id")
//	@Column(unique = true, nullable = false, insertable = true, updatable = false)
	private Article article;
	// private int articleId;

	@Column(name = "browse")
	private int browses = 0;// 浏览数量
	private int likes = 0;// 点赞数量

	@Column(name = "comment")
	private int comments = 0;// 评论数量

	private int favorites = 0;// 收藏数量

	@Transient
	private int likeFlag = 0;// 当前用户是否点过赞

	@Transient
	private int favoriteFlag = 0;// 当前用户是已收藏

	@Transient
	private StatisticsHistory.StatisticsType type;// 枚举类型，前台发过来的请求类型

	// @JsonIgnore
	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "statistics", orphanRemoval
	// = true)
	// private Set<StatisticsHistory> statisticsHistory;

	public Statistics() {
	}

	public Statistics(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getLikeFlag() {
		return likeFlag;
	}

	public void setLikeFlag(int likeFlag) {
		this.likeFlag = likeFlag;
	}

	public int getFavoriteFlag() {
		return favoriteFlag;
	}

	public void setFavoriteFlag(int favoriteFlag) {
		this.favoriteFlag = favoriteFlag;
	}

	public StatisticsHistory.StatisticsType getType() {
		return type;
	}

	public void setType(StatisticsHistory.StatisticsType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Statistics [id=" + id + ", site=" + site + ", article=" + article + ", browses=" + browses + ", likes="
				+ likes + ", comments=" + comments + ", favorites=" + favorites + ", likeFlag=" + likeFlag
				+ ", favoriteFlag=" + favoriteFlag + ", type=" + type + "]";
	}

}
