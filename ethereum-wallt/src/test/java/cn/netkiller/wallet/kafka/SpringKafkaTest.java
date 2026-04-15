//package cn.netkiller.wallet.kafka;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertThat;
//
//import java.util.concurrent.TimeUnit;
//
//import org.junit.ClassRule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.kafka.test.rule.KafkaEmbedded;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import cn.netkiller.wallet.kafka.consumer.Receiver;
//import cn.netkiller.wallet.kafka.producer.Sender;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class SpringKafkaTest {
//
//	public SpringKafkaTest() {
//		// TODO Auto-generated constructor stub
//	}
//
//	private static String TOPIC = "test.1";
//
//	@Autowired
//	private Sender sender;
//
//	@Autowired
//	private Receiver receiver;
//
//	@ClassRule
//	public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, TOPIC);
//
//	@Test
//	public void testReceive() throws Exception {
//		sender.send(TOPIC, "Hello Boot!");
//
//		receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
//		System.out.println("============== :" + receiver.getLatch().getCount());
//		assertThat(receiver.getLatch().getCount()).isEqualTo(0);
//	}
//
//}