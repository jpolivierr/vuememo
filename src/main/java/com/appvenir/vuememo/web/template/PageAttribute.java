package com.appvenir.vuememo.web.template;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
public class PageAttribute {

    private String title;
    private List<PageMeta> meta; 
    private List<LinkTag> links;
    private List<PageOgMeta> ogMeta;

    public PageAttribute(String title){
        this.title = title;
        this.meta = new ArrayList<>();
        this.links =new ArrayList<>();
        this.ogMeta = new ArrayList<>();
    }

    public void addMeta(String name, String content){
        this.meta.add(new PageMeta(name, content));
    }

    public void addOgMeta(String property, String content){
        this.ogMeta.add(new PageOgMeta(property, content));
    }

    public void addLink(LinkTag linkTag){
        this.links.add(linkTag);
    }

    @Data
    @AllArgsConstructor
    public class PageMeta {
    private String name;
    private String content;
    }

    @Data
    @AllArgsConstructor
    public class PageOgMeta {
    private String property;
    private String content;
    }

    
}
