package ru.itmo.kotiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.kotiki.enums.Color;
import ru.itmo.kotiki.model.Cat;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
    List<Cat> findAllByOwnerNameAndColor(String ownerName, Color color);

    List<Cat> findByOwnerName(String ownerName);

    Cat findByOwnerNameAndId(String ownerName, long id);

    void deleteByOwnerNameAndId(String ownerName, long id);
}
