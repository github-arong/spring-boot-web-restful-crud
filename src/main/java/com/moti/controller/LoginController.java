package com.moti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {


    /**
     * @RequestMapping(value = "/user/login",method = RequestMethod.POST)
     * 可以替换成
     * @PostMapping(value = "/user/login")
     *
     * 使用@RequestParam注解表明参数时，如果参数为空，那么会报错
     */
    @PostMapping(value = "/user/login")
    public String login(@RequestParam("userName") String userName, @RequestParam("passWord") String passWord,
                        Map<String,Object> map, HttpSession session){
        if (!StringUtils.isEmpty(userName) && "123456".equals(passWord)){
            //登录成功,为了防止表单重复提交,我们使用重定向的方式
            session.setAttribute("loginUser",userName);
            return "redirect:/main.html";
        }else{
            //登录失败
            map.put("message","密码错误");
            return "index";
        }
    }
}
