package com.appvenir.vuememo.web.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.appvenir.vuememo.web.PageTemplate.DefaultAttribute;
import com.appvenir.vuememo.web.PageTemplate.PageTemplate;

@Controller
@RequestMapping("/dashboard")
public class Dashboard extends PageTemplate{

        public Dashboard(){
        super("Vuememo | Dashboard");
        pageAttribute.addLink(DefaultAttribute.globalLinkTagAttribute());
    }
    
    @GetMapping
    public String index(Model model){
        return renderPage("dashboard", model);
    }
}
 