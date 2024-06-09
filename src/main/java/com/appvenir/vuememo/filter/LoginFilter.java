package com.appvenir.vuememo.filter;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.appvenir.vuememo.domain.users.UserValidator;
import com.appvenir.vuememo.domain.users.dto.UserLoginDto;
import com.appvenir.vuememo.exception.user.UserNotFoundException;
import com.appvenir.vuememo.exception.validationException.ValidationException;
import com.appvenir.vuememo.helper.paramBuilder.QueryParamBuilder;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = "/login")
public class LoginFilter implements Filter{

    private final UserValidator userValidator = new UserValidator();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

                HttpServletRequest httpRequest = (HttpServletRequest) request;
                HttpServletResponse httpResponse = (HttpServletResponse) response;

                if ("POST".equalsIgnoreCase(httpRequest.getMethod()) && "/login".startsWith(httpRequest.getServletPath())){

                        String email = httpRequest.getParameter("email");
                        String password = httpRequest.getParameter("password");
                        try {

                            UserLoginDto userLogin = new UserLoginDto(email, password);
                            userValidator.validate(userLogin);
                            chain.doFilter(request, response);

                        } 
                        catch (ValidationException e){
                                var errors = e.getErrors();
                                System.out.println(errors);
                                System.out.println("processing453453453...");

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
                            handleException(e, httpRequest, httpResponse);
                        }
                }else{
                    chain.doFilter(request, response);
                }

                
    }

    private void handleException(Exception ex, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.sendRedirect("/error");
    }
    
}
