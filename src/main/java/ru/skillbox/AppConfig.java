package ru.skillbox;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "ru.skillbox")
@PropertySource(value = "classpath:application.properties")
public class AppConfig {
}
