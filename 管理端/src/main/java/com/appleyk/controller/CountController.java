package com.appleyk.controller;

import com.alibaba.fastjson.JSONObject;
import com.appleyk.Dao.InsuranceDao_mysql;
import com.appleyk.pojo.Insurance_mysql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class CountController {
    @Autowired
    InsuranceDao_mysql insuranceDao_mysql;
    @RequestMapping("/count")
    public String Count(){
        List<Insurance_mysql> list1=insuranceDao_mysql.findByInsurancecompanyid(1);
        List<Insurance_mysql> list2=insuranceDao_mysql.findByInsurancecompanyid(2);
        List<Insurance_mysql> list3=insuranceDao_mysql.findByInsurancecompanyid(3);
        Map map = new HashMap();
        map.put("count1",list1.size());
        map.put("count2",list2.size());
        map.put("count3",list3.size());
    return JSONObject.toJSONString(map);
    }
}
