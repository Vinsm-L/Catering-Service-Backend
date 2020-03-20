package com.example.service;

import com.example.pojo.Order;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-mybatis_test.xml"})
public class IOrderServiceTest {

    @Autowired
    private IOrderService service;

    private long time = System.currentTimeMillis();

    private Date date = new Date(time);

    @Before
    public void setUp() throws Exception {
        ArrayList<Integer> goodsId = new ArrayList<Integer>();
        goodsId.add(1);
        ArrayList<Integer> goodsCount = new ArrayList<Integer>();
        goodsCount.add(50);
        Order o = new Order(date, goodsId, goodsCount, 10, 2, 5);
        service.addOrder(o);
    }

    @After
    public void tearDown() throws Exception {
        List<Order> list = service.getOrdersList();
        for (Order o : list) {
            service.deleteOrderById(o.getId());
        }
    }

    @Test
    public void getOrdersList() {
        List<Order> list = service.getOrdersList();
        for (Order o : list) {
            assertEquals(2, o.getStatus());
        }
    }

    @Test
    public void getOrdersListByTimeSlot() {
        long t = System.currentTimeMillis();
        Date d = new Date(t);
        List<Order> list = service.getOrdersListByTimeSlot(date, d);
        for (Order o : list) {
            assertEquals(2, o.getStatus());
        }
    }

    @Test
    public void getOrdersListByStatus() {
        List<Order> list = service.getOrdersListByStatus(2);
        for (Order o : list) {
            assertEquals(5, o.getTablesNumber());
        }
    }

    @Test
    public void getOrderById() {
        List<Order> list = service.getOrdersList();
        for (Order o : list) {
            assertEquals(o.getStatus(), service.getOrderById(o.getId()).getStatus());
        }
    }

    @Test
    public void addOrder() {
    }

    @Test
    public void deleteOrderById() {
    }

    @Test
    public void modifyOrder() {
        List<Order> list = service.getOrdersList();
        for (Order o : list) {
            o.setStatus(3);
            service.modifyOrder(o);
        }
        list = service.getOrdersList();
        for (Order o : list) {
            assertEquals(3, o.getStatus());
        }
    }
}