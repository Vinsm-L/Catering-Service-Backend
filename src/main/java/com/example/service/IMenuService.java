package com.example.service;

import com.example.pojo.Category;
import com.example.pojo.Goods;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 商品操作Service接口
 */
public interface IMenuService {

    /**
     * 获取所有商品
     * @return 商品列表
     */
    public List<Goods> getGoodsList();


    public List<Goods> getGoodsListNotInCate();

    public Goods getGoodsNotInCateById(int id);

    public Goods getAllGoodsBydishID(int id);

    /**
     * 获取某类别下所有商品
     * @param category 商品类别名
     * @return 商品列表
     */
    public List<Goods> getGoodsListByCategory(Category category);

    /**
     * 通过商品id获取商品
     * @param id
     * @return
     */
    public Goods getGoodsById(int id);

    /**
     * 添加新商品
     * @param goods
     * @return boolean 表示操作结果
     */
    public boolean addGoods(Goods goods);

    /**
     * 删除商品
     * （不建议删除商品，因历史订单需要已有商品信息，查询订单时可能出错）
     * 需要重新设计
     * @param id
     * @return boolean表示操作结果
     */
    public boolean deleteGoodsById(int id);

    /**
     * 修改已有商品信息
     * @param goods
     * @return 操作结果
     */
    public boolean modifyGoods(Goods goods);

}
