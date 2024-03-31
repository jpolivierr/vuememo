package com.appvenir.vuememo.web.PageTemplate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LinkTag {
    private String rel;
    private String href;
    private String type;
    private String sizes;

    public LinkTag(String rel, String href){
        this.rel = rel;
        this.href = href;
    }

}
