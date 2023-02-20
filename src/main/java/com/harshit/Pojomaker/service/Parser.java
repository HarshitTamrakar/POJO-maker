package com.harshit.Pojomaker.service;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harshit.Pojomaker.exception.ParserException;
import com.harshit.Pojomaker.model.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Parser {

    private final FileMaker fileMaker;
    private final ObjectMapper objectMapper;

    @Autowired
    public Parser(FileMaker fileMaker) {
        this.fileMaker = fileMaker;
        objectMapper = new ObjectMapper();
    }

    public void parseToObject(String jsonString) throws IOException, ParserException {
        System.out.println("JSON" + jsonString);
        Entity parsedEntity = objectMapper.readValue(jsonString, Entity.class);
        fileMaker.makeFile(parsedEntity);
    }

    public void pass(Entity entity) throws IOException, ParserException {
        fileMaker.makeFile(entity);
    }

}
