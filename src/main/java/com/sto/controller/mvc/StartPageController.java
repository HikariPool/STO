package com.sto.controller.mvc;

import com.sto.config.security.service.AccessibilityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/store")
public class StartPageController {
    @Autowired
    private AccessibilityServiceImpl accessibilityService;


    @GetMapping("/page")
    public String getStartPage(Model model) {
        model.addAttribute("visibility", accessibilityService.currentUserHasAccess());
        return "start_page";
    }
}
