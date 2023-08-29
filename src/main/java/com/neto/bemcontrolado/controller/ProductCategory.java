package com.neto.bemcontrolado.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class ProductCategory {
    @GetMapping("/produto")
    public String branch(){
        return "home";
    }
}
