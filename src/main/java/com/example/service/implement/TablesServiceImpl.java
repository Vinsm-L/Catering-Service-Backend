package com.example.service.implement;

import com.example.dao.ITablesDao;
import com.example.pojo.Tables;
import com.example.service.ITablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 桌位Service实现类
 */
@Service
public class TablesServiceImpl implements ITablesService {

    /**
     * 自动注入的dao
     */
    @Autowired
    private ITablesDao tablesDao;

    @Override
    public List<Tables> getTablesList() {
        return tablesDao.getAllTables();
    }

    @Override
    public List<Tables> getTablesListByStatus(int status) {
        return tablesDao.getTablesListByStatus(status);
    }

    @Override
    public Tables getTablesByNumber(int number) {
        return tablesDao.getTablesByNumber(number);
    }

    @Override
    public boolean addTables(Tables tables) {
        if (tablesDao.insertTables(tables)> 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteTableByNumber(int number) {
        if (tablesDao.deleteTablesByNumber(number) > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean modifyTables(Tables tables) {
        if (tablesDao.updateTables(tables) > 0) {
            return true;
        } else {
            return false;
        }
    }
}
