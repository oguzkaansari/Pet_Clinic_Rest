package com.petclinicrest.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class Result {

    public Map<String, Object> resultMap = new LinkedHashMap<>();

    public Result(boolean status, int total_pages, long total_elements, String message, Object result) {
        resultMap.put("status", status);
        if(total_pages != 0){ resultMap.put("total_pages", total_pages);}
        if(total_elements != 0){ resultMap.put("total_elements", total_elements);}
        if(!message.equals("")){ resultMap.put("message", message);}
        resultMap.put("result", result);
    }
}
