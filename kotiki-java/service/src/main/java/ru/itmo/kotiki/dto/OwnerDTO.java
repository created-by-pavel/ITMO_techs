package ru.itmo.kotiki.dto;

import ru.itmo.kotiki.model.User;

import java.sql.Date;

public class OwnerDTO {
    private final Long id;
    private final String name;
    private final Date birthDate;
    private final User user;

    public OwnerDTO(long id, String name, Date birthDate, User user) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.user = user;
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

    public User getUser() {
        return user;
    }

}
