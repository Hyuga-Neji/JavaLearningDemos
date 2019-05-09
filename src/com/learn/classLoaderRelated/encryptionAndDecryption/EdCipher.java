package com.learn.classLoaderRelated.encryptionAndDecryption;

import java.io.*;

/**
 * 加解密类
 */
public class EdCipher {

    // 装加密后的Class文件的目录名
    private String encryptFolderName = "encrypt";

    /**
     * 加密方法
     * @param name 需要加密的文件名
     */
    public void encryptClass(String name) {
        // 获取加密前文件的绝对路径
        String path = getAbsolutePathBeforeEncryption(name);

        // 为待加密的class文件创建File对象
        File classFile = new File(path);
        if (!classFile.exists()) {
            // TODO 如果文件不存在，做相应的处理。一般情况下都是抛出异常；
        } else {
            // 在 待加密的文件也就是classFile的同级目录下创建一个文件夹，然后把加密后的文件放进去
            File encryptFolder = new File(classFile.getParent() + File.separator + encryptFolderName);
            if (!encryptFolder.exists()) {
                encryptFolder.mkdirs();
            }
        }
        // 加密后的文件需要单独放，就放在encryptFolderName文件夹下，encryptedFile 为加密后文件的全路径文件名
        String encryptedFile = classFile.getParent() + File.separator + encryptFolderName + File.separator + classFile.getName();
        try (
                FileInputStream fileInputStream = new FileInputStream(classFile);
                BufferedInputStream bis = new BufferedInputStream(fileInputStream);
                FileOutputStream fileOutputStream = new FileOutputStream(encryptedFile);
                BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream)
            ) {

            // 加密
            int data;
            while ((data = bis.read()) != -1) {
                bos.write(data ^ 0xFF);
            }
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 现在将原来未加密的文件删除
        classFile.delete();

        // 先获取加密前文件绝对路径名，然后修改后缀，再创建File对象
        File oldFile = new File(path + "en");
        // 删除未加密前的文件
        if (oldFile.exists()) {
            oldFile.delete();
        }
        // 根据加密后的文件创建File对象
        File newEncryptedFile = new File(encryptedFile);
        // 将加密后的对象重命名，这时加密后的文件就把加密前的文件替换掉了，这就是为什么刚开始加密后的文件需要单独放的原因
        newEncryptedFile.renameTo(oldFile);
        // 删除之前的加密文件夹下面的加密文件
        newEncryptedFile.getParentFile().delete();
    }

    /**
     * 解密方法
     * @param name 需要解密的文件名
     */
    protected byte[] decryptClass(String name) {
        String path;
        if (!name.contains(".class")) {
            path = getAbsolutePathAfterEncryption(name);
        } else {
            path = name;
        }
        // 为待加密的文件创建File对象
        File decryptClassFile = new File(path);
        if (!decryptClassFile.exists()) {
            System.out.println("decryptClass() File:" + path + " not found!");
            return null;
        }
        byte[] result = null;
        BufferedInputStream bis = null;
        ByteArrayOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(decryptClassFile));
            bos = new ByteArrayOutputStream();
            // 解密
            int data;
            while ((data = bis.read()) != -1) {
                bos.write(data ^ 0xFF);
            }
            bos.flush();
            result = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 获取加密前文件的绝对路径
     * @param name 待加密的文件的全限定名
     * @return 文件的绝对路径
     * 注意：根据待加密的文件的位置不同（外部class，项目内部class），改写改方法。
     * 最终目的就是获取到待加密的文件的绝对路径
     */
    private String getAbsolutePathBeforeEncryption(String name) {
        /*String className = name.substring(name.lastIndexOf(".") + 1) + ".class";
        String path = EdCipher.class.getResource(className).toString();
        path = path.substring(path.indexOf("file:/") + "file:/".length());
        if (System.getProperty("os.name").toUpperCase().contains("LINUX")) {
            path = File.separator + path;
        }*/
        return new File(name).getAbsolutePath();
    }

    /**
     * 获取加密后文件的绝对路径
     * @param name 待解密的文件的全限定名
     * @return 文件的绝对路径
     * 注意：根据加密后文件的位置不同（外部class，项目内部class），改写改方法。
     * 最终目的就是获取到加密后文件的绝对路径
     */
    private String getAbsolutePathAfterEncryption(String name) {
        /*String className = name.substring(name.lastIndexOf(".") + 1) + ".classen";
        String path = EdCipher.class.getResource(className).toString();
        path = path.substring(path.indexOf("file:/") + "file:/".length());*/
        return new File(name).getAbsolutePath();
    }

    // 测试
    public static void main(String[] args) {
        EdCipher edCipher = new EdCipher();
        edCipher.encryptClass("F:\\Test.class");
    }



}
