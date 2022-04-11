package ru.itmo.kotiki.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itmo.kotiki.Security.DecodeTokenUtil;
import ru.itmo.kotiki.convertation.Convert;
import ru.itmo.kotiki.convertation.ConvertImpl;
import ru.itmo.kotiki.dto.OwnerDTO;
import ru.itmo.kotiki.model.Role;
import ru.itmo.kotiki.services.OwnerService;
import ru.itmo.kotiki.services.RoleService;


@SpringBootApplication
@ComponentScan(basePackages = {"ru.itmo.kotiki.*"})
@EntityScan(basePackages = {"ru.itmo.kotiki.*"})
@EnableJpaRepositories(basePackages = {"ru.itmo.kotiki.*"})
public class KotikiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KotikiApplication.class);
    }

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

    @Bean
    CommandLineRunner run(OwnerService ownerService, RoleService roleService) {
        return args -> {
            var admin = roleService.save(new Role(1L, "ROLE_ADMIN"));
            ownerService.save(new OwnerDTO(1L, "admin", null, "1234", admin));
        };
    }

}
