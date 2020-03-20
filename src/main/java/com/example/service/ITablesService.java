package com.example.service;

import com.example.pojo.Tables;

import java.util.List;

/**
 * 桌位Service接口
 */
public interface ITablesService {
    /**
     * 获取所有桌位信息
     * @return 桌位列表
     */
    public List<Tables> getTablesList();

    /**
     * 获取某状态下的所有桌位
     * @param status 指定状态
     * @return 桌位列表
     */
    public List<Tables> getTablesListByStatus(int status);

    /**
     * 根据位号获取桌位
     * @param number
     * @return 查询结果（为空则null）
     */
    public Tables getTablesByNumber(int number);

    /**
     * 添加新桌位
     * @param tables
     * @return 操作结果
     */
    public boolean addTables(Tables tables);

    /**
     * 删除桌位
     * @param number
     * @return 操作结果
     */
    public boolean deleteTableByNumber(int number);

    /**
     * 修改桌位信息
     * @param tables
     * @return 操作结果
     */
    public boolean modifyTables(Tables tables);
}
