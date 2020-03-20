package com.example.service.implement;

import com.example.dao.IManagerDao;
import com.example.pojo.Manager;
import com.example.service.IManagerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * admin管理Service实现类
 */
@Service
public class ManagerServiceImpl implements IManagerService {
    //private static final Logger LOGGER = Logger.getLogger(CategoryServiceImpl.class);
    /**
     * 自动注入的DAO
     */
    @Autowired
    private IManagerDao managerDao;

    @Override
    public boolean verifyPassword(Manager manager) {
        Manager man = managerDao.getManagerByName(manager.getUsername());
        if (null == man) return false;
        return (man.getUsername().equals(manager.getUsername())
        && man.getPassword().equals(manager.getPassword()));
    }

    @Override
    public boolean addUser(Manager manager) {
        return managerDao.insertManager(manager) != 0;
    }

    @Override
    public boolean isUsernameExist(String username) {
        return null != managerDao.getManagerByName(username);
    }

    @Override
    public boolean changePassword(Manager manager) {
        return 0 != managerDao.updateManager(manager);
    }

    @Override
    public boolean deleteUserByName(String username) {
        return 0 != managerDao.deleteManagerByName(username);
    }
}
