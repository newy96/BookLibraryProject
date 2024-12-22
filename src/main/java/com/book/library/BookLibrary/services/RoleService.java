package com.book.library.BookLibrary.services;

import com.book.library.BookLibrary.entities.Role;

public interface RoleService {

    Role findByName(String name);
    void saveRole(Role role);

}
