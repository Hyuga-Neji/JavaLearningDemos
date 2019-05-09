package com.learn.classLoaderRelated.encryptionAndDecryption;

import java.lang.reflect.Method;

import static java.lang.Thread.*;

/**
 * 自定义ClassLoader，动态解密类
 */
public class EdClassLoader extends ClassLoader{


    // 待动态加载解密的Class的全限定名（包名加类名）
    private String fullyName;

    public EdClassLoader(){

    }
    public EdClassLoader(String name){
        this.fullyName = name;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        EdCipher edCipher = new EdCipher();
        byte[] data = edCipher.decryptClass(name);
        int length = data == null ? 0 : data.length;
        return defineClass(fullyName, data, 0, length);
    }

    //在动态加载class文件后，需要通过反射才能调用其中方法
    public static void main(String[] args) throws ClassNotFoundException {
        EdClassLoader edClassLoader = new EdClassLoader("com.learn.classLoaderRelated.Test");
        Object result = null;
        try {
            Class myClass = edClassLoader.findClass("F:\\Test.classen");
            // method1 就是方法名
            Method method = myClass.getDeclaredMethod("say");
            Object obj = myClass.newInstance();
            // result 就是方法返回值
            result = method.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
