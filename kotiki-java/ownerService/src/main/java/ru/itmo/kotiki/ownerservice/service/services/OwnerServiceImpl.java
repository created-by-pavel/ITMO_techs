package ru.itmo.kotiki.ownerservice.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.common.convertation.Convert;
import ru.itmo.kotiki.common.dto.OwnerDTO;
import ru.itmo.kotiki.common.model.Owner;
import ru.itmo.kotiki.ownerservice.repository.OwnerRepository;
import ru.itmo.kotiki.ownerservice.service.tool.OwnerServiceException;

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
        if (existingOwner == null) throw new OwnerServiceException("cant find");
        existingOwner.setName(ownerDTO.getName());
        existingOwner.setBirthDate(ownerDTO.getBirthDate());
        repository.save(existingOwner);
    }
}
