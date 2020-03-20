package com.example.service;

import com.example.pojo.Category;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品类别操作Service接口
 */
public interface ICategoryService {

    /**
     * 获取所有商品类别
     * @return 类别列表
     */
    public ArrayList<Category> getCategoriesList();

    /**
     * 查
     * @param id
     * @return
     */
    public Category getCategoryById(int id);

    /**
     * 增
     * @param category
     * @return 操作结果
     */
    public boolean addCategory(Category category);

    /**
     * 删除category
     * （注意：删除某类别，需要同时删除关系表中对应行，
     *  以及修改所有属于此类别中的goods的category属性）
     *  （需要使用事务）
     * @param id
     * @return 操作结果
     */
    public boolean deleteCategoryById(int id);

    /**
     * 改
     * @param category
     * @return 操作结果
     */
    public boolean modifyCategory(Category category);
}
