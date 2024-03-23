package com.appvenir.vuememo.domain.users;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appvenir.vuememo.exception.user.UserNotFoundException;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void saveUser(User user){

        userRepository.save(user);

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
    public UserDto updateUserUsingEmail(String email, UserDto user){

        User foundUser = findByEmail(email);

        foundUser.setFirstName(user.getFirstName());
        foundUser.setLastName(user.getLastName());
        foundUser.setEmail(user.getEmail());
 
        userRepository.save(foundUser);

        return new UserDto(foundUser);
     }

    @Transactional
    public void deleteUserUsingEmail(String email){

        findByEmail(email);

        userRepository.deleteByEmail(email);
        
     }
    
}
