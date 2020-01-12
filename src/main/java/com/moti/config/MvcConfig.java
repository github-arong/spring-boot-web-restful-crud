package com.moti.config;

import com.moti.component.LoginHandlerInterceptor;
import com.moti.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 配置视图控制器:
     * 在项目开发过程中，经常会涉及页面跳转问题，而且这个页面跳转没有任何业务逻辑过程，只是单纯的路由过程 ( 点击一个按钮跳转到一个页面 ) 。
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //浏览器发送 /main.html 请求来到 dashboard
        registry.addViewController("/main.html").setViewName("dashboard");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/").setViewName("index");
    }

    /**
     * 注册登录拦截器
     * addPathPatterns() -> 拦截的请求
     * excludePathPatterns -> 不拦截的请求
     * 两个方法的参数都是可变字符串参数
     * SpringBoot已经做好了对静态资源的映射
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/","/index.html","/user/login");
    }

    /**
     * 将自己的国际化解析器组件添加到容器
     * @return
     */
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}
