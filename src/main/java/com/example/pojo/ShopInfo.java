package com.example.pojo;

/**
 * 商铺信息实体


    public ShopInfo() {
    } */
    public class ShopInfo {
        private String name;
        private String info;
        private String iconUrl;
        private String backgroundUrl;
        private String telephone;
        private String address;
        private boolean status;

    public ShopInfo(String name, String info, String iconUrl, String backgroundUrl, String telephone, String address, boolean status) {
        this.name = name;
        this.info = info;
        this.iconUrl = iconUrl;
        this.backgroundUrl = backgroundUrl;
        this.telephone = telephone;
        this.address = address;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
