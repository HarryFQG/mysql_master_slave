package com.yijiupi.login.util;

/**
 * 用于登陆和注册页面消息的静态类
 * @author fengqigui
 * @Date 2017/11/7 8:53
 */
public class MessageStates {

    public static final String MESSAGE_STATE = "message";

    public static final String LOGIN_FAILURED_VALIDATE_MESSAGE = "验证码错误或者不为空";

    public static final String LOGIN_FAILURED_NULL_MESSAGE = "用户名或者密码不为空";

    public static final String LOGIN_FAILURED_MATCHING_MESSAGE = "用户名和密码不匹配或用户不存在";

    public static final String LOGIN_SUCCESS_MESSAGE = "欢迎登陆本系统";

    public static final String REGISTER_FAILURED_NULL_MESSAGE = "用户或密码不为空";

    public static final String REGISTER_FAILURED_REPETITION_MESSAGE = "用户名重复";

    public static final String REGISTER_FAILURED_MESSAGE = "注册失败";

    public static final String REGISTER_SUCCESS_MESSAGE = "恭喜注册成功";

}
