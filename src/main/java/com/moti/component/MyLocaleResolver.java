package com.moti.component;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 自定义区域解析器,进行国际化操作
 */
public class MyLocaleResolver implements LocaleResolver {

    /**
     * 可以在链接上携带区域信息,以此来判断使用哪种语言进行国际化操作
     * @param request
     * @return
     */
    @Override
    public Locale resolveLocale(HttpServletRequest request) {

        //获得请求参数中的区域信息:http://localhost:8080/index.html?l=en_US
        String l = request.getParameter("l");
        //获得默认的区域信息
        Locale locale = Locale.getDefault();
        //如果参数中没有携带区域信息,那么使用默认的区域信息(HTTP请求头中的信息)
        if(!StringUtils.isEmpty(l)){
            //分割语言码和地区码
            String[] s = l.split("_");
            //创建一个新的区域对象并返回
            locale = new Locale(s[0],s[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
