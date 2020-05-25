package com.appleyk.controller;

import com.alibaba.fastjson.JSONObject;
import com.appleyk.dao.Insurance_mysqlDao;
import com.appleyk.node.Insurance;
import com.appleyk.node.Insurance_mysql;
import com.appleyk.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/rest/appleyk")
@RestController
public class ShowController {
    @Autowired
    Insurance_mysqlDao insurance_mysqlDao;

    @Autowired
    InsuranceService insuranceService;

    @RequestMapping(value = "/showbnrs",produces = {"application/json;charset=UTF-8"})
    public String showbnrs(@RequestParam(value="company_id") int company_id){
       List<Insurance_mysql> insurance_mysql =insurance_mysqlDao.findByInsurancecompanyid(company_id);
        JSONObject jsonObject = new JSONObject();
    return JSONObject.toJSONString(insurance_mysql);
    }
    @RequestMapping(value = "/getInByName",produces = {"application/json;charset=UTF-8"})
    public Insurance getInByName(@RequestParam(value="name") String name) {
        return insuranceService.getInByName(name);
    }
}
