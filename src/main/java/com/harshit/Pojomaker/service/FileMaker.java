package com.harshit.Pojomaker.service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import com.harshit.Pojomaker.exception.ParserException;
import com.harshit.Pojomaker.model.ClassContent;
import com.harshit.Pojomaker.model.Entity;
import com.harshit.Pojomaker.model.VariableContent;
import org.springframework.stereotype.Service;

@Service
public class FileMaker {

    private static final String BLANK_SPACE_CHARACTER = " ";
    private static final String SEMI_COLON = ";";
    private final ModelMaker modelMaker;
    private final FileWriter fileWriter;
    private static final String NEW_LINE_CHARACTER = "\n";

    public FileMaker(ModelMaker modelMaker, FileWriter fileWriter) {
        this.modelMaker = modelMaker;
        this.fileWriter = fileWriter;
    }

    public void makeFile(Entity parsedEntity) throws IOException, ParserException {
        List<ClassContent> classContents = modelMaker.makeFile(parsedEntity);
        for (ClassContent classContent : classContents) {
            StringBuilder clas = getClassFileContent(classContent);
            fileWriter.writeToFile(classContent.getClassName(), clas);
        }
    }

    private StringBuilder getClassFileContent(ClassContent classContent) {
        StringBuilder classFileContent = new StringBuilder();
        appendStatement(classContent.getPackageStatement(), classFileContent);
        classContent.getImportStatements()
            .forEach(statement -> appendStatement(statement, classFileContent));
        appendClassDefinition(classContent, classFileContent);
        return classFileContent;
    }

    private void appendClassDefinition(ClassContent classContent, StringBuilder classFileContent) {
        classFileContent.append("public ")
            .append("class ")
            .append(classContent.getClassName())
            .append("{").append(NEW_LINE_CHARACTER);
        classContent.getVariables()
            .forEach(variable -> append(variable, classFileContent));
        classContent.getInnerClasses()
            .forEach(innerClass -> appendClassDefinition(innerClass, classFileContent));
        classFileContent.append("}");
    }

    private void append(VariableContent variableContent, StringBuilder classFileContent) {
        if (Objects.nonNull(variableContent.getAnnotation())) {
            classFileContent.append("@")
                .append(variableContent.getAnnotation().name());
            classFileContent.append(NEW_LINE_CHARACTER);
        }
        if (Objects.nonNull(variableContent.getDataType()) && Objects.nonNull(
            variableContent.getName())) {
            classFileContent.append(variableContent.getDataType())
                .append(BLANK_SPACE_CHARACTER)
                .append(variableContent.getName())
                .append(SEMI_COLON);
        }
    }

    private void appendStatement(ClassContent.Statement packageStatement,
                                 StringBuilder classFileContent) {
        if (Objects.isNull(packageStatement)) {
            return;
        }
        classFileContent.append(packageStatement.getStatement())
            .append(SEMI_COLON)
            .append(NEW_LINE_CHARACTER);
    }
}
