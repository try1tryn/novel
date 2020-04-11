package com.rong.demo01;

import com.rong.entity.Book;
import com.sun.corba.se.spi.orbutil.threadpool.ThreadPool;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: QR
 * @Date: 2020-01-11 20:09
 **/
public class ThreadTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Book> list = new ArrayList<>();
        list.add(new Book("aaaaaa","a","在那里","图图","1","ss","s",1));
        list.add(new Book("bbbbbb","b","在那里","图图","1","ss","s",1));
        list.add(new Book("cccccc","c","在那里","图图","1","ss","s",1));

        /**
         * 将list转为map
         */
        Map<String, Book> collect = Optional.ofNullable(list).orElse(Collections.emptyList()).stream().collect(Collectors.toMap(Book::getId, book -> book));
        System.out.println();

        ExecutorService service = Executors.newCachedThreadPool();
        Lock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();

    }
}
