package com.appvenir.vuememo.web.pages;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.appvenir.vuememo.domain.users.User;
import com.appvenir.vuememo.domain.users.UserValidator;
import com.appvenir.vuememo.exception.validationException.ValidationException;
import com.appvenir.vuememo.web.PageTemplate.DefaultAttribute;
import com.appvenir.vuememo.web.PageTemplate.PageTemplate;

@Controller
@RequestMapping("/signup")
public class SignUp extends PageTemplate {

    public SignUp(){
        super("Vuememo | Signup");
        pageAttribute.addLink(DefaultAttribute.globalLinkTagAttribute());
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("formError", new HashMap<>());
        return renderPage("signup", model);
    }

    @PostMapping
    public String signup(@ModelAttribute("user") User user, Model model){
        UserValidator userValidator = new UserValidator(user);
        try {
            userValidator.validate(); 

        } catch (ValidationException e) {
            // model.addAttribute("errors", e.getErrors());
            model.addAttribute("user", user);
            model.addAttribute("formError", e.getErrors());
            return renderPage("signup", model);
        }

        return "redirect:/dashboard";
    }
    
}
