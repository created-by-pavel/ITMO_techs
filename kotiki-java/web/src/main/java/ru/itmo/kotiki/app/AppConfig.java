package ru.itmo.kotiki.app;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itmo.kotiki.security.DecodeTokenUtil;
import ru.itmo.kotiki.convertation.Convert;
import ru.itmo.kotiki.convertation.ConvertImpl;

@Configuration
@EnableJpaRepositories(basePackages = {"ru.itmo.kotiki.*"})
@EntityScan(basePackages = {"ru.itmo.kotiki.*"})
@ComponentScan(basePackages = {"ru.itmo.kotiki.*"})
public class AppConfig {
    @Bean
    Convert convert() {
        return new ConvertImpl();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DecodeTokenUtil decodeTokenUtil() {
        return new DecodeTokenUtil();
    }
}
