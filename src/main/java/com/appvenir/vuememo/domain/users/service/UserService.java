package com.appvenir.vuememo.domain.users.service;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appvenir.vuememo.domain.users.dto.UserDto;
import com.appvenir.vuememo.domain.users.model.User;
import com.appvenir.vuememo.domain.users.repository.UserRepository;
import com.appvenir.vuememo.exception.user.EmailExistsException;
import com.appvenir.vuememo.exception.user.UserNotFoundException;

import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User saveUser(User user){

        try {

            return userRepository.save(user);

        } catch (ConstraintViolationException | DataIntegrityViolationException e) {
            
            throw new EmailExistsException();

        }
        

    }

    public UserDto findUserByEmail(String email){

       Optional<User> foundUser = userRepository.findByEmail(email);

       if(!foundUser.isPresent()) throw new UserNotFoundException();

       return new UserDto(foundUser.get());

    }

    public User findByEmail(String email){

        Optional<User> foundUser = userRepository.findByEmail(email);
 
        if(!foundUser.isPresent()) throw new UserNotFoundException();
 
        return foundUser.get();
 
     }

    @Transactional
    public UserDto updateUserUsingEmail(String email, User user){

        User foundUser = findByEmail(email);

        foundUser.setName(user.getName());
        foundUser.setEmail(user.getEmail());

        return new UserDto(userRepository.save(foundUser));
     }

    @Transactional
    public void deleteUserUsingEmail(String email){

        findByEmail(email);

        userRepository.deleteByEmail(email);

     }
    
}
