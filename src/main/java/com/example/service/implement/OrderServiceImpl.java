package com.example.service.implement;

import com.example.dao.IOrderDao;
import com.example.dao.ITablesDao;
import com.example.pojo.Order;
import com.example.pojo.Tables;
import com.example.service.IOrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单Service实现类
 */
@Service
public class OrderServiceImpl implements IOrderService {
   // private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class);
    /**
     * 自动注入的DAO接口
     */
    @Autowired
    private IOrderDao orderDao;
    @Autowired
    private ITablesDao tablesDao;

    @Override
    public List<Order> getOrdersList() {
        return orderDao.getAllOrders();
    }

    @Override
    public List<Order> getOrdersListByTimeSlot(Date begin, Date end) {
        return orderDao.getOrdersListByPeriod(begin, end);
    }

    @Override
    public List<Order> getOrdersListByStatus(int status) {
        return orderDao.getOrdersListByStatus(status);
    }

    @Override
    public Order getOrderById(int id) {
        return orderDao.getOrderById(id);
    }

    @Override
    public boolean addOrder(Order order) {

        try {
            //System.out.println("**");
            if (orderDao.insertOrder(order)>0) {
                //table设置orderid和status
                int tableid = order.getTablesNumber();
                Tables table = tablesDao.getTablesByNumber(tableid);
                table.setOrdersId(order.getId());
                int statue = order.getStatus();
                table.setStatus(statue);
                tablesDao.updateTables(table);

                ArrayList<Integer>  goodslist = order.getGoodsId();
                ArrayList<Integer>  countlist = order.getGoodsCount();
                //System.out.println("Order id "+ order.getId());
                for (int i = 0; i < goodslist.size(); i++) {
                    orderDao.insertSpecificRelation(order.getId(), goodslist.get(i), countlist.get(i));
                }

                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            //System.out.println("1");
            System.out.println(e);
            //LOGGER.error(e);
            return false;
        }
    }

    @Override
    public boolean deleteOrderById(int id) {
        try {
            Order order = orderDao.getOrderById(id);
            ArrayList<Integer> goodslist = order.getGoodsId();
            ArrayList<Integer> countlist = order.getGoodsCount();
            if (order != null) {
                orderDao.deleteOrderById(id);
                for (int i = 0; i < goodslist.size(); i++) {
                    orderDao.deleteSpecificRelation(order.getId(), goodslist.get(i));
                }
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            //LOGGER.error(e);
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean modifyOrder(Order order) {
        try {
            if (orderDao.updateOrder(order) > 0) {
                int tableid = order.getTablesNumber();
                Tables table = tablesDao.getTablesByNumber(tableid);
                table.setStatus(order.getStatus());
                tablesDao.updateTables(table);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            //LOGGER.error(e);
            System.out.println(e);
            return false;
        }
    }
}
