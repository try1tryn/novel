package com.rong.demo01;

import com.sun.corba.se.spi.orbutil.threadpool.ThreadPool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: QR
 * @Date: 2020-01-11 20:09
 **/
public class ThreadTest {

    public static void main(String[] args) {
            ExecutorService  fixedThreadPool =Executors. newFixedThreadPool(3);
            for (int i =1; i<=5;i++){
                final int index=i ;
                fixedThreadPool.execute(new Runnable(){
                    @Override
                    public void run() {
                        try {
                            System.out.println("第" +index + "个线程" +Thread.currentThread().getName());
                            Thread.sleep(1000);
                        }  catch(InterruptedException  e ) {
                            e .printStackTrace();
                        }
                    }

                });
            }
    }
}
