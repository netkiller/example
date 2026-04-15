package cn.netkiller.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ConfigurationProperties(prefix = "mongodb")
@EnableMongoRepositories(basePackages = { "cn.netkiller.repository" }, mongoTemplateRef = MultipleMongoConfigure.primaryMongoTemplate)
public class MultipleMongoConfigure extends AbstractMongoConfigure {

	protected static final String primaryMongoTemplate = "primaryMongoTemplate";
	protected static final String secondaryMongoTemplate = "secondaryMongoTemplate";

	private MongoProperties primary = new MongoProperties();
	private MongoProperties secondary = new MongoProperties();

	public MongoProperties getPrimary() {
		return primary;
	}

	public void setPrimary(MongoProperties primary) {
		this.primary = primary;
	}

	public MongoProperties getSecondary() {
		return secondary;
	}

	public void setSecondary(MongoProperties secondary) {
		this.secondary = secondary;
	}

	public MultipleMongoConfigure() {
	}

	@Primary
	@Bean(name = MultipleMongoConfigure.primaryMongoTemplate)
	@Qualifier(value = MultipleMongoConfigure.primaryMongoTemplate)
	public MongoTemplate primaryMongoTemplate() throws Exception {
		// String uri = "mongodb://name:pass@localhost:27017/test";
		String uri = this.getPrimary().getUri();
		System.out.println(uri);
		// MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(mongoDatabaseFactory(uri)), new MongoMappingContext());
		// converter.setTypeMapper(new DefaultMongoTypeMapper(null));
		// return new MongoTemplate(mongoDatabaseFactory(uri),converter);
		return new MongoTemplate(mongoDatabaseFactory(uri));
	}

	@Bean(name = "secondaryMongoTemplate")
	@Qualifier("secondaryMongoTemplate")
	public MongoTemplate secondaryMongoTemplate() throws Exception {
		String uri = this.getSecondary().getUri();
		return new MongoTemplate(mongoDatabaseFactory(uri));
	}

}

// @Configuration
// @EnableMongoRepositories(basePackages = { "cn.netkiller.repository.primary" }, mongoTemplateRef = MultipleMongoConfigure.primaryMongoTemplate)
// public class PrimaryMongoTemplate {
//
// }