package com.harshit.Pojomaker.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ClassContent implements Content {

    @Builder.Default
    Statement packageStatement = null;
    @Builder.Default
    List<Statement> importStatements = new ArrayList<>();
    @Builder.Default
    String className = null;
    @Builder.Default
    List<VariableContent> variables = new ArrayList<>();
    @Builder.Default
    List<FunctionContent> functions = new ArrayList<>();
    @Builder.Default
    List<ClassContent> innerClasses = new ArrayList<>();

    public static enum Annotation {
        DATA, VALUE, BUILDER, REQUEST_MAPPING;

        public static Optional<Annotation> fromString(String string) {
            return Arrays.stream(Annotation.values())
                .filter(value -> value.name().equalsIgnoreCase(string))
                .findFirst();
        }
    }
}
