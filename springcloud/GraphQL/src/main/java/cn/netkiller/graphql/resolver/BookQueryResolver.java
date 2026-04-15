package cn.netkiller.graphql.resolver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import cn.netkiller.graphql.domain.Author;
import cn.netkiller.graphql.domain.Book;
import graphql.schema.GraphQLFieldDefinition;
import graphql.servlet.osgi.GraphQLQueryProvider;

@Component
public class BookQueryResolver implements GraphQLQueryProvider {

	public BookQueryResolver() {
		// TODO Auto-generated constructor stub
	}

	public List<Book> findBooks() {
		Author author = Author.builder().id(1).name("Neo Chen").age(40).build();
		Book b = Book.builder().id(1).name("Netkiller SpringCloud 手札").author(author).publisher("http://www.netkiller.cn").build();
		List<Book> bookList = new ArrayList<Book>();
		bookList.add(b);
		return bookList;
	}

	@Override
	public Collection<GraphQLFieldDefinition> getQueries() {
		// TODO Auto-generated method stub
		return null;
	}
}
