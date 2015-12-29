package ch.pantas.billsplitter.server;

import ch.pantas.billsplitter.server.services.EventImporter;
import org.prevayler.Prevayler;
import org.prevayler.PrevaylerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@ComponentScan
@EnableAutoConfiguration
@Configuration
public class Application extends WebMvcConfigurerAdapter {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Prevayler<EventImporter> eventImporter() throws Exception {
        return PrevaylerFactory.createPrevayler(new EventImporter());
    }
}