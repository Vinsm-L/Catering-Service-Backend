package com.example.controller;


import com.example.pojo.Category;
import com.example.service.ICategoryService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller


public class CategoryController extends HttpServlet {
    //log日志
    private static final Logger logger = Logger.getLogger(CategoryController.class);

    @Autowired
    private ICategoryService categoryService;

    //查找菜的种类
    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/api/*/query/category", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getCategory(HttpServletResponse response) {
        logger.info("query category!");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("Content-Type:application/json");


        JSONObject json = new JSONObject();

        List<Category> temp = categoryService.getCategoriesList();
        if (temp.size() >= 0) {
            response.setStatus(200);
            json.put("msg", "OK");
            JSONArray temp1 =  new JSONArray();

            for (int i = 0; i < temp.size(); i++) {
                JSONObject temp3 = new JSONObject();
                temp3.put("categoryID", temp.get(i).getId());
                temp3.put("categoryName", temp.get(i).getName());
                temp1.add(temp3);
            }

            json.put("data", temp1);
            logger.info("query category successfully!");
            return json.toString();
        } else {
            response.setStatus(403);
            json.put("msg", "没有权限");
            json.put("data", "");
            logger.info("query category failed!");
            return  json.toString();
        }

    }


    //修改菜品
    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/api/*/modify/category", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String renameCate(@RequestBody String data, HttpServletResponse response) {
        logger.info("modify category!");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("Content-Type:application/json");
        JSONObject result = new JSONObject();
        JSONObject temp = JSONObject.fromObject(data);
        String temp_id = temp.getString("categoryID");
        String temp_name = temp.getString("categoryName");
        int id = Integer.parseInt(temp_id);
        Category temp1 = categoryService.getCategoryById(id);
        if (!temp_name.equals(""))
            temp1.setName(temp_name);
        if (categoryService.modifyCategory(temp1)) {
            response.setStatus(200);
            //修改
            result.put("msg", "OK");
            JSONObject temp3 = new JSONObject();
            //修改
            temp3.put("categoryID", id);
            temp3.put("categoryName", temp_name);
            result.put("data", temp3);

            //日志
            logger.info("modify category successfully!");
            return result.toString();
        } else {
            response.setStatus(409);
            result.put("msg", "资源冲突");
            result.put("data", "");
            logger.info("modify category failed!");
            return result.toString();
        }
    }

    //增加菜品类型

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/api/*/add/category", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String  addCate(@RequestBody String data, HttpServletResponse response) {
        logger.info("add category!");

        //设置头部信息
        response.setCharacterEncoding("UTF-8");
        response.setContentType("Content-Type:application/json");

        //定义返回值
        JSONObject res = new JSONObject();


        JSONObject paramJSON = JSONObject.fromObject(data);
        String tempname = paramJSON.getString("categoryName");
        Category temp = new Category();
        temp.setName(tempname);
        if (categoryService.addCategory(temp)) {
            int newid = temp.getId();
            response.setStatus(200);
            res.put("msg", "OK");
            JSONObject temp1 = new JSONObject();
            temp1.put("categoryID", newid);
            temp1.put("categoryName", tempname);
            res.put("data", temp1);
            logger.info("modify category successfully!");
            return res.toString();
        } else {
            response.setStatus(403);
            res.put("msg", "没有权限");
            res.put("data", "");
            logger.info("modify category failed!");
            return res.toString();
        }
    }

    //删除菜品
    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/api/*/delete/category", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String deleteCate(@RequestParam(value = "categoryID", defaultValue = "-1") int id, HttpServletResponse response) {
        logger.info("delete category!");
        //response.setCharacterEncoding("UTF-8");
        response.setContentType("Content-Type:application/json;charset=UTF-8");

        JSONObject res = new JSONObject();

        Category cate = categoryService.getCategoryById(id);
        String tempname = cate.getName();
        if (categoryService.deleteCategoryById(id)) {
            response.setStatus(200);
            res.put("msg", "OK");
            JSONObject temp1 = new JSONObject();
            temp1.put("categoryID", id);
            temp1.put("categoryName", tempname);
            res.put("data", temp1);
            logger.info("delete category successfully!");
            return res.toString();
        } else {
            response.setStatus(403);
            res.put("msg", "没有权限");
            res.put("data", "");
            logger.info("delete category failed!");
            return res.toString();
        }
    }

}
