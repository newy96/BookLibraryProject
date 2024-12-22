package com.book.library.BookLibrary.controllers;

import com.book.library.BookLibrary.DTOs.UserDTO;
import com.book.library.BookLibrary.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        UserDTO registeredUser = userService.registerUser(userDTO);
        if (registeredUser != null)
            return new ResponseEntity<>(registeredUser, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.IM_USED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
        boolean authenticated = userService.authenticateUser(username, password);
        if (authenticated)
            return ResponseEntity.ok("Login successful");
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}