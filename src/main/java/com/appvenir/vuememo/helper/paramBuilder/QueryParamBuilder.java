package com.appvenir.vuememo.helper.paramBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class QueryParamBuilder {

        public static String buildQueryString(Map<String, String> parameters) throws UnsupportedEncodingException {
        StringBuilder queryString = new StringBuilder();
        for (Map.Entry<String, String> param : parameters.entrySet()) {
            if (param.getValue() == null || param.getValue().isEmpty()) {
                continue;
            }
            if (queryString.length() != 0) {
                queryString.append("&");
            }
            queryString.append(URLEncoder.encode(param.getKey(), "UTF-8"))
                       .append("=")
                       .append(URLEncoder.encode(param.getValue(), "UTF-8"));
        }
        return queryString.toString();
    }
    
}
