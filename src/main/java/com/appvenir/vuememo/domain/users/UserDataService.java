package com.appvenir.vuememo.domain.users;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.appvenir.vuememo.exception.user.UserNotFoundException;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class UserDataService implements UserDetailsService{

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        

        return userRepository.findByEmail(email)
                            .map(UserSecurity::new)
                            .orElseThrow( () -> new UserNotFoundException());

    }
     
}
