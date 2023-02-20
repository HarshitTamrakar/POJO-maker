package com.harshit.Pojomaker.model;

import java.util.Arrays;
import java.util.Optional;

import lombok.Value;

public interface Content {

    @Value
    public class Statement {
        String statement;
    }

    public enum AccessSpecifier {
        PUBLIC, PRIVATE, PROTECTED;
    }

    public enum ReturnType {
        INT, DOUBLE, FLOAT, STRING, DATE, VOID
    }

    public enum DataType {
        INT, DOUBLE, FLOAT, STRING, DATE, LONG;

        public static Optional<DataType> fromString(String string) {
            return Arrays.stream(DataType.values())
                .filter(value -> value.name().equalsIgnoreCase(string))
                .findFirst();
        }
    }

}
