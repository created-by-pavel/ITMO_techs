package ru.itmo.kotiki.mainservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.kotiki.common.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
