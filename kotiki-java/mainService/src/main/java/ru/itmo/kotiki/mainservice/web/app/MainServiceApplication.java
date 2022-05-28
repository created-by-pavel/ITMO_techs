package ru.itmo.kotiki.mainservice.web.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.itmo.kotiki.common.model.Role;
import ru.itmo.kotiki.common.model.User;
import ru.itmo.kotiki.mainservice.service.services.RoleService;
import ru.itmo.kotiki.mainservice.service.services.UserService;

@SpringBootApplication
public class MainServiceApplication {

    appConfig appConfig = new appConfig();

    public static void main(String[] args) {
        SpringApplication.run(MainServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner run(RoleService roleService, UserService userService) {
        return args -> {
            var admin = roleService.save(new Role(1L, "ROLE_ADMIN"));
            var user = userService.save(new User(1L, "admin", "1234", admin));
        };
    }
}
