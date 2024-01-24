package com.sto.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/store")
public class StartPageController {
    @GetMapping("/page")
    public String getStartPage() {
        return "start_page";
    }
}
