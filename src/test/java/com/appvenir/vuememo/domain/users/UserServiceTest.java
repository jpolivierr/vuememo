package com.appvenir.vuememo.domain.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.appvenir.vuememo.domain.users.dto.UserDto;
import com.appvenir.vuememo.domain.users.model.User;
import com.appvenir.vuememo.domain.users.repository.UserRepository;
import com.appvenir.vuememo.domain.users.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void savedUser_should_return_User_Object(){
        
        User user = new User("FreddyTest","jp@gmail.com");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.saveUser(user);

        assertNotNull(savedUser, "Saved user should not be null");
        assertEquals(user, savedUser, "Saved user should match the input user");

        verify(userRepository, times(1)).save(user);

    }

    @Test
    void findUserByEmail_should_return_UserDto_when_user_exists(){

        User user = new User("Freddy","jp@gmail.com");

        UserDto userDto = new UserDto(user);

        when(userRepository.findByEmail("jp@gmail.com")).thenReturn(Optional.of(user));

        UserDto foundUser = userService.findUserByEmail("jp@gmail.com");

        verify(userRepository, times(1)).findByEmail("jp@gmail.com");
        assertNotNull(foundUser, "User should not be null");
        assertEquals(userDto, foundUser, "Retrieved user should match the userDto object");
            
    }

    @Test
    void updateUserUsingEmail_should_update_existing_user(){

        String originalEmail = "jp@gmail.com";
        User existingUser = new User("Freddy",originalEmail);

        User userDto = new User("Fred","jpolivier@gmail.com");

        when(userRepository.findByEmail(originalEmail)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);

        UserDto updatedUserDto = userService.updateUserUsingEmail(originalEmail, userDto);

        verify(userRepository, times(1)).findByEmail(originalEmail);
        verify(userRepository, times(1)).save(any(User.class));

        assertEquals(userDto.getName(), updatedUserDto.getName());
        assertEquals(userDto.getEmail(), updatedUserDto.getEmail());

    }
    
}
