package com.appleyk.controller;

import com.appleyk.Dao.CompanyDao_mysql;
import com.appleyk.pojo.Company_mysql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class CompanyManageController {
    @Autowired
    CompanyDao_mysql companyDao_mysql;

    @RequestMapping("/admin/company/findall")
    public String listCompany(Model model){
        Collection<Company_mysql> list = companyDao_mysql.findAll();
        model.addAttribute("list",list);
        return "company/list";
    };

    @RequestMapping("/admin/company/toadd")
    public String toAdd(){
        return "company/add";
    }


    //添加
    @PostMapping("/admin/company/add")
    public String addCompany(Company_mysql company_mysql) throws Exception{
        companyDao_mysql.saveAndFlush(company_mysql);
        return "redirect:/admin/company/findall";
    }
    //删除
    @RequestMapping("/admin/company/delete/{company_id}")
    public String deletecompany(@PathVariable("company_id") Integer company_id) throws Exception{
        companyDao_mysql.deleteById(company_id);
        return "redirect:/admin/company/findall";
    }
    //编辑
    @GetMapping("/admin/company/edit/{company_id}")
    public String editcompany(@PathVariable("company_id") Integer company_id, Model model) throws Exception{
        Company_mysql company_mysql = companyDao_mysql.findById(company_id).get();
        model.addAttribute("company",company_mysql);
        return "company/edit";
    }
    //更新
    @RequestMapping("admin/company/update")
    public String updatecompany(Company_mysql company_mysql) throws Exception{
        companyDao_mysql.save(company_mysql);
        return "redirect:/admin/company/findall";
    }

}
