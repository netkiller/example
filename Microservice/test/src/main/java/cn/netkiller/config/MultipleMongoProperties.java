//package cn.netkiller.config;
//
//import org.springframework.boot.autoconfigure.mongo.MongoProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//
//@ConfigurationProperties(prefix = "mongodb")
//public class MultipleMongoProperties {
//
//	public MultipleMongoProperties() {
//		// TODO Auto-generated constructor stub
//	}
//
//	private MongoProperties primary = new MongoProperties();
//	private MongoProperties secondary = new MongoProperties();
//
//	public MongoProperties getPrimary() {
//		return primary;
//	}
//
//	public void setPrimary(MongoProperties primary) {
//		this.primary = primary;
//	}
//
//	public MongoProperties getSecondary() {
//		return secondary;
//	}
//
//	public void setSecondary(MongoProperties secondary) {
//		this.secondary = secondary;
//	}
//
//}