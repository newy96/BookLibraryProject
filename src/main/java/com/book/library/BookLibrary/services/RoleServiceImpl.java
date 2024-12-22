package com.book.library.BookLibrary.services;

import com.book.library.BookLibrary.entities.Role;
import com.book.library.BookLibrary.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public Role findByName(String name) {

        return roleRepository.findByName(name);
    }

    public void saveRole(Role role) {

        roleRepository.save(role);
    }

}