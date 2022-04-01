package ru.itmo.kotiki_spring.app;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.itmo.kotiki_spring.convertation.Convert;
import ru.itmo.kotiki_spring.convertation.IConvert;

@SpringBootApplication
@ComponentScan(basePackages = {"ru.itmo.kotiki_spring.*"})
@EntityScan(basePackages = {"ru.itmo.kotiki_spring.*"})
@EnableJpaRepositories(basePackages = {"ru.itmo.kotiki_spring.*"})
public class KotikiApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public IConvert convert() {
        return new Convert();
    }

    public static void main(String[] args) {
        SpringApplication.run(KotikiApplication.class);
    }
}
