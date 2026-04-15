package cn.netkiller.graphql.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Book {
	private Integer id;
	private String name;
	private Author author;
	private String publisher;

	// public Book() {
	// // TODO Auto-generated constructor stub
	// }

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author + ", publisher=" + publisher + "]";
	}

}
