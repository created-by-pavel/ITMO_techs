package ru.itmo.kotiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.kotiki.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
