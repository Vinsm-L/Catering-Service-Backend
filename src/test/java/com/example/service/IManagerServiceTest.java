package com.example.service;

import com.example.pojo.Manager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-mybatis_test.xml"})
public class IManagerServiceTest {

    @Autowired
    private IManagerService service;

    @Before
    public void setUp() throws Exception {
        Manager manager = new Manager("myUser", "000000");
        service.addUser(manager);
    }

    @After
    public void tearDown() throws Exception {
        service.deleteUserByName("testuser");
    }

    @Test
    public void verifyPassword() {
        Manager manager = new Manager("testuser", "123456");
        assertEquals(true, service.verifyPassword(manager));
    }

    @Test
    public void addUser() {
    }

    @Test
    public void isUsernameExist() {
        assertEquals(true, service.isUsernameExist("testuser"));
    }

    @Test
    public void changePassword() {
        Manager manager = new Manager("testuser", "000000");
        assertEquals(true, service.changePassword(manager));
    }

    @Test
    public void deleteUserByName() {
        assertEquals(true, service.deleteUserByName("testUser"));
    }
}