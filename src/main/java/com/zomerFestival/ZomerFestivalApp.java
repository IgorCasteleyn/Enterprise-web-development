package com.zomerFestival;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import validator.NieuwOptredenValidation;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import perform.PerformRest;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "repository" })
@EntityScan("domein")
@ComponentScan({ "service", "com.zomerFestival" })
public class ZomerFestivalApp implements WebMvcConfigurer {
  public static void main(String[] args) {
    SpringApplication.run(ZomerFestivalApp.class, args);
  
    try {
			new PerformRest();
		} catch (Exception e) {
			e.printStackTrace();
		}
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addRedirectViewController("/", "/login");
  }

  @Bean
  NieuwOptredenValidation nieuwOptredenValidation() {
    return new NieuwOptredenValidation();
  }
}
