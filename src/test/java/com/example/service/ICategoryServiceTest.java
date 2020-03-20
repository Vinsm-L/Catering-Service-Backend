package com.example.service;

import com.example.pojo.Category;
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

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-mybatis_test.xml"})
public class ICategoryServiceTest {

    @Autowired
    private ICategoryService service;

    @Before
    public void setUp() throws Exception {
        Category c = new Category(1, "sample");
        service.addCategory(c);
    }

    @After
    public void tearDown() throws Exception {
        ArrayList<Category> list = service.getCategoriesList();
        for (Category c : list) {
            service.deleteCategoryById(c.getId());
        }
    }

    @Test
    public void getCategoriesList() {
        ArrayList<Category> list = service.getCategoriesList();
        for (Category c : list) {
            assertEquals("sample", c.getName());
        }
    }

    @Test
    public void getCategoryById() {
        ArrayList<Category> list = service.getCategoriesList();
        for (Category c : list) {
            assertEquals(c.getName(), service.getCategoryById(c.getId()).getName());
        }
    }

    @Test
    public void addCategory() {
        ArrayList<Category> list = service.getCategoriesList();
        for (Category c : list) {
            assertEquals("sample", c.getName());
        }
    }

    @Test
    public void deleteCategoryById() {
    }

    @Test
    public void modifyCategory() {
        ArrayList<Category> list = service.getCategoriesList();
        for (Category c : list) {
            c.setName("sample1");
            service.modifyCategory(c);
        }
        list = service.getCategoriesList();
        for (Category c : list) {
            assertEquals("sample1", c.getName());
        }
    }
}