package com.learn.memoryRelated;

/**
 * 字符串对象内存分配情况测试类
 */
public class StringMallocTest {
    public static void main(String[] args) {
        String str = "abc";
        String test = "abc";
        String strObj = new String("abc");

        System.out.println( str == test);
        System.out.println( test == strObj );

    }
}
