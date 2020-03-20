package com.example.service;

import com.example.pojo.Order;

import java.sql.Date;
import java.util.List;

/**
 * 订单管理Service接口
 */
public interface IOrderService {

    /**
     * 获取所有订单
     * @return 订单列表
     */
    public List<Order> getOrdersList();

    /**
     * 获取时间段内所有订单
     * @param begin 起始时间（sql.Date类型）
     * @param end 结束时间（sql.Date类型）
     * @return 订单列表
     */
    public List<Order> getOrdersListByTimeSlot(Date begin, Date end);

    /**
     * 获取某状态下所有订单
     * @param status 查询状态
     * @return 订单列表
     */
    public List<Order> getOrdersListByStatus(int status);

    /**
     * 通过id获取订单
     * @param id
     * @return 查询结果 （无结果则返回null）
     */
    public Order getOrderById(int id);

    /**
     * 添加新订单
     * @param order
     * @return 操作结果
     */
    public boolean addOrder(Order order);

    /**
     * 删除订单
     * @param id 订单id
     * @return 操作结果
     */
    public boolean deleteOrderById(int id);

    /**
     * 修改订单信息
     * @param order
     * @return 操作结果
     */
    public boolean modifyOrder(Order order);

}
