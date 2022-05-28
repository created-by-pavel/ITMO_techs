package ru.itmo.kotiki.catservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.kotiki.common.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
}
