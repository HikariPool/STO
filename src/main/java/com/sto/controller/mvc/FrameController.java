package com.sto.controller.mvc;

import com.sto.config.security.service.AccessibilityServiceImpl;
import com.sto.data.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/frame")
public class FrameController {
    @Autowired
    private AccessibilityServiceImpl accessibilityService;


    @GetMapping("/{frame}")
    public String get(@PathVariable String frame, Model model) {
        model.addAttribute("visibility", accessibilityService.currentUserHasAccess());
        return Constants.FRAME_PATH + frame;
    }
}
