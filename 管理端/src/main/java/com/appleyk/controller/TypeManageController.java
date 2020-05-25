package com.appleyk.controller;

import com.appleyk.Dao.TypeDao_mysql;
import com.appleyk.pojo.Type_mysql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class TypeManageController {
    @Autowired
    TypeDao_mysql typeDao_mysql;

    @RequestMapping("/admin/type/findall")
    public String listType(Model model){
        Collection<Type_mysql> list = typeDao_mysql.findAll();
        model.addAttribute("list",list);
        return "type/list";
    };

    @RequestMapping("/admin/type/toadd")
    public String toAdd(){
        return "type/add";
    }


    //添加
    @PostMapping("/admin/type/add")
    public String addType(Type_mysql type_mysql) throws Exception{
        typeDao_mysql.saveAndFlush(type_mysql);
        return "redirect:/admin/type/findall";
    }
    //删除
    @RequestMapping("/admin/type/delete/{type_id}")
    public String deletetype(@PathVariable("type_id") Integer type_id) throws Exception{
        typeDao_mysql.deleteById(type_id);
        return "redirect:/admin/type/findall";
    }
    //编辑
    @GetMapping("/admin/type/edit/{type_id}")
    public String edittype(@PathVariable("type_id") Integer type_id, Model model) throws Exception{
       Type_mysql type_mysql = typeDao_mysql.findById(type_id).get();
        model.addAttribute("type",type_mysql);
        return "type/edit";
    }
    //更新
    @RequestMapping("admin/type/update")
    public String updatetype(Type_mysql type_mysql) throws Exception{
        typeDao_mysql.save(type_mysql);
        return "redirect:/admin/type/findall";
    }

}
