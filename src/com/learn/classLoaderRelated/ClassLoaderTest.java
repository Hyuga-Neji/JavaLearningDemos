package com.learn.classLoaderRelated;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassLoaderTest {

    public static void main(String[] args) {

        //创建自定义classloader对象。假设外部Class是放在F盘下面
        CustomClassLoader diskLoader = new CustomClassLoader("F:\\");
        try {
            //加载class文件
            Class c = diskLoader.loadClass("com.learn.classLoaderRelated.Test");

            if(c != null){
                try {
                    Object obj = c.newInstance();
                    Method method = c.getDeclaredMethod("say");
                    //通过反射调用Test类的say方法
                    method.invoke(obj);
                } catch (InstantiationException | IllegalAccessException
                        | NoSuchMethodException
                        | SecurityException |
                        IllegalArgumentException |
                        InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
