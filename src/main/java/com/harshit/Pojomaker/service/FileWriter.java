package com.harshit.Pojomaker.service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class FileWriter {

    public FileWriter() {
    }

    public void writeToFile(String fileName, StringBuilder classContents) throws IOException {
        try (BufferedWriter fileWriter = new BufferedWriter(
            new java.io.FileWriter(fileName + ".java"))) {
            fileWriter.write(classContents.toString());
        }
    }

}
