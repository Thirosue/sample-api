package com.sample.web.admin.controller.api.inquiry;

import lombok.Getter;
import lombok.Setter;
import lombok.val;

@Getter
@Setter
public class Option {

    String value;

    String text;

    public static Option create(String value, String text) {
        val option = new Option();
        option.value = value;
        option.text = text;
        return option;
    }
}
