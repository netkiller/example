//package cn.netkiller.log;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.slf4j.MDC;
//import org.slf4j.Marker;
//import org.slf4j.MarkerFactory;
//
//import cn.netkiller.log.LogMarker;
//
//public class LogTest {
//
//	private static final Logger logger = LoggerFactory.getLogger(LogTest.class);
//
//	public LogTest() {
//		// TODO Auto-generated constructor stub
//	}
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//		Marker finance = MarkerFactory.getMarker(LogMarker.finance.toString());
//		Marker customer = MarkerFactory.getMarker(LogMarker.customer.toString());
//		Marker market = MarkerFactory.getMarker(LogMarker.market.toString());
//		logger.info("AAAAAAAAA");
//		logger.info(finance, "BBBBBBBBB");
//		logger.error(finance, "This is a serious an error requiring the admin's attention", new Exception("Just testing"));
//		logger.info(finance, "BBBBBBBBB");
//		logger.info(customer, "BBBBBBBBB");
//		logger.info(market, "BBBBBBBBB");
//		
//		MDC.put("userId","0001");
//		logger.info("0001用户");
//        MDC.clear(); 
//
//        MDC.put("userId","0002");
//        logger.info("0002用户");
//        MDC.clear();
//
//	}
//
//}
