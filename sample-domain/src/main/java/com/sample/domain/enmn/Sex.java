package com.sample.domain.enmn;

public enum Sex {

    male("男"),
    female("女"),
    ;

    private final String value;

    public String getValue() {
        return value;
    }

    Sex(String value) {
        this.value = value;
    }
}
