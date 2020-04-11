package com.rong.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.expression.Fields;

import java.lang.reflect.Field;
import java.util.stream.Stream;

/**
 * @Description:
 * @Author: QR
 * @Date: 2020-04-02 21:05
 **/
@Aspect
@Component
public class DemoAspect {

//    @Around(value = "execution(* com.rong.service.impl.BookServiceImpl.saveBook(..))")
////    @Around(value = "execution(* com.rong.entity.Book.setId(..))")
//    public Object checkPrivilege(ProceedingJoinPoint joinPoint) throws Exception{
//        try {
//            Object[] args = joinPoint.getArgs();
//
//            for (int i = 0; i < args.length; i++) {
//                Field[] fileds =  args[i].getClass().getDeclaredFields();
//                for (Field filed : fileds) {
//                    filed.setAccessible(true);
//                    if ("id".equals(filed.getName())) {
//                        filed.set(args[i],"111");
//                    }
//                }
//
//                Object ret = joinPoint.proceed(args);
//                return ret;
//            }
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


    @Before(value = "execution(* com.rong.service.impl.BookServiceImpl.saveBook(..))")
    public void aaa(JoinPoint joinPoint) throws Exception{
        try {
            Object[] args = joinPoint.getArgs();

            for (int i = 0; i < args.length; i++) {
                Field[] fileds =  args[i].getClass().getDeclaredFields();

                for (Field filed : fileds) {
                    filed.setAccessible(true);
                    if ("id".equals(filed.getName())) {

                        filed.set(args[i],"def");
                    }
                }

//                Object ret = joinPoint..proceed(args);
//                return ret;

            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }
}
