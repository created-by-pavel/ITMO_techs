package ru.itmo.kotiki_spring.convertation;

import ru.itmo.kotiki_spring.dto.CatDTO;
import ru.itmo.kotiki_spring.dto.OwnerDTO;
import ru.itmo.kotiki_spring.model.Cat;
import ru.itmo.kotiki_spring.model.Owner;

public interface IConvert {
    public CatDTO convertEntityToDto(Cat cat);
    public Cat convertDtoToEntity(CatDTO catDTO);
    public OwnerDTO convertEntityToDto(Owner owner);
    public Owner convertDtoToEntity(OwnerDTO ownerDTO);
}
