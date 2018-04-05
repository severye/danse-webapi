package danse.web.boot;

import org.springframework.boot.SpringApplication;

import danse.web.config.AppConfig;

public class Boot {

	public static void main(String[] args) {
		SpringApplication.run(AppConfig.class, args);
	}
}
