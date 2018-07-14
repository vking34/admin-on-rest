package com.dkt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

//@RestController
@Controller
public class Index {

//    @GetMapping("/")
//    public ModelAndView index(){
//        ModelAndView modelAndView = new ModelAndView("index");
//
//        return modelAndView;
//    }
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("departmentsLink", "/departments");
        return "index";
    }

}
