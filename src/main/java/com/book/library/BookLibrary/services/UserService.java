package com.book.library.BookLibrary.services;

import com.book.library.BookLibrary.DTOs.UserDTO;

public interface UserService {

    UserDTO registerUser(UserDTO userDTO);
    boolean authenticateUser(String username, String password);

}
