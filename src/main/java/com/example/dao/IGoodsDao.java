package com.example.dao;

import com.example.pojo.Category;
import com.example.pojo.Goods;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 商品DAO接口
 * 无需实现类，通过mybatis的GoodsMapper.xml实现
 */
@Repository
public interface IGoodsDao {

    /**
     * 获取所有商品
     * @return 商品列表
     */
    public List<Goods> getAllGoods();


    public List<Goods> getGoodsNotInCate();

    public Goods getGoodsNotInCateByID(int id);

    public  Goods getAllGoodsByID(int id);

    // 废弃方法（category已定义成新类型）
    // public List<String> getAllCategories();

    /**
     * 通过id获取Goods
     * @param id 商品id
     * @return 对应Goods实例 or null
     */
    public Goods getGoodsById(int id);

    /**
     * 获取某类型下所有商品
     * @param cate 指定类型
     * @return 商品列表
     */
    public List<Goods> getGoodsListByCategory(Category cate);

    /**
     * 通过商品名获取商品
     * 考虑到可能出现的同名商品情况
     * 尽量使用id查询
     * @param name
     * @return 商品列表
     */
    public List<Goods> getGoodsListByName(String name);


    /**
     * 插入商品
     * @param goods
     * @return 行数的变化，即增加几行，删除几行
     * @throws DataAccessException 插入失败，抛出异常
     */
    public int insertGoods(Goods goods) throws DataAccessException;

    /**
     * 通过商品id删除对应商品
     * （注意：删除商品后，包含此商品的订单在查询时会出问题，所以非常不建议删除商品）
     *
     * @param id
     * @return table变更行数
     * @throws DataAccessException
     */
    public int deleteGoodsById(int id) throws DataAccessException;

    /**
     * 更新商品信息
     * （不包含category字段，category有独立方法进行更新）
     * @param goods
     * @return table变更行数
     * @throws DataAccessException
     */
    public int updateGoods(Goods goods) throws DataAccessException;

    /**
     * 商品信息修改后，为关系表中插入一行goods category映射
     * 此方法在方法insert与delete后调用时，必须组成事务
     * 可单独调用（当商品信息更新了所属category时）
     * @param goodsId
     * @param categoryId
     * @return
     * @throws DataAccessException
     */
    public int insertSpecificRelation(int goodsId, int categoryId) throws DataAccessException;

    /**
     * 商品信息修改后，为关系表中删除一行goods category映射
     * 此方法在方法insert与delete后调用时，必须组成事务
     * 可单独调用（当商品信息更新了所属category时）
     * @param goodsId
     * @param categoryId
     * @return
     * @throws DataAccessException
     */
    public int deleteSpecficRelation(int goodsId, int categoryId) throws DataAccessException;
}
