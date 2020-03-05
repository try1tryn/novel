package com.rong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.rong.service", "com.rong.mapper", "com.rong.controller", "com.rong.entity", "com.rong.common", "com.rong.annotation"})
@MapperScan(value = "com.rong.mapper")
@ServletComponentScan
public class Demo01Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo01Application.class, args);
    }
}
