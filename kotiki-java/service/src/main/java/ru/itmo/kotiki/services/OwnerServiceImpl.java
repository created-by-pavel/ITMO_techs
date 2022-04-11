package ru.itmo.kotiki.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.convertation.Convert;
import ru.itmo.kotiki.dto.OwnerDTO;
import ru.itmo.kotiki.model.Owner;
import ru.itmo.kotiki.repository.OwnerRepository;
import ru.itmo.kotiki.tool.KotikiException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OwnerServiceImpl implements OwnerService, UserDetailsService {
    @Autowired
    private OwnerRepository repository;

    @Autowired
    private Convert convert;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Owner owner = repository.findByName(name);
        if (owner == null) throw new KotikiException("cant find owner");
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(owner.getRole().getName()));
        return new org.springframework.security.core.userdetails.User(owner.getName(), owner.getPassword(), authorities);
    }

    public void save(OwnerDTO ownerDTO) {
        Owner owner = convert.convertDtoToEntity(ownerDTO);
        owner.setPassword(passwordEncoder.encode(owner.getPassword()));
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
