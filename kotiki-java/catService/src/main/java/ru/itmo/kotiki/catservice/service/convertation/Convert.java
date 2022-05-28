package ru.itmo.kotiki.catservice.service.convertation;

import ru.itmo.kotiki.common.dto.CatDTO;
import ru.itmo.kotiki.common.dto.OwnerDTO;
import ru.itmo.kotiki.common.model.Cat;
import ru.itmo.kotiki.common.model.Owner;


public interface Convert {

    CatDTO convertEntityToDto(Cat cat);

    Cat convertDtoToEntity(CatDTO catDTO);

    OwnerDTO convertEntityToDto(Owner owner);

    Owner convertDtoToEntity(OwnerDTO ownerDTO);
}
