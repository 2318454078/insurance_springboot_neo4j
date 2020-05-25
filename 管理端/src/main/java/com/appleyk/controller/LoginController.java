package com.appleyk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
@RequestMapping("/login")
    public String Login(@RequestParam("username") String username, @RequestParam("pwd") String pwd, Model model){
    if("admin".equals(username)&&"admin".equals(pwd)){
        return "redirect:admin";
    }else {
        model.addAttribute("msg","用户名或密码错误");
        return "index";
    }
    }
}
