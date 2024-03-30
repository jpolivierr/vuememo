package com.appvenir.vuememo.web.template;

import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.ui.Model;


public abstract class PageTemplate {

    protected PageAttribute pageAttribute;

    public PageTemplate(String title){
        this.pageAttribute = new PageAttribute(title);
        // setPageAttributes();
    }

    public String renderPage(String pageName, Model model){

        setAttributesToModel(model);

        return pageName;

    }

    protected void setAttributesToModel(Model model){
        model.addAttribute("props", pageAttribute);
        model.addAttribute("metaTags", pageAttribute.getMeta());
        model.addAttribute("linkTags", pageAttribute.getLinks());
        model.addAttribute("ogMetaTags", pageAttribute.getOgMeta());
  }
    
}
