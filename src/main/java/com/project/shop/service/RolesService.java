package com.project.shop.service;

import com.project.shop.model.Roles;
import com.project.shop.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolesService {
    private final RolesRepository rolesRepository;

    @Autowired
    public RolesService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public Optional<Roles> findByRole(String role) {
        return rolesRepository.findByRole(role);
    }

    public Roles saveRole(Roles role) {
        return rolesRepository.save(role);
    }
}

