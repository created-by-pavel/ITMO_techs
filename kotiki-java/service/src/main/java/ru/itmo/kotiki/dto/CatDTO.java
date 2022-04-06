package ru.itmo.kotiki.dto;

import ru.itmo.kotiki.enums.Color;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CatDTO {
    private long id;
    private String name;
    private String breed;
    private Color color;
    private Date birthDate;
    private OwnerDTO ownerDTO;
    private List<CatDTO> friends = new ArrayList<>();

    public CatDTO(long id, String name, String breed, Color color, Date birthDate, OwnerDTO ownerDTO, List<CatDTO> friends) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.color = color;
        this.birthDate = birthDate;
        this.ownerDTO = ownerDTO;
        this.friends = friends;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public Color getColor() {
        return color;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public OwnerDTO getOwnerDTO() {
        return ownerDTO;
    }

    public List<CatDTO> getFriends() {
        return friends;
    }
}
