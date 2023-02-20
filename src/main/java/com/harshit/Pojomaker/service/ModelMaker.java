package com.harshit.Pojomaker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.harshit.Pojomaker.exception.ParserException;
import com.harshit.Pojomaker.model.ClassContent;
import com.harshit.Pojomaker.model.Content;
import com.harshit.Pojomaker.model.Entity;
import com.harshit.Pojomaker.model.VariableContent;
import com.harshit.Pojomaker.utils.ModelMakerUtility;
import org.springframework.stereotype.Service;

@Service
public class ModelMaker {

    public List<ClassContent> makeFile(Entity parsedEntity) throws ParserException {
        List<ClassContent> classContents = new ArrayList<>();
        for (Entity.ClassEntity classEntity : parsedEntity.getClasses()) {
            ClassContent classContent = getClassContent(classEntity);
            if (Objects.nonNull(classContent)) {
                classContents.add(classContent);
            }
        }
        return classContents;
    }

    private ClassContent getClassContent(Entity.ClassEntity classEntity) throws ParserException {
        ClassContent.ClassContentBuilder classBuilder = ClassContent.builder();
        classBuilder.className(classEntity.getName());
        List<VariableContent> variables = new ArrayList<>();
        List<ClassContent> innerClasses = new ArrayList<>();
        for (Entity.ClassEntity.VariableEntity variable : classEntity.getVariables()) {
            VariableContent variableContent = getVariableContent(variable);
            if (Objects.nonNull(variableContent)) {
                variables.add(variableContent);
            }
        }
        for (Entity.ClassEntity innerClass : classEntity.getInnerClass()) {
            ClassContent innerClassContent = getClassContent(innerClass);
            if (Objects.nonNull(innerClassContent)) {
                innerClasses.add(innerClassContent);
            }
        }
        classBuilder.innerClasses(innerClasses)
            .variables(variables);
        return classBuilder.build();
    }

    private VariableContent getVariableContent(Entity.ClassEntity.VariableEntity variable) {
        if (Objects.isNull(variable)) {
            return null;
        }
        VariableContent.VariableContentBuilder variableContentBuilder = VariableContent.builder();
        if (variable.getType().equalsIgnoreCase("Class")) {
            variableContentBuilder.name(ModelMakerUtility.getClassName(variable.getName()));
        } else {
            variableContentBuilder.name(variable.getName());
        }
        Optional<Content.DataType> dataTypeOptional = Content.DataType.fromString(variable.getType());
        dataTypeOptional.ifPresent(variableContentBuilder::dataType);
        return variableContentBuilder.build();
    }

}
