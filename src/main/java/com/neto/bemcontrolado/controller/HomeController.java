package com.neto.bemcontrolado.controller;

import com.neto.bemcontrolado.model.Branch;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {


    @GetMapping("/")
    public String branch(){
        return "home";
    }
}
