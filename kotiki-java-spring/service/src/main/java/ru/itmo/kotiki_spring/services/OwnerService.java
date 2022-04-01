package ru.itmo.kotiki_spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki_spring.convertation.IConvert;
import ru.itmo.kotiki_spring.dto.OwnerDTO;
import ru.itmo.kotiki_spring.model.Owner;
import ru.itmo.kotiki_spring.repository.OwnerRepository;
import ru.itmo.kotiki_spring.tool.KotikiException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerService {
    @Autowired
    private OwnerRepository repository;

    @Autowired
    private IConvert convert;

    public void save(OwnerDTO ownerDTO) {
        Owner owner = convert.convertDtoToEntity(ownerDTO);
        repository.save(owner);
    }

    public List<OwnerDTO> getAll() {
        return repository.findAll().stream().map(convert::convertEntityToDto).collect(Collectors.toList());
    }

    public OwnerDTO getById(long id) {
        return convert.convertEntityToDto(repository.findById(id).orElse(null));
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public void updateOwner(OwnerDTO ownerDTO) {
        Owner existingOwner = repository.findById(ownerDTO.getId()).orElse(null);
        if(existingOwner == null) throw new KotikiException("cant find");
        existingOwner.setName(ownerDTO.getName());
        existingOwner.setBirthDate(ownerDTO.getBirthDate());
        repository.save(existingOwner);
    }
}
