package cn.netkiller.config;
//package cn.netkiller.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.mongo.MongoProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.mongodb.MongoDatabaseFactory;
//import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//
//import com.mongodb.ConnectionString;
//import com.mongodb.client.MongoClient;
//
//@Configuration
////@EnableMongoRepositories(basePackages = "cn.netkiller.repository.primary", mongoTemplateRef = PrimaryMongoConfig.MONGO_TEMPLATE)
////@EnableMongoRepositories(basePackages = "cn.netkiller.repository.secondary", mongoTemplateRef = SecondaryMongoConfig.MONGO_TEMPLATE)
//public class MultipleMongoConfiguration {
//
//	public MultipleMongoConfiguration() {
//		// TODO Auto-generated constructor stub
//	}
//
//	@Autowired
//	private MultipleMongoProperties mongoProperties;
//
//	@Primary
//	@Bean(name = PrimaryMongoConfig.MONGO_TEMPLATE)
//	public MongoTemplate primaryMongoTemplate() throws Exception {
//		return new MongoTemplate(primaryFactory(this.mongoProperties.getPrimary()));
//	}
//
//	@Bean
//	@Primary
//	public MongoDatabaseFactory primaryFactory(MongoProperties mongo) throws Exception {
//		ConnectionString connectionString = new ConnectionString(mongo.getUri());
//		return new SimpleMongoClientDatabaseFactory(connectionString);
////		return new SimpleMongoClientDatabaseFactory(new MongoClient(mongo.getHost(), mongo.getPort()), mongo.getDatabase());
//	}
//
//	@Bean
//	@Qualifier(SecondaryMongoConfig.MONGO_TEMPLATE)
//	public MongoTemplate secondaryMongoTemplate() throws Exception {
//		return new MongoTemplate(secondaryFactory(this.mongoProperties.getSecondary()));
//	}
//
//	@Bean
//	public MongoDatabaseFactory secondaryFactory(MongoProperties mongo) throws Exception {
//		return new SimpleMongoClientDatabaseFactory(new MongoClient(mongo.getHost(), mongo.getPort()), mongo.getDatabase());
//	}
//
//	//	@Configuration
//	// @EnableMongoRepositories(basePackages = "cn.netkiller.repository.primary", mongoTemplateRef = PrimaryMongoConfig.MONGO_TEMPLATE)
//	public class PrimaryMongoConfig {
//
//		protected static final String MONGO_TEMPLATE = "primaryMongoTemplate";
//	}
//
////	@Configuration
//	// @EnableMongoRepositories(basePackages = "cn.netkiller.repository.secondary", mongoTemplateRef = SecondaryMongoConfig.MONGO_TEMPLATE)
//	public class SecondaryMongoConfig {
//
//		protected static final String MONGO_TEMPLATE = "secondaryMongoTemplate";
//	}
//
//}
