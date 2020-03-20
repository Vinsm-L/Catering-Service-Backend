package com.example.pojo;

import java.sql.Date;
import java.util.ArrayList;

/**
 * 订单实体类
 */
public class Order {

    /**
     * 订单id
     * 数据库自增，无需设置
     */
    private int id;

    /**
     * 订单创建时间
     * sql.Date数据类型，print时需要调整format
     */
    private Date addDate;

    /**
     * 订单状态
     * 2-已提交，等待确认
     * 3-已确认
     * 4-已完成
     * 5-已拒绝
     */
    private int status;

    // 商品id列表
    private ArrayList<Integer> goodsId;
    // 商品数量，与商品id下标对应
    private ArrayList<Integer> goodsCount;
    // 订单总价
    private float price;
    // 订单所在桌号
    private int tablesNumber;

    /**
     * 默认构造函数
     * mybatis创建实体使用
     */
    public Order(){}



    /**
     * 订单构造函数
     * @param addDate
     * @param goodsId
     * @param price
     * @param status
     * @param tablesNumber
     */
    public Order(Date addDate, ArrayList<Integer> goodsId, ArrayList<Integer> goodsCount, float price, int status, int tablesNumber) {
        this.addDate = addDate;
        this.goodsId = goodsId;
        this.goodsCount = goodsCount;
        this.price = price;
        this.status = status;
        this.tablesNumber = tablesNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public ArrayList<Integer> getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(ArrayList<Integer> goodsId) {
        this.goodsId = goodsId;
    }

    public ArrayList<Integer> getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(ArrayList<Integer> goodsCount) {
        this.goodsCount = goodsCount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTablesNumber() {
        return tablesNumber;
    }

    public void setTablesNumber(int tablesNumber) {
        this.tablesNumber = tablesNumber;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", addDate=" + addDate +
                ", status=" + status +
                ", goodsId=" + goodsId +
                ", goodsCount=" + goodsCount +
                ", price=" + price +
                ", tablesNumber=" + tablesNumber +
                '}';
    }
}
