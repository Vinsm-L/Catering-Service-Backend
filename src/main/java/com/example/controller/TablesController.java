package com.example.controller;

import com.example.pojo.Tables;
import com.example.service.ITablesService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(produces = "application/json;charset=utf-8;")
public class TablesController {
    @Autowired
    private ITablesService tablesService;
    @CrossOrigin
    @RequestMapping(value = "/api/*/add/table", method = RequestMethod.GET)
    @ResponseBody
    public String addTable(@RequestParam(value = "tableID", defaultValue = "-1") int tableid, HttpServletRequest request, HttpServletResponse response) {
        JSONObject res = new JSONObject();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("Content-Type:application/json");

        Tables table = new Tables(tableid, 6, 0);
        if (tablesService.addTables(table)) {
            res.put("msg", "OK");
            JSONObject temp = new JSONObject();
            temp.put("tableID", tableid);
            res.put("data", temp);
        } else {
            res.put("msg", "没有权限");
            res.put("data", "");
        }
        return res.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/api/*/delete/table", method = RequestMethod.GET)
    @ResponseBody
    public String deleteOrder(@RequestParam(value = "tableID", defaultValue = "-1") int tableid, HttpServletRequest request, HttpServletResponse response) {
        JSONObject res = new JSONObject();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("Content-Type:application/json");

        //Tables table = new Tables(tableid, 6, 0);
        if (tablesService.deleteTableByNumber(tableid)) {
            res.put("msg", "OK");
            JSONObject temp = new JSONObject();
            temp.put("tableID", tableid);
            res.put("data", temp);
        } else {
            res.put("msg", "没有权限");
            res.put("data", "");
        }
        return res.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/api/*/query/table", method = RequestMethod.GET)
    @ResponseBody
    public String queryTable(HttpServletRequest request, HttpServletResponse response) {
        JSONObject res = new JSONObject();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("Content-Type:application/json");
        response.setStatus(200);
        res.put("msg", "OK");
        List<Tables> tablelist = tablesService.getTablesList();
        List<Integer> tablenumberlist = new ArrayList<Integer>();
        for (int i = 0; i < tablelist.size(); i++){
            tablenumberlist.add(tablelist.get(i).getNumber());
        }
        JSONObject temp = new JSONObject();
        temp.put("tableID",tablenumberlist);
        res.put("data", temp);
        return res.toString();
    }

}
