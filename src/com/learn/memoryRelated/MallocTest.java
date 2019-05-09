package com.learn.memoryRelated;

/**
 * 内存分配测试
 */
public class MallocTest {

    public static void main(String[] args) {
        // 创建内存分配测试类对象
        MallocTest mt = new MallocTest();
        // 创建白葡萄酒类，id=1，价格=199
        Wine whiteWine = new Wine(1,199);
        // 创建红葡萄酒类，id=2，价格=299
        Wine redWine = new Wine(2,299);
        // 定义基础变量
        int testNum = 123;

        /* 下面是方法调用 */
        mt.changeBasicVar(testNum);
        mt.changeWineProp(whiteWine);
        mt.changeWine(redWine);
    }

    /**
     * 修改对象属性
     * 疑问：对象属性修改成功吗？
     * @param wine 葡萄酒类
     */
    public void changeWineProp(Wine wine){
        wine.setId(20);
        wine.setPrice(500);
    }

    /**
     * 修改对象引用
     * 疑问：对象引用能修改成功吗？
     * @param wine 葡萄酒类
     */
    public void changeWine(Wine wine){
        wine = new Wine(15,800);
    }

    /**
     * 修改基础类型变量值
     * 疑问：能修改成功吗？
     * @param num 基础类型变量
     */
    public void changeBasicVar(int num){
        num = 1250;
    }
}
