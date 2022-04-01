package ru.itmo.kotiki_spring.dto;

import lombok.Data;
import ru.itmo.kotiki_spring.enums.Color;
import ru.itmo.kotiki_spring.model.Cat;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data

public class CatDTO {
    private long id;
    private String name;
    private String breed;
    private Color color;
    private Date birthDate;
    private OwnerDTO ownerDTO;
    private List<CatDTO> friends = new ArrayList<>();
}
