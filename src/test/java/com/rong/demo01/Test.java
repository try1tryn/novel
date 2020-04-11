package com.rong.demo01;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: QR
 * @Date: 2020-03-05 22:03
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    private static int DD = 8;

    /**
     *  redis 测试
     */
    @org.junit.Test
    public void fun01(){
        redisTemplate.opsForValue().set("aaa","1111111");
        System.out.println(redisTemplate.opsForValue().get("aaa"));
    }

    @org.junit.Test
    public void  fun02(){

        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        threadPool.submit(new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }));

        threadPool.execute(new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }));

        Thread[] threads = new Thread[100];

        int a = 2;

        for (int i = 0; i < threads.length; i++) {
            threads[i]= new Thread(new Runnable() {
                int b = 3;
                @Override
                public void run() {
                    DD--;
                    b=a+1;
                }
            });
            threadPool.submit(threads[i]);
        }


    }
}

