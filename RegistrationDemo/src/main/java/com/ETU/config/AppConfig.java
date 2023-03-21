package com.ETU.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
@ComponentScan("com.ETU") // DI с помощью аннотаций
@EnableWebMvc // Включаем MVC функции Spring
//@PropertySource("classpath:jdbc.properties") // Указываем путь до отдельного файла с данными для подключения к БД
public class AppConfig implements WebMvcConfigurer {

    // Инициализируем параметры подключения к БД
//    @Value("${jdbc.driverClassName}")
//    private String driver;
//    @Value("${jdbc.url}")
//    private String url;
//    @Value("${jdbc.username}")
//    private String username;
//    @Value("${jdbc.password}")
//    private String password;

    // Подключение к базе данных
//    @Bean
//    public DataSource dataSource() {
//        // Так как реализация интерфейса DataSource класс DriverManagerDataSource
//        // не поддерживает пул соединений с БД, то он пригоден только для тестирования
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//
//        dataSource.setDriverClassName(driver);
//        dataSource.setUrl(url);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//
//        return dataSource;
//    }

    // Инициализируем объект класса JdbcTemplate
    // Это надстройка над JDBC, запросы писать теперь удобнее и безопаснее(по умолчанию preparedStatement)
    // Даём конструктору класса параметры на подключение к БД
//    @Bean
//    public JdbcTemplate jdbcTemplate(){
//        return new JdbcTemplate(dataSource());
//    }
}
