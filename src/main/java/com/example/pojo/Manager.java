package com.example.pojo;

public class Manager {
    private String username;
    private String password;

    /**
     * 默认构造函数
     * mybatis创建实体使用
     */
    public Manager() {}

    public Manager(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
