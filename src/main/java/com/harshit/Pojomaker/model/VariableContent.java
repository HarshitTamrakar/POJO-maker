package com.harshit.Pojomaker.model;


import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class VariableContent implements Content {
    @Builder.Default
    ClassContent.AccessSpecifier accessSpecifier = ClassContent.AccessSpecifier.PRIVATE;
    @Builder.Default
    Annotation annotation = null;
    @Builder.Default
    ClassContent.DataType dataType = null;
    @Builder.Default
    String name = null;
    @Builder.Default
    String value = null;

    public static enum Annotation {
        DEFAULT
    }
}