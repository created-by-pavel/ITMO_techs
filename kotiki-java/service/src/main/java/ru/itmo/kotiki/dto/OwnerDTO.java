package ru.itmo.kotiki.dto;

import ru.itmo.kotiki.model.Role;

import java.sql.Date;

public class OwnerDTO {
    private final Long id;
    private final String name;
    private final Date birthDate;
    private final String password;
    private final Role role;

    public OwnerDTO(long id, String name, Date birthDate, String password, Role role) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

}
