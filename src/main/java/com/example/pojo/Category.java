package com.example.pojo;

/**
 * 商品分类
 * （待扩展：类别优先级字段）
 */
public class Category {
    // 类别id
    private int id;
    // 类别名
    private String name;

    /**
     * 默认构造函数
     * mybatis创建实体使用
     */
    public Category () {}

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
