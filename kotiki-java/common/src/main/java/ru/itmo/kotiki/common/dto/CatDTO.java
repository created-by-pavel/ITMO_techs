package ru.itmo.kotiki.common.dto;


import ru.itmo.kotiki.common.enums.Color;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CatDTO implements Serializable {
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

    public CatDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public OwnerDTO getOwnerDTO() {
        return ownerDTO;
    }

    public void setOwnerDTO(OwnerDTO ownerDTO) {
        this.ownerDTO = ownerDTO;
    }

    public List<CatDTO> getFriends() {
        return friends;
    }

    public void setFriends(List<CatDTO> friends) {
        this.friends = friends;
    }
}
