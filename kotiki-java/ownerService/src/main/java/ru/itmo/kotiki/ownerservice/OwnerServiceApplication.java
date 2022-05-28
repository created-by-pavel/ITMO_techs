package ru.itmo.kotiki.ownerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.itmo.kotiki.ownerservice.service.convertation.Convert;
import ru.itmo.kotiki.ownerservice.service.convertation.ConvertImpl;

@EnableJpaRepositories(basePackages = {"ru.itmo.kotiki.ownerservice.*"})
@EntityScan(basePackages = {"ru.itmo.kotiki.**"})
@ComponentScan(basePackages = {"ru.itmo.kotiki.ownerservice.**"})
@SpringBootApplication
public class OwnerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OwnerServiceApplication.class, args);
    }

    @Bean
    Convert convert() {
        return new ConvertImpl();
    }
}
