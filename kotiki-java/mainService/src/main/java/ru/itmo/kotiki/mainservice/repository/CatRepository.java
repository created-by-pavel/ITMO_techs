package ru.itmo.kotiki.mainservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.kotiki.common.enums.Color;
import ru.itmo.kotiki.common.model.Cat;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
    List<Cat> findAllByOwnerIdAndColor(long ownerId, Color color);

    List<Cat> findByOwnerId(long ownerId);

    Cat findByOwnerIdAndId(long ownerId, long id);

    void deleteByOwnerIdAndId(long ownerId, long id);
}
