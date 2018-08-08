package com.sample.web.admin.controller.api.codes;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CodeQuery implements Serializable {

    private static final long serialVersionUID = 7593564324192730932L;

    Long id;

    //コード分類キー
    String categoryKey;

    //コード値
    String codeKey;

    //コードエイリアス
    String codeAlias;

    //無効フラグ
    Boolean isInvalid;
}
