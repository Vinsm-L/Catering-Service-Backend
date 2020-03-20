package com.example.dao;

import com.example.pojo.Tables;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * 桌位DAO接口
 * 无需实现类，通过mybatis的TablesMapper.xml实现
 */
@Repository
public interface ITablesDao {
    /**
     * 获取所有桌位
     * @return 桌位列表
     */
    public List<Tables> getAllTables();

    /**
     * 获取某状态下的所有桌位
     * @param status 指定状态
     * @return 桌位列表
     */
    public List<Tables> getTablesListByStatus(int status);

    /**
     * 根据座位号获取桌位
     * @param number
     * @return tables or null
     */
    public Tables getTablesByNumber(int number);

    /**
     * 插入新的桌位
     * 此处需指定主键 即桌号  不能使用自增主键
     * @param tables
     * @return 影响的行数
     * @throws DataAccessException 操作异常
     */
    public int insertTables(Tables tables) throws DataAccessException;

    /**
     * 根据座位号删除桌位
     * @param number
     * @return 数据库变更行数
     * @throws DataAccessException
     */
    public int deleteTablesByNumber(int number) throws DataAccessException;

    /**
     * 更新桌位信息
     * @param tables
     * @return 数据库变更行数
     * @throws DataAccessException
     */
    public int updateTables(Tables tables) throws DataAccessException;
}
