package com.moti.controller;

import com.moti.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {


    /**
     * 用户登录,用户名随意,密码为123456
     *
     * @RequestMapping(value = "/user/login",method = RequestMethod.POST)  -> @PostMapping(value = "/user/login")
     * 使用@RequestParam注解表明参数时，如果参数为空，那么会报错
     */
    @PostMapping(value = "/user/login")
    public String login(@RequestParam("userName") String userName, @RequestParam("passWord") String passWord,
                        Map<String,Object> map, HttpSession session){
        if (!"管理员".equals(userName)){
            throw new UserNotExistException();
        }else if ("123456".equals(passWord)){
            //登录成功,为了防止表单重复提交,我们使用重定向的方式
            session.setAttribute("loginUser",userName);
            return "redirect:/main.html";
        }else{
            //登录失败
            map.put("message","密码错误");
            return "index";
        }
    }

    /**
     * 退出登录
     * @param session 清空session
     * @return 转发到登录主页
     */
    @GetMapping("/user/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
