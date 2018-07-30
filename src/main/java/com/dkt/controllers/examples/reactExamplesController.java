package com.dkt.controllers.examples;

import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
//@RequestMapping("react")
//@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class reactExamplesController {

    @GetMapping("/example1")
    public String example1(Model model){

        System.out.println("getting ex1...");
        return "example1";
    }

    @GetMapping("/router")
    public String router(Model model){
        System.out.println("getting router page...");
        return "router";
    }

    @GetMapping("/hoc")
    public String HOC(Model model){
        System.out.println("getting HOC example...");
        return "react/hoc" ;
    }

    @GetMapping("/departments")
    public String getDepartmentsList(Model model){
        System.out.println("getting react department example...");
        return "react/departments" ;
    }

    @GetMapping("/redux")
    public String getReduxExample(Model model){
        System.out.println("get redux example....");
        return "react/redux_example";
    }

}
