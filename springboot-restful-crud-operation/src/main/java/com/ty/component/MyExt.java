package com.ty.component;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * Created by Administrator on 2018/5/8.
 */
@Component
public class MyExt extends DefaultErrorAttributes {
    public Map<String, Object> getErrorAttributes(WebRequest requestAttributes, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(requestAttributes, includeStackTrace);
        Map<String, Object> ext = (Map<String, Object>) requestAttributes.getAttribute("ext", 0);
        map.put("error", ext);
        return map;
    }
}
