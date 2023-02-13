package com.harshit.Pojomaker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/build")
public class Controller {

    @GetMapping
    public void build() {
        
    }

    @PostMapping
    public void build2() {

    }

}
