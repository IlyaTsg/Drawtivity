package com.ETU.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("com.ETU") // DI с помощью аннотаций
@EnableWebMvc // Включаем MVC функции Spring
public class AppConfig implements WebMvcConfigurer {
}
