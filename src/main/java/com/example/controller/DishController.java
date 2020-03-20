package com.example.controller;

import com.example.pojo.Category;
import com.example.pojo.Goods;
import com.example.service.ICategoryService;
import com.example.service.IMenuService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
@CrossOrigin
@RequestMapping(produces = "application/json; charset=utf-8")
public class DishController {
    @Autowired
    private IMenuService menuService;
    @Autowired
    private ICategoryService categoryService;
    private static final Logger logger = Logger.getLogger(DishController.class);
    public static  final String my_separator = File.separator;

    /*商家端api*/

    /*
    增加菜式
     */
    @CrossOrigin
    @RequestMapping(value = "/api/*/add/dish", method = RequestMethod.POST)
    @ResponseBody
    public String addDish(@RequestBody String data, HttpServletResponse response) {
        logger.info("add dish!");

        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type","text/html;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        JSONObject res = new JSONObject();

        //System.out.println(data);
        /* 获取请求body信息 */
        JSONObject json = JSONObject.fromObject(data);
        String dishname = json.getString("dishName");
        String dishprice = json.getString("dishPrice");
        String dishimage = json.getString("dishImg");
        //String volume = json.getString("dishVolume");
        String dishDesc = json.getString("dishDescription");
        String cateid = json.getString("categoryID");
        String demosub = cateid.substring(1,cateid.length()-1);
        String demoArray[] = demosub.split(",");
        if (demosub.length() == 0) {
            demoArray = new String[] {};
        }
        //图片处理
        String imgURL = "";
        if (!dishimage.equals("")) {
            imgURL += "http://";
            String data1="";
            String dataprefix="";

            String[] str=dishimage.split("base64,");
            if(str==null||str.length!=2)
                return null;
            dataprefix=str[0];
            data1=str[1];
            String suffix = "";
            if("data:image/jpeg;".equalsIgnoreCase(dataprefix)){//data:image/jpeg;base64,base64编码的jpeg图片数据
                suffix = ".jpg";
            } else if("data:image/x-icon;".equalsIgnoreCase(dataprefix)){//data:image/x-icon;base64,base64编码的icon图片数据
                suffix = ".ico";
            } else if("data:image/gif;".equalsIgnoreCase(dataprefix)){//data:image/gif;base64,base64编码的gif图片数据
                suffix = ".gif";
            } else if("data:image/png;".equalsIgnoreCase(dataprefix)){//data:image/png;base64,base64编码的png图片数据
                suffix = ".png";
            }

            //因为BASE64Decoder的jar问题，此处使用spring框架提供的工具包
            try {
                String path = System.getProperty("evan.webapp");
                path += "statics";
                path += my_separator;
                path += "image";
                path += my_separator;
                File tempfile = new File(path);
                if (!tempfile.exists()) {
                    tempfile.mkdirs();
                }
                Long picname = System.currentTimeMillis();
                path += picname;
                path += suffix;
                byte[] bs = Base64Utils.decodeFromString(data1);
                //System.out.println(bs);
                File f = new File(path);
                f.setWritable(true, false);
                FileUtils.writeByteArrayToFile(f, bs);





                imgURL += "139.199.71.21:8080/ordering/image/";
                imgURL += picname;
                imgURL += suffix;

                //重新设置图片
                //System.out.println(imgURL);
            } catch (Exception e) {
                logger.error(e);
                e.printStackTrace();
            }

        }

        List<String> tempList = Arrays.asList(demoArray);
        List<Integer> tempint = new ArrayList<Integer>();
        ArrayList<Category> catelist = new ArrayList<Category>();
        for (int i = 0; i < tempList.size(); i++) {
            //System.out.println(Integer.parseInt(tempList.get(i)));
            tempint.add(Integer.parseInt(tempList.get(i)));
            catelist.add(categoryService.getCategoryById(Integer.parseInt(tempList.get(i))));
        }
        Goods good = new Goods(dishname, dishDesc, catelist, Float.parseFloat(dishprice), imgURL, 0);
        if (menuService.addGoods(good)) {
            int dishid = good.getId();
            response.setStatus(200);
            res.put("msg", "OK");
            JSONObject tempjson = new JSONObject();
            JSONObject tempjson1 = new JSONObject();
            tempjson.put("categoryID", tempint);
            tempjson1.put("dishID", dishid);
            tempjson1.put("dishName", dishname);
            tempjson1.put("dishPrice", Float.parseFloat(dishprice));
            tempjson1.put("dishImg",imgURL);
            tempjson1.put("dishDescription", dishDesc);
            tempjson.put("dishInfo", tempjson1);
            res.put("data", tempjson);

            logger.info("add dish successfully!");
            //System.out.println(res.toString());
            return res.toString();
        } else {
            response.setStatus(200);
            res.put("msg", "没有权限");
            res.put("data","");
            logger.info("add dish failed!");
            return  res.toString();
        }
    }


    /*删除菜式*/
    @CrossOrigin
    @RequestMapping(value = "/api/*/delete/dish", method = RequestMethod.GET)
    @ResponseBody
    public String delDish(@RequestParam(value = "dishID", defaultValue = "-1") int dishid,HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("Content-Type:application/json");

        JSONObject res = new JSONObject();
        //
        // System.out.println(dishid);
        Goods good = menuService.getGoodsById(dishid);
        if (good == null) {
            good = menuService.getGoodsNotInCateById(dishid);
        }
        if (good != null) {
            menuService.deleteGoodsById(dishid);
            response.setStatus(200);
            res.put("msg", "OK");
            String dishname = good.getName();
            float dishprice = good.getPrice();
            String dishimage = good.getImgSrc();
            //String volume = json.getString("dishVolume");
            String dishDesc = good.getDesc();
            ArrayList<Category> cateList = good.getCate();
            List<Integer> cateid =  new ArrayList<Integer>();
            for (int i = 0; i < cateList.size(); i++) {
                cateid.add(cateList.get(i).getId());
            }
            JSONObject tempjson = new JSONObject();
            JSONObject tempjson1 = new JSONObject();
            tempjson.put("categoryID", cateid);
            tempjson1.put("dishID", good.getId());
            tempjson1.put("dishName", dishname);
            tempjson1.put("dishPrice", dishprice);
            tempjson1.put("dishImg", dishimage);
            tempjson1.put("dishDescription", dishDesc);
            tempjson.put("dishInfo", tempjson1);
            res.put("data", tempjson);
            return res.toString();
        } else {
            response.setStatus(403);
            res.put("msg", "没有权限");
            res.put("data", "");
            return res.toString();
        }

    }

    /*修改菜式*/
    @CrossOrigin
    @RequestMapping(value = "/api/*/modify/dish", method = RequestMethod.POST)
    @ResponseBody
    public String modifyDish(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("Content-Type:application/json");
        JSONObject res = new JSONObject();
        /* 获取请求body信息 */
        JSONObject json = JSONObject.fromObject(data);
        String dishid = json.getString("dishID");
        String dishname = json.getString("dishName");
        String dishprice = json.getString("dishPrice");
        String dishimage = json.getString("dishImg");
        //String volume = json.getString("dishVolume");
        String dishDesc = json.getString("dishDescription");
        String cateid = json.getString("categoryID");
        String demosub = cateid.substring(1,cateid.length()-1);
        String demoArray[] = demosub.split(",");
        if (demosub.length() == 0) {
            demoArray = new String[] {};
        }
        List<String> tempList = Arrays.asList(demoArray);
        List<Integer> tempint = new ArrayList<Integer>();

        ArrayList<Category> catelist = new ArrayList<Category>();

        for (int i = 0; i < tempList.size(); i++) {
            //System.out.println(Integer.parseInt(tempList.get(i)));
            tempint.add(Integer.parseInt(tempList.get(i)));
            catelist.add(categoryService.getCategoryById(Integer.parseInt(tempList.get(i))));
        }

        //得到要处理的goods
        int tempdishid = Integer.parseInt(dishid);
        Goods good = menuService.getGoodsById(tempdishid);
        //图片处理
        if (good == null) {
            good = menuService.getGoodsNotInCateById(tempdishid);
        }
        //System.out.println("788: "+good.toString());
        if (!dishimage.equals("")) {
            String imgURL = "http://";
            String data1="";
            String dataprefix="";

            String[] str=dishimage.split("base64,");
            if(str==null||str.length!=2)
                return null;
            dataprefix=str[0];
            data1=str[1];
            String suffix = "";
            if("data:image/jpeg;".equalsIgnoreCase(dataprefix)){//data:image/jpeg;base64,base64编码的jpeg图片数据
                suffix = ".jpg";
            } else if("data:image/x-icon;".equalsIgnoreCase(dataprefix)){//data:image/x-icon;base64,base64编码的icon图片数据
                suffix = ".ico";
            } else if("data:image/gif;".equalsIgnoreCase(dataprefix)){//data:image/gif;base64,base64编码的gif图片数据
                suffix = ".gif";
            } else if("data:image/png;".equalsIgnoreCase(dataprefix)){//data:image/png;base64,base64编码的png图片数据
                suffix = ".png";
            }

            //因为BASE64Decoder的jar问题，此处使用spring框架提供的工具包
            try {
                String path = System.getProperty("evan.webapp");
                path += "statics";
                path += my_separator;
                path += "image";
                path += my_separator;
                File tempfile = new File(path);
                if (!tempfile.exists()) {
                    tempfile.mkdirs();
                }
                Long picname = System.currentTimeMillis();
                path += picname;
                path += suffix;
                byte[] bs = Base64Utils.decodeFromString(data1);
                //System.out.println(bs);
                File f = new File(path);
                f.setWritable(true, false);
                FileUtils.writeByteArrayToFile(f, bs);

                imgURL += "139.199.71.21:8080/ordering/image/";
                imgURL += picname;
                imgURL += suffix;

                //重新设置图片
                good.setImgSrc(imgURL);
                //System.out.println(imgURL);
            } catch (Exception e) {
                logger.error(e);
                e.printStackTrace();
            }

        }

        //System.out.println("333: "+catelist.toString());
        good.setCate(catelist);
        //System.out.println("ssss: "+good.toString());
        if (!dishDesc.equals("")) {
            good.setDesc(dishDesc);
        }
        if (!dishname.equals("")) {
            good.setName(dishname);
        }
        if (!dishprice.equals("")) {
            good.setPrice(Float.parseFloat(dishprice));
        }


        if (menuService.modifyGoods(good)) {
            response.setStatus(200);
            res.put("msg", "OK");
            JSONObject tempjson = new JSONObject();
            JSONObject tempjson1 = new JSONObject();
            tempjson.put("categoryID", tempint);
            tempjson1.put("dishID", tempdishid);
            tempjson1.put("dishName", dishname);
            tempjson1.put("dishPrice", Float.parseFloat(dishprice));
            tempjson1.put("dishImg", good.getImgSrc());
            tempjson1.put("dishDescription", dishDesc);
            tempjson.put("dishInfo", tempjson1);
            res.put("data", tempjson);
            return res.toString();
        } else {
            response.setStatus(200);
            res.put("msg", "没有权限");
            res.put("data","");
            return  res.toString();
        }
    }

    /*查找菜式*/
    @CrossOrigin
    @RequestMapping(value = "/api/*/query/dish", method = RequestMethod.GET)
    @ResponseBody
    public String getDish(@RequestParam(value = "categoryID", defaultValue = "-1") int id,HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("Content-Type:application/json");

        JSONObject res = new JSONObject();
        //System.out.println(id);
        if (id != -1) {
            Category cate = categoryService.getCategoryById(id);
            List<Integer> categoryid = new ArrayList<Integer>();
            categoryid.add(id);
            if (cate != null) {
                response.setStatus(200);
                res.put("msg", "OK");
                List<Goods> dishlist = menuService.getGoodsListByCategory(cate);
                JSONArray templist = new JSONArray();
                JSONObject tempjson = new JSONObject();
                tempjson.put("categoryID", categoryid);
                for (int i = 0; i < dishlist.size(); i++) {
                    Goods good = dishlist.get(i);
                    String dishname = good.getName();
                    float dishprice = good.getPrice();
                    String dishimage = good.getImgSrc();
                    String dishDesc = good.getDesc();
                    //String volume = json.getString("dishVolume"
                    JSONObject tempjson1 = new JSONObject();
                    tempjson1.put("dishID", good.getId());
                    tempjson1.put("dishName", dishname);
                    tempjson1.put("dishPrice", dishprice);
                    tempjson1.put("dishImg", dishimage);
                    tempjson1.put("dishDescription", dishDesc);
                    templist.add(tempjson1);
                }
                tempjson.put("dishInfo", templist);
                res.put("data", tempjson);
                return res.toString();
            } else {
                response.setStatus(404);
                res.put("msg", "not found");
                res.put("data", "");
                return res.toString();
            }
        } else {
            //返回active=1但并未归类的菜
            response.setStatus(200);
            List<Integer> categoryid = new ArrayList<Integer>();
            categoryid.add(-1);
            List<Goods> goodslist1 = menuService.getGoodsListNotInCate();
            //System.out.println("Goodsize "+goodslist1.size());
            JSONArray templist = new JSONArray();
            res.put("msg", "OK");
            for (int i = 0; i < goodslist1.size(); i++) {
                if (goodslist1.get(i).getCate().size() == 0) {
                    Goods good = goodslist1.get(i);
                    String dishname = good.getName();
                    float dishprice = good.getPrice();
                    String dishimage = good.getImgSrc();
                    String dishDesc = good.getDesc();
                    //String volume = json.getString("dishVolume"
                    JSONObject tempjson1 = new JSONObject();
                    tempjson1.put("dishID", good.getId());
                    tempjson1.put("dishName", dishname);
                    tempjson1.put("dishPrice", dishprice);
                    tempjson1.put("dishImg", dishimage);
                    tempjson1.put("dishDescription", dishDesc);
                    templist.add(tempjson1);
                }
            }
            JSONObject tempdata = new JSONObject();
            tempdata.put("categoryID", categoryid);
            tempdata.put("dishInfo", templist);
            res.put("data", tempdata);
            return res.toString();
        }
    }


    /*通过dishID查找菜式*/
    @CrossOrigin
    @RequestMapping(value = "/api/*/query/deletedDish", method = RequestMethod.GET)
    @ResponseBody
    public String queryDishbyID(@RequestParam(value = "dishID", defaultValue = "-1") int dishid,HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("Content-Type:application/json");

        JSONObject res = new JSONObject();
        //
        // System.out.println(dishid);
        Goods good = menuService.getAllGoodsBydishID(dishid);
        res.put("msg","OK");
        JSONObject temp = new JSONObject();
        temp.put("dishName", good.getName());
        temp.put("dishPrice", good.getPrice());
        res.put("data", temp);
        return res.toString();
    }

}