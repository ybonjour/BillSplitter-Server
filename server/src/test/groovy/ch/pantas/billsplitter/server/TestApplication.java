package ch.pantas.billsplitter.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@ComponentScan
@EnableAutoConfiguration
@Configuration
@PropertySource("classpath:application.properties")
public class TestApplication extends WebMvcConfigurerAdapter {
        public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}