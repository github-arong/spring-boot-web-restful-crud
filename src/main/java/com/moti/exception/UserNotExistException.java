package com.moti.exception;

/**
 * 自定义用户不存在异常
 */

public class UserNotExistException extends RuntimeException {
    public UserNotExistException() {
        super("用户不存在，请使用‘管理员’登录！");
    }

}
