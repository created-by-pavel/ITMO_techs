package ru.itmo.kotiki.catservice.service.convertation;

import ru.itmo.kotiki.common.dto.CatDTO;
import ru.itmo.kotiki.common.dto.OwnerDTO;
import ru.itmo.kotiki.common.model.Cat;
import ru.itmo.kotiki.common.model.Owner;

import java.util.List;
import java.util.stream.Collectors;

public class ConvertImpl implements Convert {

    public CatDTO convertEntityToDto(Cat cat) {
        var cats = cat.getFriends();
        List<CatDTO> catsDTO = null;

        if (cats != null) {
            catsDTO = cats.stream().map(this::convertEntityToDto).collect(Collectors.toList());
        }

        CatDTO catDTO = new CatDTO(
                cat.getId(),
                cat.getName(),
                cat.getBreed(),
                cat.getColor(),
                cat.getBirthDate(),
                convertEntityToDto(cat.getOwner()),
                catsDTO);
        return catDTO;
    }

    public Cat convertDtoToEntity(CatDTO catDTO) {
        var catsDTO = catDTO.getFriends();
        List<Cat> cats = null;

        if (catsDTO != null) {
            cats = catsDTO.stream().map(this::convertDtoToEntity).collect(Collectors.toList());
        }

        Cat cat = new Cat();
        cat.setId(catDTO.getId());
        cat.setName(catDTO.getName());
        cat.setColor(catDTO.getColor());
        cat.setBreed(catDTO.getBreed());
        cat.setFriends(cats);
        cat.setOwner(convertDtoToEntity(catDTO.getOwnerDTO()));
        cat.setBirthDate(catDTO.getBirthDate());
        return cat;
    }

    public OwnerDTO convertEntityToDto(Owner owner) {
        OwnerDTO ownerDTO = new OwnerDTO(owner.getId(), owner.getName(), owner.getBirthDate(), owner.getUser());
        return ownerDTO;
    }

    public Owner convertDtoToEntity(OwnerDTO ownerDTO) {
        Owner owner = new Owner();
        owner.setId(ownerDTO.getId());
        owner.setBirthDate(ownerDTO.getBirthDate());
        owner.setName(ownerDTO.getName());
        owner.setUser(ownerDTO.getUser());
        return owner;
    }
}
