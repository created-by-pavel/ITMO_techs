package ru.itmo.kotiki.convertation;

import ru.itmo.kotiki.dto.CatDTO;
import ru.itmo.kotiki.dto.OwnerDTO;
import ru.itmo.kotiki.model.Cat;
import ru.itmo.kotiki.model.Owner;

public interface Convert {

    CatDTO convertEntityToDto(Cat cat);

    Cat convertDtoToEntity(CatDTO catDTO);

    OwnerDTO convertEntityToDto(Owner owner);

    Owner convertDtoToEntity(OwnerDTO ownerDTO);
}
