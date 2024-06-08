package com.zomerFestival;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "repository" })
@EntityScan("domein")
@ComponentScan({ "service", "com.zomerFestival" })
public class ZomerFestivalApp {
  public static void main(String[] args) {
    SpringApplication.run(ZomerFestivalApp.class, args);
  }
}
