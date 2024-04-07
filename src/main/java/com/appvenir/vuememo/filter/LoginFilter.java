package com.appvenir.vuememo.filter;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.appvenir.vuememo.domain.users.UserLogin;
import com.appvenir.vuememo.domain.users.UserLoginValidator;
import com.appvenir.vuememo.exception.user.UserNotFoundException;
import com.appvenir.vuememo.exception.validationException.ValidationException;
import com.appvenir.vuememo.helper.paramBuilder.QueryParamBuilder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginFilter extends UsernamePasswordAuthenticationFilter{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

                HttpServletRequest httpRequest = (HttpServletRequest) request;
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                String email = httpRequest.getParameter("email");
                String password = httpRequest.getParameter("password");
                try {

                if ("POST".equalsIgnoreCase(httpRequest.getMethod())) {
                    new UserLoginValidator(new UserLogin(email, password)).validate();;
                }
        
                  chain.doFilter(request, response);

                } 
                catch (ValidationException e){
                        var errors = e.getErrors();

                        Map<String, String> params = new LinkedHashMap<>();
                        params.put("email", email);
                        params.put("emailError", errors.get("email"));
                        params.put("passwordError", errors.get("password"));
                        
                        QueryParamBuilder.buildQueryString(params);

                        httpResponse.sendRedirect("/login?" + QueryParamBuilder.buildQueryString(params));
                }
                catch (UserNotFoundException e) {
                    Map<String, String> params = new LinkedHashMap<>();
                    params.put("email", email);
                    httpResponse.sendRedirect("/login?error=true&" + QueryParamBuilder.buildQueryString(params));
                }
                catch (Exception e) {
                    System.out.println("##############################");
                   System.out.println("Exception called at login Filter...");
                    handleException(e, httpRequest, httpResponse);
                }
                
    }

    private void handleException(Exception ex, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.sendRedirect("/error");
    }
    
}
