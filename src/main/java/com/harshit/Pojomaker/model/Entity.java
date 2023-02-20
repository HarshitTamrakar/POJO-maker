package com.harshit.Pojomaker.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class Entity {

    List<ClassEntity> classes;

    @Value
    @Builder
    @Jacksonized
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ClassEntity {
        String type;
        String name;
        @Builder.Default
        List<VariableEntity> variables = new ArrayList<>();
        @Builder.Default
        List<ClassEntity> innerClass = new ArrayList<>();

        @Value
        @Builder
        @Jacksonized
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class VariableEntity {
            String type;
            String name;
            String defaultValue;
        }
    }
}
