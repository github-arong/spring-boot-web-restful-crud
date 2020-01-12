package com.moti.controller;

import com.moti.exception.UserNotExistException;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义异常处理器，处理自定义异常
 */
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(UserNotExistException.class)
    public String handlerException(Exception e, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        //传入我们自己的错误状态码  4xx 5xx，否则就不会进入定制错误页面的解析流程
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("info","MyExceptionHandler携带的错误信息");
        map.put("text"," user not exist！");
        map.put("message",e.getMessage());
        //将当前map放到request域中，最后由MyErrorAttributes汇总合并
        request.setAttribute("extendMap",map);
        //转发到/error
        return "forward:/error";
    }
}
