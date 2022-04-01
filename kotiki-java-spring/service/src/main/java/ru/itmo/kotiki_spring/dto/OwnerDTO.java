package ru.itmo.kotiki_spring.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class OwnerDTO {
    private long id;
    private String name;
    private Date birthDate;
}
