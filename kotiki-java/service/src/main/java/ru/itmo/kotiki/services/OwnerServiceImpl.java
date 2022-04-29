package ru.itmo.kotiki.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.convertation.Convert;
import ru.itmo.kotiki.dto.OwnerDTO;
import ru.itmo.kotiki.model.Owner;
import ru.itmo.kotiki.repository.OwnerRepository;
import ru.itmo.kotiki.tool.KotikiException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OwnerServiceImpl implements OwnerService {
    @Autowired
    private OwnerRepository repository;

    @Autowired
    private Convert convert;

    public void save(OwnerDTO ownerDTO) {
        Owner owner = convert.convertDtoToEntity(ownerDTO);
        repository.save(owner);
    }

    public List<OwnerDTO> getAll() {
        return repository.findAll().stream().map(convert::convertEntityToDto).collect(Collectors.toList());
    }

    public Owner getByName(String name) {
        return repository.findByName(name);
    }

    public OwnerDTO getById(long id) {
        return convert.convertEntityToDto(repository.findById(id).orElse(null));
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public void updateOwner(OwnerDTO ownerDTO) {
        Owner existingOwner = repository.findById(ownerDTO.getId()).orElse(null);
        if (existingOwner == null) throw new KotikiException("cant find");
        existingOwner.setName(ownerDTO.getName());
        existingOwner.setBirthDate(ownerDTO.getBirthDate());
        repository.save(existingOwner);
    }
}
