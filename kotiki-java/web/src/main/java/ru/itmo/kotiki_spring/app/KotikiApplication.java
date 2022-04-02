package ru.itmo.kotiki.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.itmo.kotiki.convertation.Convert;
import ru.itmo.kotiki.convertation.ConvertImpl;

@SpringBootApplication
@ComponentScan(basePackages = {"ru.itmo.kotiki_spring.*"})
@EntityScan(basePackages = {"ru.itmo.kotiki_spring.*"})
@EnableJpaRepositories(basePackages = {"ru.itmo.kotiki_spring.*"})
public class KotikiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KotikiApplication.class);
    }

    @Bean
    public Convert convert() {
        return new ConvertImpl();
    }
}
