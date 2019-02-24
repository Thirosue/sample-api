package com.sample.domain.enmn;

public enum Category {

    service("サービスについて"),
    ticket("チケットについて"),
    etc("その他（タイトルにご記入ください）"),
    ;

    private final String value;

    public String getValue() {
        return value;
    }

    Category(String value) {
        this.value = value;
    }
}
