package com.myintrest.phasect.controllers;

import com.myintrest.phasect.service.CoronoDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    CoronoDataService coronoDataService;
@GetMapping("/")
    public String home(Model model){
    model.addAttribute("locationStats",coronoDataService.getAllstats());

        return "home";
    }
}
