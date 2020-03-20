package com.example.service;

import com.example.pojo.ShopInfo;

public interface IShopService {
    /**
     * 获取店铺信息
     * @return
     */
    public ShopInfo getShopInfo();

    /**
     * 修改店铺信息
     * @param shopInfo
     * @return
     */
    public boolean addShopInfo(ShopInfo shopInfo);

    public boolean modifyShopInfo(ShopInfo shopInfo);

    /**
     * 查询营业状态
     * 高频方法，单独分离
     * @return
     */
    public boolean getOpenStatus();

    /**
     * 修改营业状态
     * @param status 营业状态
     * @return
     */
    public boolean setOpenStatus(boolean status);
}
