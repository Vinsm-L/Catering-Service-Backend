package com.example.controller;

import com.example.pojo.ShopInfo;
import com.example.service.IShopService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 店铺信息管理controller
 */
@Controller
@RequestMapping(produces = "application/json;charset=utf-8;")
public class ShopController {
    private static final Logger logger = Logger.getLogger(ShopController.class);


    @Autowired
    private IShopService shopinfoService;

    //修改商家信息
    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/api/*/modify/basic", method = RequestMethod.POST)
    public String modifyBasic(@RequestBody String data, HttpServletResponse response) {
        //response.setHeader("content-type", "text/html;charset=UTF-8");
        logger.info("modify shop information!");
        JSONObject res = new JSONObject();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("Content-Type:application/json");
        JSONObject json = JSONObject.fromObject(data);
        String name = json.getString("name");
        String phone = json.getString("phone");
        String location = json.getString("location");
        String notice = json.getString("notice");
        String temp_status = json.getString("status");
        String iconurl = "http://139.199.71.21:8080/image/icon.jpg";
        String backgroundurl = "http://139.199.71.21:8080/image/background.jpg";

        boolean status;
        //System.out.println(temp_status);

        //System.out.println(status);
        ShopInfo preInfo = shopinfoService.getShopInfo();
        boolean flag = false;
        ShopInfo shopinfo = new ShopInfo(name, notice, iconurl, backgroundurl, phone, location, true);
        if (preInfo == null) {
            flag = shopinfoService.addShopInfo(shopinfo);
        } else {
            flag = shopinfoService.modifyShopInfo(shopinfo);
        }

        if(flag) {
            res.put("msg", "OK");
            JSONObject temp = new JSONObject();
            temp.put("name", name);
            temp.put("notice", notice);
            temp.put("phone", phone);
            temp.put("location", location);
            temp.put("status", 1);
            logger.info("modify shop information successfully!");
            res.put("data", temp);
        } else {
            logger.info("modify shop information failed!");
            res.put("msg", "没有权限");
            res.put("data", "");
        }
        return res.toString();

    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/api/*/query/basic", method = RequestMethod.GET)
    public String queryBasic(HttpServletRequest request, HttpServletResponse response) {
        //response.setHeader("content-type", "text/html;charset=UTF-8");
        JSONObject res = new JSONObject();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("Content-Type:application/json");
        ShopInfo shopinfo = shopinfoService.getShopInfo();
        response.setStatus(200);
        res.put("msg", "OK");
        if (shopinfo == null) {
            JSONObject temp = new JSONObject();
            temp.put("name", "");
            temp.put("notice", "");
            temp.put("phone", "");
            temp.put("location", "");
            temp.put("status", 1);
            res.put("data",temp);

            return res.toString();
        } else {
            JSONObject temp = new JSONObject();
            temp.put("name", shopinfo.getName());
            temp.put("notice", shopinfo.getInfo());
            temp.put("phone", shopinfo.getTelephone());
            temp.put("location", shopinfo.getAddress());
            temp.put("status", shopinfo.isStatus());
            res.put("data", temp);

            return res.toString();
        }
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/api/*/shop", method = RequestMethod.GET)
    public String modifyBasic(HttpServletRequest request, HttpServletResponse response) {
        //response.setHeader("content-type", "text/html;charset=UTF-8");
        JSONObject res = new JSONObject();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("Content-Type:application/json");
        ShopInfo shopinfo = shopinfoService.getShopInfo();
        if (shopinfo != null) {
            res.put("msg", "OK");
            JSONObject temp = new JSONObject();
            temp.put("name", shopinfo.getName());
            temp.put("info", shopinfo.getInfo());
            temp.put("icon_url", shopinfo.getIconUrl());
            temp.put("background_url", shopinfo.getBackgroundUrl());
            temp.put("telephone", shopinfo.getTelephone());
            temp.put("address", shopinfo.getAddress());
            temp.put("status", shopinfo.isStatus());
            res.put("data",temp);
        } else {
            res.put("msg","Not Found");
        }


        return res.toString();
    }

}


