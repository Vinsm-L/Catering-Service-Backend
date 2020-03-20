package com.example.service.implement;

import com.example.dao.IShopDao;
import com.example.pojo.ShopInfo;
import com.example.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 店铺管理Service实现类
 */
@Service
public class ShopServiceImpl implements IShopService {
    @Autowired
    private IShopDao shopDao;

    @Override
    public ShopInfo getShopInfo() {
        return shopDao.getShopInfo();
    }

    @Override
    public boolean addShopInfo(ShopInfo shopInfo) {
        return(shopDao.insertShopInfo(shopInfo) > 0);
    }

    @Override
    public boolean modifyShopInfo(ShopInfo shopInfo) {
        return (shopDao.updateShopInfo(shopInfo) > 0);
    }

    @Override
    public boolean getOpenStatus() {
        return shopDao.getStatus();
    }

    @Override
    public boolean setOpenStatus(boolean status) {
        shopDao.setStatus(status);
        return status == shopDao.getStatus();
    }
}
