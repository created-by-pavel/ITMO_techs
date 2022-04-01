package ru.itmo.kotiki_spring.convertation;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import ru.itmo.kotiki_spring.dto.CatDTO;
import ru.itmo.kotiki_spring.dto.OwnerDTO;
import ru.itmo.kotiki_spring.model.Cat;
import ru.itmo.kotiki_spring.model.Owner;

public class Convert implements IConvert{

    @Autowired
    private ModelMapper modelMapper;

    public CatDTO convertEntityToDto(Cat cat) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        CatDTO catDTO = new CatDTO();
        catDTO = modelMapper.map(cat, CatDTO.class);
        return catDTO;
    }
    public Cat convertDtoToEntity(CatDTO catDTO) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Cat cat = new Cat();
        cat = modelMapper.map(catDTO, Cat.class);
        return cat;
    }
    public OwnerDTO convertEntityToDto(Owner owner) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO = modelMapper.map(owner, OwnerDTO.class);
        return ownerDTO;
    }
    public Owner convertDtoToEntity(OwnerDTO ownerDTO) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Owner owner = new Owner();
        owner = modelMapper.map(ownerDTO, Owner.class);
        return owner;
    }
}
