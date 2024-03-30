package com.appvenir.vuememo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.appvenir.vuememo.domain.users.User;
import com.appvenir.vuememo.web.template.LinkTag;
import com.appvenir.vuememo.web.template.PageTemplate;

@Controller
@RequestMapping("/signup")
public class SignUp extends PageTemplate {

    public SignUp(){
        super("Vuememo | Sign-up");
        pageAttribute.addLink(new LinkTag("preconnect", "https://fonts.googleapis.com"));
        pageAttribute.addLink(new LinkTag("preconnect", "https://fonts.gstatic.com"));
        pageAttribute.addLink(new LinkTag("stylesheet", "https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap"));
        pageAttribute.addLink(new LinkTag("stylesheet", "/style/main.css"));
    }

    @GetMapping
    public String signup(Model model){
        model.addAttribute("user", new User());
        return renderPage("signup", model);
    }
    
}
