package cn.netkiller.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

public class TestCommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(TestCommandLineRunner.class);

	@Component
	@Order(1)
	public class CommandRunner1 implements CommandLineRunner {

		@Override
		public void run(String... args) throws Exception {
			logger.info("执行第一个command line runner...");
		}

	}

	@Component
	@Order(2)
	public class CommandRunner2 implements CommandLineRunner {

		@Override
		public void run(String... args) throws Exception {
			logger.info("执行第二个command line runner...");
		}

	}

	@Component
	@Order(3)
	public class CommandRunner3 implements CommandLineRunner {

		@Override
		public void run(String... args) throws Exception {
			logger.info("执行第三个command line runner...");
		}

	}

	@Component
	public class ApplicationRunner1 implements ApplicationRunner {

		@Override
		public void run(ApplicationArguments args) throws Exception {
			logger.info("执行application runner...");
			logger.info("获取到参数: " + args.getOptionValues("param"));
		}
	}
}
