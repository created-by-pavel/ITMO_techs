package ru.itmo.kotiki.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.itmo.kotiki.dto.OwnerDTO;
import ru.itmo.kotiki.model.Role;
import ru.itmo.kotiki.model.User;
import ru.itmo.kotiki.services.OwnerService;
import ru.itmo.kotiki.services.RoleService;
import ru.itmo.kotiki.services.UserService;


@SpringBootApplication
public class KotikiApplication {

    AppConfig config = new AppConfig();

    public static void main(String[] args) {
        SpringApplication.run(KotikiApplication.class);
    }

    @Bean
    CommandLineRunner run(OwnerService ownerService, RoleService roleService, UserService userService) {
        return args -> {
            var admin = roleService.save(new Role(1L, "ROLE_ADMIN"));
            var user = userService.save(new User(1L, "admin", "1234", admin));
            ownerService.save(new OwnerDTO(1L, "A", null, user));
        };
    }

}
