package com.example.pojo;

import java.util.ArrayList;

/**
 * 商品实体类
 */
public class Goods {

    /**
     * 商品id
     * 数据库自增，无需初始化
     */
    private int id;
    // 商品名,可重名
    private String name;
    // 描述信息
    private String desc;
    // 商品分类(Category组成的List)
    private ArrayList<Category> cate;
    // 商品价格
    private float price;
    // 图片URl地址
    private String imgSrc;
    // 商品剩余数量
    private int volume;

    // 默认图片地址
    private static String defaultImageSrc = "defaultImageSrc";

    /**
     * 默认构造函数
     * mybatis创建实体使用
     */
    public Goods() {
    }

    /**
     *  带图片地址的构造函数
     * @param name
     * @param desc
     * @param cate
     * @param price
     * @param imgSrc
     * @param volume
     */
    public Goods(String name, String desc, ArrayList<Category> cate, float price, String imgSrc, int volume) {
        this.name = name;
        this.desc = desc;
        this.cate = cate;
        this.price = price;
        this.imgSrc = imgSrc;
        this.volume = volume;
        this.id = this.id+1;
    }

    /**
     * 使用默认图片地址的构造函数
     * @param name
     * @param desc
     * @param cate
     * @param price
     * @param volume
     */
    public Goods(String name, String desc, ArrayList<Category> cate, float price, int volume) {
        this.name = name;
        this.desc = desc;
        this.cate = cate;
        this.price = price;
        this.imgSrc = defaultImageSrc;
        this.volume = volume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ArrayList<Category> getCate() {
        return cate;
    }

    public void setCate(ArrayList<Category> cate_) {
        this.cate = cate_;
        //System.out.println("ttt: "+this.cate.toString());
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public int getVolume() { return this.volume; }

    public void setVolume(int volume) { this.volume = volume; }


    public static String getDefaultImageSrc() {
        return defaultImageSrc;
    }

    public static void setDefaultImageSrc(String defaultImageSrc) {
        Goods.defaultImageSrc = defaultImageSrc;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", cate='" + cate + '\'' +
                ", price=" + price +
                ", imgSrc='" + imgSrc + '\'' +
                ", volume=‘" + volume + '\'' +
                '}';
    }
}
