package com.appvenir.vuememo.security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserAuthenticationFailure extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(
        HttpServletRequest request,
        HttpServletResponse response,
        AuthenticationException exception
        ) throws IOException, ServletException {
       
        String failureUrl = "/login?error=true&exception=" + exception.getMessage();
        super.setDefaultFailureUrl(failureUrl);
        super.onAuthenticationFailure(request, response, exception);
            

    }
    
}
