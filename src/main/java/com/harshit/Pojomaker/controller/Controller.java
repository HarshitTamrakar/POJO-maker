package com.harshit.Pojomaker.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harshit.Pojomaker.exception.ParserException;
import com.harshit.Pojomaker.model.Entity;
import com.harshit.Pojomaker.service.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/build")
public class Controller {

    private final Parser parserService;

    @Autowired
    public Controller(Parser parserService) {
        this.parserService = parserService;
    }


    @GetMapping
    public void getTemplate() {

    }

    @PostMapping
    public void parse(String jsonString) {
        try (BufferedReader externalFileConfig = new BufferedReader(new InputStreamReader(
            Objects.requireNonNull(Entity.class.getClassLoader()
                .getResourceAsStream("demo.json"))))) {
            ObjectMapper objectMapper = new ObjectMapper();
            Entity entity = objectMapper.readValue(externalFileConfig, Entity.class);
            parserService.pass(entity);
        } catch (IOException | ParserException e) {
            System.out.println(e);
        }
    }

}
