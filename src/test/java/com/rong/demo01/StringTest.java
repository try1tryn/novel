package com.rong.demo01;

import net.minidev.json.JSONUtil;
import org.junit.platform.commons.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description: 字符串练习
 * @Author: QR
 * @Date: 2020-03-05 15:29
 **/
public class StringTest {
    /**
     * 练习一：“aabbccdaa” -> “a2b2c2d1a2”
     * 1 寻找连续相同的字符串
     * 2 统计相同字符串的个数
     *
     * @param str
     * @return String
     */
    public static String fun01(String str) {
        String[] array = str.split("", str.length());
        StringBuilder result = new StringBuilder();
        // 设置比较的字符对象
        String compareStr = array[0];
        // 记录连续重复的字符数
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            String currentStr = array[i];
            // 相邻的两个字符不一致
            if (!compareStr.equals(currentStr)) {
                result = result.append(compareStr).append(count);
                count = 1;
                compareStr = currentStr;
            } else {
                // 记录连续重复的字符数
                count++;
            }
            // 追加输出最后一个比较的字符和个数
            if (i == array.length - 1) {
                result = result.append(currentStr).append(count);
            }
        }
        return result.toString();
    }

    /**
     * 练习二：“aabbccdaa” -> “abcda” 去除连续相同的字符
     * 1 寻找连续相同的字符串
     * 2 连续相同的字符只保留一个
     *
     * @param str
     * @return String
     */
    public static String fun02(String str) {
        String[] array = str.split("", str.length());


        // 设置比较的字符对象
        String compareStr = array[0];

        StringBuilder result = new StringBuilder(compareStr);

        for (int i = 1; i < array.length; i++) {
            // 只保存连续相同的第一个字符
            if (!compareStr.equals(array[i])) {
                result.append(array[i]);
                // 重置比较的字符对象
                compareStr = array[i];
            }
        }
        return result.toString();
    }

    /**
     * 练习三：统计字符串中数字个数
     *
     * @param str
     * @return int
     */
    public static int fun03(String str) {
        int count = 0;
        char[] array = str.toCharArray();
        for (char s : array) {
            // 若字符是数字 则累加
            if (Character.isDigit(s)) {
                count++;
            }
        }
        return count;
    }

    /**
     * 练习四：字符串逆置
     *
     * @return
     */
    public static String fun04(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    /**
     * 集合
     * @param args
     */

    public static void main(String[] args) {

        Map map1 = new HashMap();
        Stream.of("1","2","3").map(k->Integer.valueOf(k)*2).forEach(System.out::println);
        List<String> stringStream = (List<String>)Stream.of("1s", "2a", "3s", "张11", "哈哈哈").collect(Collectors.toList());
        System.out.println();
    }
}
