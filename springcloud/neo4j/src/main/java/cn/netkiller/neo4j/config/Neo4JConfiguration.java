package cn.netkiller.neo4j.config;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableNeo4jRepositories
public class Neo4JConfiguration {
	@Bean
	public SessionFactory sessionFactory() {
		return new SessionFactory("org.mythsky.neo4jdemo");
	}

	@Bean
	public Neo4jTransactionManager transactionManager() {
		return new Neo4jTransactionManager(sessionFactory());
	}

	// public Neo4JConfiguration() {
	// setBasePackage("data");
	// }
	//
	// @Bean(destroyMethod = "shutdown")
	// public GraphDatabaseService graphDatabaseService() {
	// return new GraphDatabaseFactory().newEmbeddedDatabase("target/recommendation.db");
	// }
}
