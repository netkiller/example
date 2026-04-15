package cn.netkiller.common.domain.elasticsearch;
//package api.domain.elasticsearch;
//
//import java.io.Serializable;
//import java.util.Date;
//
//import javax.persistence.Id;
//
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.elasticsearch.annotations.DateFormat;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldIndex;
//import org.springframework.data.elasticsearch.annotations.FieldType;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//
//@Document(indexName = "information", type = "article")
//public class Article implements Serializable {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 8789505663320446079L;
//	@Id
//	private int id;
//	private String title;
//	private String description;
//	private String author;
//	private String source;
//	private String content;
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd'T'HHmmss.SSS'Z'")
//    @Field(type = FieldType.Date, format = DateFormat.basic_date_time, index = FieldIndex.not_analyzed)
//    @CreatedDate
//	private Date ctime;
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public String getTitle() {
//		return title;
//	}
//
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public Date getCtime() {
//		return ctime;
//	}
//
//	public void setCtime(Date ctime) {
//		this.ctime = ctime;
//	}
//
//	public String getAuthor() {
//		return author;
//	}
//
//	public void setAuthor(String author) {
//		this.author = author;
//	}
//
//	public String getSource() {
//		return source;
//	}
//
//	public void setSource(String source) {
//		this.source = source;
//	}
//
//	public String getContent() {
//		return content;
//	}
//
//	public void setContent(String content) {
//		this.content = content;
//	}
//
//	@Override
//	public String toString() {
//		return "Article [id=" + id + ", title=" + title + ", description=" + description + ", author=" + author + ", source=" + source + ", content=" + content + ", ctime=" + ctime + "]";
//	}
//
//}
