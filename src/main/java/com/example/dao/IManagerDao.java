package com.example.dao;

import com.example.pojo.Manager;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Manager操作接口
 * 通过mybatis的ManagerMapper.xml来实现各方法，无需创建实现类
 */
@Repository
public interface IManagerDao {
    /**
     * 通过用户名获取Manager对象
     * @param name Manager用户名
     * @return 对应Manager对象 or null
     */
    public Manager getManagerByName(String name);

    /**
     * 向数据库插入新的manager
     * @param manager 待插入对象
     * @return 插入行的主键 即manager id （需包含<selectKet>语句，否则返回null）
     * @throws DataAccessException 插入错误，抛出异常
     */
    public int insertManager(Manager manager) throws DataAccessException;

    /**
     * 通过name删除manager
     * @param name
     * @return 修改的数据库行数 即删除实例个数
     * @throws DataAccessException
     */
    public int deleteManagerByName(String name) throws DataAccessException;

    /**
     * 更新manager密码
     * @param manager 待更新manager
     * @return 修改的数据库行数
     * @throws DataAccessException
     */
    public int updateManager(Manager manager)throws DataAccessException;

}
