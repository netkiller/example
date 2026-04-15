package cn.netkiller.graphql.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Author {
	private Integer id;
	private String name;
	private Integer age;

	// public Author() {
	// // TODO Auto-generated constructor stub
	// }

	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

}
