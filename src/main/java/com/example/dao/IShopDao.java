package com.example.dao;

import com.example.pojo.ShopInfo;
import org.springframework.stereotype.Repository;

/**
 * 商铺信息管理dao
 * table中只有一行，故只提供update与get方法
 * mybatis映射文件：ShopMapper.xml
 */

@Repository
public interface IShopDao {
    /**
     * 获取店铺信息
     * @return
     */
    public ShopInfo getShopInfo();

    /**
     * 修改店铺信息
     * @param shopInfo
     * @return 变更行数
     */
    public int insertShopInfo(ShopInfo shopInfo);

    public int updateShopInfo(ShopInfo shopInfo);

    /**
     * 获取营业状态
     * @return
     */
    public boolean getStatus();

    /**
     * 设置营业状态
     * @param status
     * @return
     */
    public int setStatus(boolean status);
}
