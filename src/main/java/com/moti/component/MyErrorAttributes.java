package com.moti.component;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class MyErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        map.put("info","MyErrorAttributes携带的错误信息");
        map.put("hello","world");
        //获取异常处理器携带的数据
        Map<String,Object> extendMap = (Map<String, Object>) webRequest.getAttribute("extendMap", 0);
        map.put("extendMap",extendMap);
        return map;
    }
}
