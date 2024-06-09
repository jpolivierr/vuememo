package com.appvenir.vuememo.web.pages;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.appvenir.vuememo.domain.users.model.User;
import com.appvenir.vuememo.domain.users.service.UserService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
public class SignUpTest {

    private MockMvc mockMvc;
    @Mock
    private UserService userService;

    private SignUp signUp;

    @BeforeEach
    public void setup(){
        signUp = new SignUp(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(signUp).build();    
    }

    @Test
    public void test_form_submission_is_successful() throws Exception {

        User user = new User();
        user.setEmail("jp@gmail.com");
        user.setName("Frederic Olivier");
        user.setPassword("calkjslkdjf");

        when(userService.saveUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/signup")
        .param("name", user.getName())
        .param("password", user.getPassword())
        .param("email", user.getEmail()))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/login"));

    }


    
}
