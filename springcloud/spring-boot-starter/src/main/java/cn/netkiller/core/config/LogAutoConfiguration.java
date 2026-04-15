package cn.netkiller.core.config;

import org.slf4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.netkiller.core.annotation.LogProcessor;

/**
 * 日志自动装配
 * @author xsx
 * @date 2019/6/19
 * @since 1.8
 */
@Configuration
@ConditionalOnClass({Logger.class})
public class LogAutoConfiguration {

    @Bean
    public LogProcessor logProcessor() {
        return new LogProcessor();
    }
}
