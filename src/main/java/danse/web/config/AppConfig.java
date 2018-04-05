package danse.web.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.DispatcherServlet;

import danse.config.DomainAndPersistenceConfig;

@EnableAutoConfiguration
@ComponentScan(basePackages = { "danse.web" })
@Import({DomainAndPersistenceConfig.class, SecurityConfig.class })
public class AppConfig {
	
}