package com.flat.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/sign_in")
    public String getSignInPage() {
        return "sign_in";
    }

    @GetMapping("/sign_up")
    public String getSignUpPage() {
        return "sign_up";
    }
}
