package com.funtl.my.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {
    /*跳转到首页*/
    @RequestMapping(value="main",method= RequestMethod.GET)
    public String main(){
        return "main";
    }
}
