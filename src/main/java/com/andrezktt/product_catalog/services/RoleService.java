package com.andrezktt.product_catalog.services;

import com.andrezktt.product_catalog.dto.RoleDTO;
import com.andrezktt.product_catalog.entities.Role;
import com.andrezktt.product_catalog.repositories.RoleRepository;
import com.andrezktt.product_catalog.services.exceptions.DatabaseException;
import com.andrezktt.product_catalog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;

    @Transactional(readOnly = true)
    public Page<RoleDTO> findAllPaged(Pageable pageable) {
        return repository.findAll(pageable).map(RoleDTO::new);
    }

    @Transactional(readOnly = true)
    public RoleDTO findById(Long id) {
        return new RoleDTO(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found!")));
    }

    @Transactional
    public RoleDTO insert(RoleDTO dto) {
        Role entity = new Role();
        entity.setAuthority(dto.getAuthority());
        entity = repository.save(entity);
        return new RoleDTO(entity);
    }

    @Transactional
    public RoleDTO update(Long id, RoleDTO dto) {
        try {
            Role entity = repository.getReferenceById(id);
            entity.setAuthority(dto.getAuthority());
            entity = repository.save(entity);
            return new RoleDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Resource not found using ID: " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado!");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial.");
        }
    }
}
