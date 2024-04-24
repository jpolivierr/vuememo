package com.appvenir.vuememo.web.PageTemplate;

import java.util.List;

public class DefaultAttribute {

    public static List<LinkTag> globalLinkTagAttribute(){

        return List.of(
            new LinkTag("preconnect", "https://fonts.googleapis.com"),
            new LinkTag("preconnect", "https://fonts.gstatic.com"),
            new LinkTag("stylesheet", "https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap"),
            new LinkTag("stylesheet", "https://use.fontawesome.com/releases/v6.0.0/css/all.css"),
            new LinkTag("stylesheet", "/assets/style/main.css")
        );
    }
    
}
