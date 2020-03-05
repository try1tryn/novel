package com.rong.common;

import com.rong.annotation.PassToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @description: 全局拦截器
 * @author: QR
 * @create: 2020-01-05 15:38
 **/
@Component
public class GlobalInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate redisTemplate;
    private final Logger logger = LoggerFactory.getLogger(GlobalInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("-----------进入拦截器-----------");
        if (!(handler instanceof HandlerMethod)) {
            logger.info("没有映射到方法，则无需检查直接通过");
        }


        //判断方法是否需要校验token
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        PassToken loginRequired = method.getAnnotation(PassToken.class);

        //有PassToken注解，不需要认证
        if (loginRequired != null) {
            return true;
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (Constants.AUTHORIZATION.equals(cookie.getName())) {
                    if (redisTemplate.boundValueOps(Constants.AUTHORIZATION).get().equals(cookie.getValue())) {
                        logger.info("token一致，登陆成功");
                        return true;
                    }
                    break;
                }
            }
        }
        logger.info("token不一致");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
