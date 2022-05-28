package ru.itmo.kotiki.catservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.itmo.kotiki.catservice.service.convertation.Convert;
import ru.itmo.kotiki.catservice.service.convertation.ConvertImpl;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"ru.itmo.kotiki.catservice.*"})
@EntityScan(basePackages = {"ru.itmo.kotiki.**"})
@ComponentScan(basePackages = {"ru.itmo.kotiki.catservice.**"})
public class CatServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatServiceApplication.class, args);
    }

    @Bean
    Convert convert() {
        return new ConvertImpl();
    }
}
