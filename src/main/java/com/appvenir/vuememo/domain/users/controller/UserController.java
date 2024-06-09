package com.appvenir.vuememo.domain.users.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.appvenir.vuememo.domain.users.dto.UserDto;
import com.appvenir.vuememo.domain.users.dto.UserRegistrationDto;
import com.appvenir.vuememo.domain.users.model.User;
import com.appvenir.vuememo.domain.users.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //Create user
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@Valid @RequestBody UserRegistrationDto user){
        userService.saveUser(new User(user));
    }

    //Get user
    @GetMapping("/{email}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDto> findUserByEmail(@PathVariable String email){
        return ResponseEntity.ok().body(userService.findUserByEmail(email));
    }

    //Update user
    @PutMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDto> updateUserUsingEmail(@PathVariable String email,  @RequestBody @Valid User user){
        UserDto foundUser = userService.updateUserUsingEmail(email, user);
        return ResponseEntity.ok().body(foundUser);
    }

    //Delete user
    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserUsingEmail(@PathVariable String email){
         userService.deleteUserUsingEmail(email);
    }
    
}
