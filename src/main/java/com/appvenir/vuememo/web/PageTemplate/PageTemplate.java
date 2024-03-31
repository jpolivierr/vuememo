package com.appvenir.vuememo.web.PageTemplate;

import org.springframework.ui.Model;


public abstract class PageTemplate {

    protected PageAttribute pageAttribute;

    public PageTemplate(String title){
        this.pageAttribute = new PageAttribute(title);
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
