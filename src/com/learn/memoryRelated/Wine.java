package com.learn.memoryRelated;

/**
 * 葡萄酒类
 */
public class Wine {

    private int id; // 编号
    private int price; // 价格

    public Wine() {}

    public Wine(int id, int price) {
        this.id = id;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
