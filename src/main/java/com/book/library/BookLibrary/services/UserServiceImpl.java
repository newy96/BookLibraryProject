package com.book.library.BookLibrary.services;

import com.book.library.BookLibrary.DTOs.UserDTO;
import com.book.library.BookLibrary.Mapper.Mapper;
import com.book.library.BookLibrary.entities.Role;
import com.book.library.BookLibrary.repositories.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.book.library.BookLibrary.repositories.UserRepository;
import com.book.library.BookLibrary.entities.User;
import java.util.Optional;

@Service
public class UserServiceImpl implements  UserService{

    private static final String UserRole = "ROLE_USER";
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private Mapper mapper;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, Mapper mapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }

    public UserDTO registerUser(UserDTO userDTO) {
        String username = userDTO.getUsername();
        if (userRepository.findByUsername(username).isPresent()) {
            return null;
        }

        String hashedPassword = PasswordEncryptor.hashPassword(userDTO.getPassword());
        userDTO.setPassword(hashedPassword);

        User userToSave = mapper.modelMapper.map(userDTO, User.class);

        Role role = roleRepository.findByName(UserRole);
        userToSave.setRole(role);

        User savedUser = userRepository.save(userToSave);

        return mapper.modelMapper.map(savedUser, UserDTO.class);
    }

    public boolean authenticateUser(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return PasswordEncryptor.checkPassword(password, user.getPassword());
        }

        return false;
    }
}
