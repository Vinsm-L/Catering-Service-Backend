package com.example.dao;

import com.example.pojo.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * 订单DAO接口
 * 无需实现类，通过mybatis的OrderMapper.xml实现
 */
@Repository
public interface IOrderDao {
    /**
     * 获取所有订单
     * @return 订单列表
     */
    public List<Order> getAllOrders();

    /**
     * 获取某时间段内所有订单
     * @Param begin 开始时间
     * @Param end 结束时间
     * @return 订单列表
     */
    public List<Order> getOrdersListByPeriod(Date begin, Date end);

    /**
     * 获取某一状态下的所有订单
     * @param status 指定状态
     * @return 订单列表
     */
    public List<Order> getOrdersListByStatus(int status);

    /**
     * 根据订单id获取对应订单
     * @param id
     * @return Order对象 or null
     */
    public Order getOrderById(int id);

    /**
     * 插入新的订单
     * @param order
     * @return 插入行主键
     * @throws DataAccessException 操作异常
     */
    public int insertOrder(Order order) throws DataAccessException;

    /**
     * 根据id删除订单
     * @param id
     * @return 数据库变更行数
     * @throws DataAccessException
     */
   public int deleteOrderById(int id) throws DataAccessException;

    /**
     * 更新订单
     * @param order 待更新的订单
     * @return 数据库变更行数
     * @throws DataAccessException
     */
    public int updateOrder(Order order) throws DataAccessException;

    /**
     * 向order_goods关系表中插入新行
     * （此方法一般与insertOrder同时执行，需要开启事务）
     * 可单独执行（可设计新api）
     * @param orderId 订单id
     * @param goodsId 商品id
     * @param count 商品数量
     * @return tables影响行数
     */
    public int insertSpecificRelation(int orderId, int goodsId, int count);

    /**
     * 向order_goods关系表中删除行
     * （此方法一般与deleteOrder同时执行，需要开启事务）
     * 可单独执行（可设计新api）
     * @param orderId 订单id
     * @param goodsId 商品id
     * @return tables影响行数
     */
    public int deleteSpecificRelation(int orderId, int goodsId);

    /**
     * 更新关系表中某行信息
     * 此方法一般作为单独api被调用，用于修改订单中商品数量
     * @param orderId 订单id
     * @param goodsId 商品id
     * @param count 商品数量
     * @return tables影响行数
     */
    public int updateSpecificRelation(int orderId, int goodsId, int count);
}
