package ru.itmo.kotiki.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.kotiki.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
}
