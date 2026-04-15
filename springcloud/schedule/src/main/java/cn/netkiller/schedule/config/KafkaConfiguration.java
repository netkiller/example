package cn.netkiller.schedule.config;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.IntegerSerializer;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//
@Configuration
@EnableKafka
public class KafkaConfiguration {
//	private static final Logger logger = LoggerFactory.getLogger(KafkaConfiguration.class);
//
//	@Value("${spring.kafka.bootstrap_servers}")
//	private String bootstrap_servers;
//
//	public KafkaConfiguration() {
//	}
//
//	@Bean
//	public Map<String, Object> producerConfigs() {
//		HashMap<String, Object> props = new HashMap<>();
//
//		// props.put("zk.connect", "127.0.0.1:2181");
//		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrap_servers);
//		props.put(ProducerConfig.RETRIES_CONFIG, 0);
//		props.put(ProducerConfig.BATCH_SIZE_CONFIG, 4096);
//		props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
//		props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 40960);
//		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
//		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//		// value to block, after which it will throw a TimeoutException
//		// props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 5000);
//		logger.info("Kafka {}", props.toString());
//		return props;
//	}
//
//	public ProducerFactory<String, String> producerFactory() {
//		return new DefaultKafkaProducerFactory<String, String>(producerConfigs());
//	}
//
//	@Bean
//	public KafkaTemplate<String, String> kafkaTemplate() {
//		return new KafkaTemplate<String, String>(producerFactory());
//	}
}
