package cn.netkiller.common.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "statistics_history")
public class StatisticsHistory implements Serializable {

    private static final long serialVersionUID = 6504744825701612512L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, insertable = true, updatable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;
    // private int memberId;

    // @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    // @JoinColumn(name = "article_id", referencedColumnName = "articleId")
    // private Statistics statistics;
    private int articleId;

    public enum StatisticsType {
        LIKE, COMMENT, BROWSE, FAVORITE;
    }

    @Enumerated(EnumType.STRING)
    private StatisticsType type;

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

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public StatisticsType getType() {
        return type;
    }

    public void setType(StatisticsType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "StatisticsHistory [id=" + id + ", member=" + member + ", articleId=" + articleId + ", type=" + type + "]";
    }

}
