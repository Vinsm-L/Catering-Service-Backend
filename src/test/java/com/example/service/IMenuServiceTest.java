package com.example.service;

import com.example.pojo.Category;
import com.example.pojo.Goods;
import com.example.service.implement.CategoryServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-mybatis_test.xml"})
public class IMenuServiceTest {

    @Autowired
    private IMenuService service;


    @Before
    public void setUp() throws Exception {
        Category c = new Category(1, "default");
        ArrayList<Category> cate = new ArrayList<Category>();
        cate.add(c);
        Goods g = new Goods("rice", "best", cate, 10, 100);
        service.addGoods(g);
    }

    @After
    public void tearDown() throws Exception {
        List<Goods> list = service.getGoodsList();
        for (Goods g : list) {
            service.deleteGoodsById(g.getId());
        }
    }

    @Test
    public void getGoodsList() {
        List<Goods> list = service.getGoodsList();
        for (Goods g : list) {
            assertEquals("rice", g.getName());
        }
    }

    @Test
    public void getGoodsListByCategory() {
        Category c = new Category(1, "default");
        List<Goods> list = service.getGoodsListByCategory(c);
        for (Goods g : list) {
            assertEquals("rice", g.getName());
        }
    }

    @Test
    public void getGoodsById() {
        List<Goods> list = service.getGoodsList();
        for (Goods g : list) {
            assertEquals(g.getName(), service.getGoodsById(g.getId()).getName());
        }
    }

    @Test
    public void addGoods() {
        //Category c = new Category(1, "default");
        //ArrayList<Category> cate = new ArrayList<Category>();
        //cate.add(c);
        //Goods g = new Goods("rice", "best", cate, 10, 100);
        //assertEquals(true, service.addGoods(g));
    }

    @Test
    public void deleteGoodsById() {
    }

    @Test
    public void modifyGoods() {
        List<Goods> list = service.getGoodsList();
        for (Goods g : list) {
            g.setName("noodles");
            service.modifyGoods(g);
        }
        list = service.getGoodsList();
        for (Goods g : list) {
            assertEquals("noodles", g.getName());
        }
    }
}