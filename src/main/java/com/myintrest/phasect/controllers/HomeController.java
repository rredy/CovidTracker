package com.myintrest.phasect.controllers;

import com.myintrest.phasect.model.LocationStats;
import com.myintrest.phasect.service.CoronoDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CoronoDataService coronoDataService;
@GetMapping("/")
    public String home(Model model){
    List<LocationStats> allSats=coronoDataService.getAllstats();
    int totalcases=allSats.stream().mapToInt(stat->stat.getLatestTotalCases()).sum();
    int totalNewcases=allSats.stream().mapToInt(stat->stat.getDiffFromPrevious()).sum();
    model.addAttribute("locationStats",allSats);
    model.addAttribute("totalReportedCases",totalcases);
    model.addAttribute("previousday",totalNewcases);


        return "home";
    }
}
