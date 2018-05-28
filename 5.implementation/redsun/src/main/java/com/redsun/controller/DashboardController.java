package com.redsun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DashboardController { 

    @RequestMapping("/dashboard")
    public String dashboard(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "dashboard";
    }
    
    @RequestMapping("/customer")
    public String customer(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "customer";
    }
    
    @RequestMapping("/milestone")
    public String milestone(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "milestone";
    }
    @RequestMapping("/priority")
    public String priority(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "Prioritys";
    }
    @RequestMapping("/risk")
    public String risk(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "Risks";
    }
}
