package com.appvenir.vuememo.web.pages;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.appvenir.vuememo.domain.users.User;
import com.appvenir.vuememo.domain.users.UserValidator;
import com.appvenir.vuememo.domain.users.UserRegistration;
import com.appvenir.vuememo.domain.users.UserService;
import com.appvenir.vuememo.exception.user.EmailExistsException;
import com.appvenir.vuememo.exception.validationException.ValidationException;
import com.appvenir.vuememo.web.PageTemplate.DefaultAttribute;
import com.appvenir.vuememo.web.PageTemplate.PageTemplate;

@Controller
@RequestMapping("/signup")
public class SignUp extends PageTemplate {

    private final UserService userService;
    private final UserValidator userValidator = new UserValidator();

    public SignUp(UserService userService){
        super("Vuememo | Signup");
        pageAttribute.addLink(DefaultAttribute.globalLinkTagAttribute());
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("formError", new HashMap<>());
        return renderPage("signup", model);
    }

    @PostMapping
    public String signup(@ModelAttribute("UserRegistration") UserRegistration userRegistration, Model model){
        User user = new User(userRegistration);
        model.addAttribute("user", user);
        try {
            userValidator.validate(userRegistration);
            userService.saveUser(new User(userRegistration));
            return "redirect:/login";
        } 
        catch (EmailExistsException e) {
            model.addAttribute("formError", Map.of("email", "Email already exists"));
        }
        catch (ValidationException e) {
            model.addAttribute("formError", e.getErrors());
        }

        return renderPage("signup", model);
    }
    
}
