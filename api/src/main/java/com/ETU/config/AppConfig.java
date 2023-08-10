package com.ETU.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude= HibernateJpaAutoConfiguration.class)
@ComponentScan("com.ETU")
@Import(HibernateConfiguration.class)
public class AppConfig extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class, args);
    }
}