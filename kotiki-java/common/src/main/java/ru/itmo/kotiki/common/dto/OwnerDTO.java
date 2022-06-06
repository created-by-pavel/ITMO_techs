package ru.itmo.kotiki.common.dto;

import ru.itmo.kotiki.common.model.User;

import java.io.Serializable;
import java.sql.Date;

public class OwnerDTO implements Serializable {
    private long id;
    private String name;
    private Date birthDate;
    private User user;

    public OwnerDTO(long id, String name, Date birthDate, User user) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.user = user;
    }

    public OwnerDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
