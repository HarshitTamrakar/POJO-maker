package com.harshit.Pojomaker.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FunctionContent implements Content{

    @Builder.Default
    public ClassContent.AccessSpecifier accessSpecifier = ClassContent.AccessSpecifier.PUBLIC;
    @Builder.Default
    public boolean isStatic = false;
    @Builder.Default
    public ClassContent.ReturnType returnType = ClassContent.ReturnType.VOID;
    public String name;
    @Builder.Default
    public List<ClassContent.Statement> statements = new ArrayList<>();
    @Builder.Default
    public Annotation annotation = null;

    public static enum Annotation {
        GET_MAPPING, POST_MAPPING, PUT_MAPPING, DELETE_MAPPING
    }
}
