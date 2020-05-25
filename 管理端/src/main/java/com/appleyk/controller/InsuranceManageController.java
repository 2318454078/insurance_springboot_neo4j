package com.appleyk.controller;

import com.appleyk.Dao.InsuranceDao_mysql;
import com.appleyk.pojo.Insurance_mysql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class InsuranceManageController {
    @Autowired
    InsuranceDao_mysql insuranceDaoMysql;

    @RequestMapping("/admin/insurance/findall")
    public String listInsurance(Model model){
        Collection<Insurance_mysql> list = insuranceDaoMysql.findAll();
        model.addAttribute("list",list);
        return "insurance/list";
    };

    @RequestMapping("/admin/insurance/toadd")
    public String toAdd(){
        return "insurance/add";
    }

    @RequestMapping("/admin/insurance/list")
    //按页数展示
    //在参数里接受当前是第几页 start ，以及每页显示多少条数据 size。 默认值分别是0和5
    public String listInsurance(Model model, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        start = start<0?0:start;                //如果 start 为负，那么修改为0. 这样在首页点击上一页的时候就会停留在首页
        //设置排序方式，ASC是顺序，DESC是倒序
        Sort sort = new Sort(Sort.Direction.ASC,"id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page<Insurance_mysql> page =insuranceDaoMysql.findAll(pageable);
        model.addAttribute("page", page);
        return "admin";
    }
    //添加
    @PostMapping("/admin/insurance/add")
    public String addInsurance(Insurance_mysql insurance_mysql) throws Exception{
        insuranceDaoMysql.saveAndFlush(insurance_mysql);
        return "redirect:/admin/insurance/findall";
    }
    //删除
    @RequestMapping("/admin/insurance/delete/{insurance_id}")
    public String deleteInsurance(@PathVariable("insurance_id") Integer insurance_id) throws Exception{
        insuranceDaoMysql.deleteById(insurance_id);
        return "redirect:/admin/insurance/findall";
    }
    //编辑
    @GetMapping("/admin/insurance/edit/{insurance_id}")
    public String editInsurance(@PathVariable("insurance_id") Integer insurance_id, Model model) throws Exception{
        Insurance_mysql insurance_mysql = insuranceDaoMysql.findById(insurance_id).get();
        model.addAttribute("insurance",insurance_mysql);
        return "insurance/edit";
    }
    //更新
    @RequestMapping("admin/insurance/update")
    public String updateInsurance(Insurance_mysql insurance_mysql) throws Exception{
        insuranceDaoMysql.save(insurance_mysql);
        return "redirect:/admin/insurance/findall";
    }

}
