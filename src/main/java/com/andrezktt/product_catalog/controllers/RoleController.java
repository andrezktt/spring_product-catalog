package com.andrezktt.product_catalog.controllers;

import com.andrezktt.product_catalog.dto.RoleDTO;
import com.andrezktt.product_catalog.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Controller
@RequestMapping(value = "/roles")
public class RoleController {

    @Autowired
    private RoleService service;

    @GetMapping
    public ResponseEntity<Page<RoleDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(service.findAllPaged(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RoleDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<RoleDTO> insert(@RequestBody RoleDTO dto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(service.insert(dto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RoleDTO> update(@PathVariable Long id, @RequestBody RoleDTO dto) {
        return ResponseEntity.ok().body(service.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
