package com.example.service;

import com.example.pojo.Category;
import com.example.pojo.Tables;
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
public class ITablesServiceTest {

    @Autowired
    private ITablesService service;

    @Before
    public void setUp() throws Exception {
        Tables t = new Tables(1, 4, 0);
        service.addTables(t);
    }

    @After
    public void tearDown() throws Exception {
        service.deleteTableByNumber(1);
    }

    @Test
    public void getTablesList() {
        List<Tables> list = service.getTablesList();
        for (Tables t : list) {
            assertEquals(1, t.getNumber());
        }
    }

    @Test
    public void getTablesListByStatus() {
        List<Tables> list = service.getTablesListByStatus(0);
        for (Tables t : list) {
            assertEquals(1, t.getNumber());
        }
    }

    @Test
    public void getTablesByNumber() {
        Tables t = service.getTablesByNumber(1);
        assertEquals(4, t.getCapacity());
    }

    @Test
    public void addTables() {
    }

    @Test
    public void deleteTableByNumber() {
    }

    @Test
    public void modifyTables() {
        Tables newTable = new Tables(1, 8, 0);
        service.modifyTables(newTable);
        assertEquals(8, service.getTablesByNumber(1).getCapacity());
    }
}