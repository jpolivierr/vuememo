package com.appvenir.vuememo.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.appvenir.vuememo.domain.users.UserDataService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JpaAuthenticationProvider implements AuthenticationProvider {

    private final UserDataService userDataService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        // Will throw a UserNotFoundException if the user is not found
        UserDetails userDetauDetails = userDataService.loadUserByUsername(email);

        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.");
        System.out.println("validating user");
        if(!password.equals(userDetauDetails.getPassword())) {
            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.");
        System.out.println("INVALID CREDENTIALS......");
             throw new BadCredentialsException("Invalid credentials");
        }
        System.out.println("###################################.");
        System.out.println("Authentication successful..");
        return new UsernamePasswordAuthenticationToken(userDetauDetails, password);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
}
