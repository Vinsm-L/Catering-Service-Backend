package com.example.service.implement;

import com.example.dao.ICategoryDao;
import com.example.dao.IGoodsDao;
import com.example.pojo.Category;
import com.example.pojo.Goods;
import com.example.service.ICategoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 类别管理service实现类
 */
@Service
public class CategoryServiceImpl implements ICategoryService {
   // private static final Logger LOGGER = Logger.getLogger(CategoryServiceImpl.class);
    /**
     * 自动注入的DAO
     */
    @Autowired
    private ICategoryDao categoryDao;

    @Autowired
    private IGoodsDao goodsDao;

    @Override
    public ArrayList<Category> getCategoriesList() {
        return categoryDao.getAllCategories();
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryDao.getCategoryByID(id);
    }

    @Override
    public boolean addCategory(Category category) {

        try {
            if (categoryDao.insertCategory(category) > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            //LOGGER.error(e);
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean deleteCategoryById(int id) {
        try {
            Category cate = categoryDao.getCategoryByID(id);
            List<Goods> goodlist = goodsDao.getGoodsListByCategory(cate);
            if (cate != null) {
                for (int i = 0; i < goodlist.size(); i++) {
                    goodsDao.deleteSpecficRelation(goodlist.get(i).getId(), id);
                }
                categoryDao.deleteCategoryById(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            //LOGGER.error(e);
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean modifyCategory(Category category) {
        try {
            if (categoryDao.updateCategory(category) > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            //LOGGER.error(e);
            System.out.println(e);
            return false;
        }
    }
}
