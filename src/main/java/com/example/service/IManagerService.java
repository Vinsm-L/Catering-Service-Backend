package com.example.service;

import com.example.pojo.Manager;

/**
 * admin登录与账户管理Service接口
 */
public interface IManagerService {
    public boolean verifyPassword(Manager manager);

    public boolean addUser(Manager manager);

    public boolean isUsernameExist(String username);

    public boolean changePassword(Manager manager);

    public boolean deleteUserByName(String username);

}
