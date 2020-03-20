package com.example.controller;

import com.example.pojo.Category;
import com.example.pojo.Goods;
import com.example.service.ICategoryService;
import com.example.service.IMenuService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

//import com.sun.deploy.net.HttpResponse;

@Controller
@RequestMapping(value = "/api/*/menu")
public class MenuController extends HttpServlet {
    @Autowired
    private IMenuService menuService;

    private static final Logger logger = Logger.getLogger(MenuController.class);
    @Autowired
    private ICategoryService categoryService;

    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getMenu(HttpServletRequest request, HttpServletResponse response){
        //response.setHeader("content-type", "text/html;charset=UTF-8");
        //System.out.println("Path: "+System.getProperty("evan.webapp"));

        response.setCharacterEncoding("UTF-8");
        response.setContentType("Content-Type:application/json");

        //获取所有菜品
        ArrayList<Category> cateList = categoryService.getCategoriesList();
        //System.out.println(cateList);
        JSONObject jsonObject = new JSONObject();

        //json加入msg
        jsonObject.put("msg", "OK");



        ArrayList<JSONObject> data = new ArrayList<JSONObject>();
        //menuService.deleteGoodsById(1)
       for (Category x : cateList) {

            JSONObject temp = JSONObject.fromObject(x);
            JSONObject cateoryJSON = new JSONObject();
            cateoryJSON.put("category", temp);


            ArrayList<JSONObject> goodsjson = new ArrayList<JSONObject>();
            //获取每个菜品对应的所有菜
            List<Goods> menulist = menuService.getGoodsListByCategory(x);
            for (Goods y : menulist) {
                int id = y.getId();
                String name = y.getName();
                String des = y.getDesc();
                String image = y.getImgSrc();
                float price = y.getPrice();
                int volume = y.getVolume();
                JSONObject temp3 = new JSONObject();
                temp3.put("id", id);
                temp3.put("name", name);
                temp3.put("desc", des);
                temp3.put("price", price);
                temp3.put("img_src", image);
                temp3.put("volume", volume);
                goodsjson.add(temp3);
            }
            cateoryJSON.put("goods",goodsjson);
            data.add(cateoryJSON);
        }
        jsonObject.put("data", data);
        //System.out.println(jsonObject.toString());
        return jsonObject.toString();
    }
}
