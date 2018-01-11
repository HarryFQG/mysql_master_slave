package com.yijiupi.login.controller;


import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.yijiupi.login.util.CookieUtil;
import com.yijiupi.login.pojo.User;
import com.yijiupi.login.service.UserService;
import com.yijiupi.login.util.MessageStates;
import com.yijiupi.login.util.CheckedCode;
import com.yijiupi.login.util.UploadImageUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 接受用户的请求
 * @author fengqigui
 * @Date 2017/11/6 15:04
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @Autowired
    private Producer captchaProducer;

    /**
     * 生成注册页面(register.html)的
     * @param modelAndView
     * @return
     */
    @RequestMapping("registerHtml")
    public ModelAndView registerHtml(ModelAndView modelAndView){

        modelAndView.setViewName("register");

        String createHtmlTime = new Date().toString();

        modelAndView.addObject("createHtmlTime", createHtmlTime);

        LOGGER.info("register.html生成时间：" + createHtmlTime);

        return modelAndView;
    }


    /**
     * 生成home.html页面
     * @param pageCurrent 当前页
     * @param modelAndView
     * @return
     */
    @RequestMapping("home/{pageCurrent}")
    public ModelAndView home(@PathVariable int pageCurrent, ModelAndView modelAndView){

        List<User> users = userService.listUser(5, pageCurrent);

        modelAndView.addObject("users", users);

        modelAndView.addObject("pageCurrent", pageCurrent);

        modelAndView.setViewName("home");

        return modelAndView;
    }

    /**
     * 生成index.html页面
     * @param modelAndView
     * @return
     */
    @RequestMapping("index")
    public ModelAndView indexHtml(ModelAndView modelAndView){

        modelAndView.setViewName("index");

        String createHtmlTime = new Date().toString();

        modelAndView.addObject("createHtmlTime", createHtmlTime);

        return modelAndView;
    }


    /**
     * 用户登陆：用户名和密码匹配则进入首页，否则停留在登录页面
     * @param userName 用户姓名
     * @param password 用户密码
     * @param request request请求
     * @return 逻辑视图
     */
    @RequestMapping("login")
    public String userLogin(String userName, String password,String authCode, HttpServletRequest request, HttpServletResponse response){

        if (null == userName || null == password || "authCode=".equals(authCode)) {

            // 验证码为空
            if ("authCode=".equals(authCode)) {

                request.setAttribute(MessageStates.MESSAGE_STATE, MessageStates.LOGIN_FAILURED_VALIDATE_MESSAGE);

                LOGGER.info(MessageStates.LOGIN_FAILURED_VALIDATE_MESSAGE);

                return "index";
            }

            request.setAttribute(MessageStates.MESSAGE_STATE, MessageStates.LOGIN_FAILURED_NULL_MESSAGE);

            LOGGER.info(MessageStates.LOGIN_FAILURED_NULL_MESSAGE);

            return "index";

        }

        String code = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);

        // 如果验证码不匹配则停留在登录页面
        if (!authCode.equals(code)) {

            request.setAttribute(MessageStates.MESSAGE_STATE, MessageStates.LOGIN_FAILURED_VALIDATE_MESSAGE);

            LOGGER.info(MessageStates.LOGIN_FAILURED_VALIDATE_MESSAGE);

            return "index";
        }

        User user = userService.getUserByNameAndPassword(userName, password);

        // 用户和密码不匹配或者不存在
        if (null == user) {

            request.setAttribute(MessageStates.MESSAGE_STATE, MessageStates.LOGIN_FAILURED_MATCHING_MESSAGE);

            LOGGER.info(MessageStates.LOGIN_FAILURED_MATCHING_MESSAGE);

            return "index";

        }

        saveUserToCookie(response, user);

        request.getSession().setAttribute("user", user);

        request.setAttribute(MessageStates.MESSAGE_STATE, MessageStates.LOGIN_SUCCESS_MESSAGE);

        LOGGER.info(MessageStates.LOGIN_SUCCESS_MESSAGE);

        return "redirect:home.do";
    }

    /**
     * 注册新用户
     * @param user  :待添加到数据库的用户
     * @param request   :HttpServletRequest
     * @return  逻辑视图
     */
    @RequestMapping("register")
    public String registerUser(User user, HttpServletRequest request){

        if (null == user.getUserName() || null == user.getUserPassword()) {

            request.setAttribute(MessageStates.MESSAGE_STATE, MessageStates.REGISTER_FAILURED_NULL_MESSAGE);

            return "register";
        }

        int countUserName = userService.countUserName(user.getUserName());

        if (0 < countUserName) {

            request.setAttribute(MessageStates.MESSAGE_STATE, MessageStates.REGISTER_FAILURED_REPETITION_MESSAGE);

            return "register";
        }

        int insertUser = userService.insertUser(user);

        if (0 < insertUser) {

            request.setAttribute(MessageStates.MESSAGE_STATE, MessageStates.REGISTER_SUCCESS_MESSAGE);

            LOGGER.info("注册成功");

            return "forward:/rest/user/home/1";
        }

        request.setAttribute(MessageStates.MESSAGE_STATE, MessageStates.REGISTER_FAILURED_MESSAGE);

        LOGGER.info("注册失败");

        return "register";

    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @RequestMapping("deleteUserById/{userId}")
    public String deleteUserById(@PathVariable Integer userId){

        if (null == userId) {

            LOGGER.error("删除的用户ID不为空");

            return "redirect:/user/home.do";

        }

        userService.removeUser(userId);

        return "redirect:/rest/user/home/1";
    }


    /**
     * 用于生成验证码
     * @param request   ：   HttpServletRequest
     * @param response  ：   HttpServletResponse
     */
    @RequestMapping("validateCode")
    public void validateCode(HttpServletRequest request, HttpServletResponse response){

        CheckedCode.putValidateCode(captchaProducer, request, response);

    }

    /**
     * 使用AJAX做验证码校检，如果验证码错误，则禁止提交表单。
     * @param authCode  ： 传入的验证码
     * @param request   ： HttpServletRequest
     * @return 返回是否提交的标志量
     */
    @RequestMapping("authCode")
    public @ResponseBody String authCode(@RequestBody  String authCode, HttpServletRequest request){

        if (null == authCode || authCode.equalsIgnoreCase("authCode=")) {

            LOGGER.info("验证码输入为空");

            return "false";

        }

        String codeWait = authCode.replace("authCode=","");

        String code = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);

        if (codeWait.equalsIgnoreCase(code)) {

            LOGGER.info("验证码校检成功,返回true");

            return "true";

        }

        LOGGER.info("验证码校检失败，返回false");

        return "false";
    }

    /**
     * 文件上传
     * @param request ：HttpServletRequest
     * @param fileName : 待上传的文件
     * @param out
     */
    @RequestMapping("uploadPic")
    public void uploadPicture(HttpServletRequest request,String fileName,PrintWriter out){

        String result = UploadImageUtil.uploadSpringMvc(request, fileName, "/upload/");

        LOGGER.info("文件相对路径:"+result);

        out.print(result);
    }

    /**
     * 将User实例的对象数据放进map，然后将其存进cookie
     * @param response
     * @param user
     * @return
     */
    private boolean saveUserToCookie(HttpServletResponse response, User user){

        if (null != user) {

            Map<String, String> userMap = new HashMap<>();

            if(null != user.getUserId()) {

                userMap.put("userId", user.getUserId().toString());

            }

            if(null != user.getUserName()) {
                userMap.put("userName", user.getUserName());
            }

            if(null != user.getUserPassword()) {

                userMap.put("userPassword", user.getUserPassword());

            }

            if(null != user.getUserSex()) {

                userMap.put("userSex", user.getUserSex());

            }

            if(null != user.getUserSalary()) {

                userMap.put("userSalary", user.getUserSalary().toString());

            }

            if(null != user.getUserFriend()) {

                userMap.put("userFriend", user.getUserFriend().toString());

            }

            if (null != user.getUserPhoto()) {

                userMap.put("userPhoto", user.getUserPhoto());

            }

            boolean cookieFlag = CookieUtil.saveCookie(response, userMap);

            return cookieFlag;

        }

        LOGGER.info("cookie存储异常");

        return false;

    }



}
