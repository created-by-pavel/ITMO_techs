package ru.itmo.kotiki_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itmo.kotiki_spring.model.Cat;
import ru.itmo.kotiki_spring.model.Owner;

import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    @Query(value = "SELECT * FROM cat WHERE cat.owner_id = :ownerId", nativeQuery = true)
    List<Cat> getCatsByOwnerId(long ownerId);
}
