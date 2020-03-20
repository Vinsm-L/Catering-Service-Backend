package com.example.pojo;

/**
 * 桌位实体类
 */
public class Tables {
    /**
     * 桌号
     * 创建时需指定，非自增，不可重复
     */
    private int number;

    // 餐位数
    private int capacity;

    /**
     * 桌位状态
     * 0-空闲
     * 1-已落座，无订单
     * 2.3.4.5-对应订单状态
     */
    private int status;

    /**
     * 当前订单id
     * 若无订单则字段为-1
     */
    private Integer orderId;

    /**
     * 默认构造函数
     * mybatis创建实体使用
     */
    public Tables(){}

    /**
     * 桌位构造函数， 默认无订单
     * @param number
     * @param capacity
     * @param status
     */
    public Tables(int number, int capacity, int status) {
        this.number = number;
        this.capacity = capacity;
        this.status = status;
        this.orderId = null;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrdersId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "Tables{" +
                "number=" + number +
                ", capacity=" + capacity +
                ", status=" + status +
                ", orderId=" + orderId +
                '}';
    }
}
