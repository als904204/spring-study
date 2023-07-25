package com.study.springstudy.springSecurity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

        @GetMapping("/formLogin")
        String login() {
            return "formLogin";
        }

}

