package com.example.pojo;

/**
 * 针对api返回格式而创建的对象
 * 为了方便生成json
 */
public class responseObj {
    private String msg;
    private String data;

    public responseObj(String msg, String data) {
        this.msg = msg;
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
