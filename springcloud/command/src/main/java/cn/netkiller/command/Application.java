package cn.netkiller.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements ApplicationRunner {
	private static Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		logger.info("Application");
		System.out.println("# NonOptionArgs: " + args.getNonOptionArgs().size());
		System.out.println("NonOptionArgs:");
		args.getNonOptionArgs().forEach(System.out::println);
		System.out.println("# OptionArgs: " + args.getOptionNames().size());
		System.out.println("OptionArgs:");
		args.getOptionNames().forEach(optionName -> {
			System.out.println(optionName + "=" + args.getOptionValues(optionName));
		});
	}
	// java -jar command-0.0.1-SNAPSHOT.jar iamnonoption --app.name=CmdRulez --app.hosts=abc,def,ghi --app.name=2

}