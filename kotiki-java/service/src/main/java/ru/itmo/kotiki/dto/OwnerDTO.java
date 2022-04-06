package ru.itmo.kotiki.dto;

import java.sql.Date;

public class OwnerDTO {
    private long id;
    private String name;
    private Date birthDate;

    public OwnerDTO(long id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

}
