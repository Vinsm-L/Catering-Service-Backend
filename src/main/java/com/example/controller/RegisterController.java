package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.pojo.Manager;
import com.example.pojo.responseObj;
import com.example.service.IManagerService;
import com.example.utils.EncryptUtils;
import com.example.utils.JwtTokenUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录、注册controller
 */
@Controller
@RequestMapping(path = "/api/*/", produces = "application/json;charset=utf-8;")
public class RegisterController {
    @Autowired
    private IManagerService managerService;

    private static final Logger logger = Logger.getLogger(RegisterController.class);

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value="signin", method = RequestMethod.POST)
    public String signIn(@RequestBody String body, HttpServletResponse response,HttpSession session){
        JSONObject jsonObject = JSON.parseObject(body);
        String username = jsonObject.get("username").toString();
        String password = jsonObject.get("password").toString();
        logger.debug("sign in: " + username + " " + password);
        // password为null时，加密方法会出错
        if (null == password) {
            password = "null";
        }
        Manager man = new Manager(username, EncryptUtils.encrypt(password));

        responseObj obj;
        // 登录验证通过
        if (managerService.verifyPassword(man)) {
            logger.debug("login succeed.");
            obj = new responseObj("OK", JSON.toJSONString(man));
            // 生成token, 通过cookie返回客户端
            try {
                String token = JwtTokenUtils.createToken(username);
                Cookie cookie = new Cookie("token", token);
                response.addCookie(cookie);

                session.setAttribute("username", token);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("create token error : " + username);
            }
            response.setStatus(200);
            return JSON.toJSONString(obj);
        } else {    // 验证失败
            logger.debug("login failed.");
            response.setStatus(401);
            obj = new responseObj("wrong password", null);
            return JSON.toJSONString(obj);
        }
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "signup", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String  signUp(@RequestBody String body, HttpServletResponse response) {
        JSONObject jsonObject = JSON.parseObject(body);
        String username = jsonObject.get("username").toString();
        String password = jsonObject.get("password").toString();
        logger.debug("sign up: " + username + " " + password);

        if (null == username || username.length() < 4 || username.length() > 16) {
            response.setStatus(409);
            return JSON.toJSONString(new responseObj("username format error", null));
        }
        if (null == password || password.length() < 6 || password.length() > 16) {
            response.setStatus(409);
            return JSON.toJSONString(new responseObj("password format error", null));
        }
        if (managerService.isUsernameExist(username)) {
            response.setStatus(409);
            return JSON.toJSONString(new responseObj("user already exists", null));
        }
        // 添加用户
        managerService.addUser(new Manager(username, EncryptUtils.encrypt(password)));
        logger.debug("add user: " + username);

        response.setStatus(200);
        // 只传回用户名
        return JSON.toJSONString(new responseObj("OK", JSON.toJSONString(username)));
    }
}
