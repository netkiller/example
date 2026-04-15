package cn.netkiller.config;

import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import com.mongodb.ConnectionString;

public abstract class AbstractMongoConfigure {

	public MongoDatabaseFactory mongoDatabaseFactory(String uri) {
		ConnectionString connectionString = new ConnectionString(uri);
		return new SimpleMongoClientDatabaseFactory(connectionString);
	}

	// abstract public MongoTemplate getMongoTemplate() throws Exception;

}
