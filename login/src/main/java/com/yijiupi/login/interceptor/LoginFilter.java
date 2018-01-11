package com.yijiupi.login.interceptor;

import com.yijiupi.login.pojo.User;
import com.yijiupi.login.util.CookieUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**拦截器的对未登录的用户的进行控制
 * @author fengqigui
 * @Date 2017/11/9 9:32
 */
public class LoginFilter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        User user = (User) request.getSession().getAttribute("user");

        if (null == user) {

            user = (User) CookieUtil.getCookieToObject(request, User.class);

        }

        String path=request.getRequestURL().toString();

        if (null == user && path.contains("/user/home.do") ) {

		    response.sendRedirect(request.getContextPath()+"/user/index.do");

            return false;
        }

        return true;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
