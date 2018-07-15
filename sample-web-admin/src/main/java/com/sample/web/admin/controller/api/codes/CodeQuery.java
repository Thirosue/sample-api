package com.sample.web.admin.controller.api.codes;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CodeQuery implements Serializable {

    private static final long serialVersionUID = 7593564324192730932L;

    Long id;

    String categoryKey;

    String categoryName;

    String codeKey;

    String codeValue;

    String codeAlias;

    String attribute1;

    String attribute2;

    String attribute3;

    String attribute4;

    String attribute5;

    String attribute6;

    Integer displayOrder;

    Boolean isInvalid;
}
