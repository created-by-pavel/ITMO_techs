package ru.itmo.kotiki_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.kotiki_spring.enums.Color;
import ru.itmo.kotiki_spring.model.Cat;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
    List<Cat> findByColor(Color color);
}
