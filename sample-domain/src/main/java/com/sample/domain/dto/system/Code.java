package com.sample.domain.dto.system;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.seasar.doma.*;

import com.sample.domain.dto.common.DomaDtoImpl;

import lombok.Getter;
import lombok.Setter;

@Table(name = "code")
@Entity
@Getter
@Setter
public class Code extends DomaDtoImpl {

    private static final long serialVersionUID = 8207242972390517957L;

    // コードID
    @Id
    @Column(name = "code_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // コード分類ID
    @JsonProperty("codeCategoryId")
    @Column(name = "code_category_id")
    Long codeCategoryId;

    // コード分類キー
    @JsonProperty("categoryKey")
    String categoryKey;

    // コード分類名
    @JsonProperty("categoryName")
    String categoryName;

    // コードキー
    @JsonProperty("codeKey")
    String codeKey;

    // コード値
    @JsonProperty("codeValue")
    String codeValue;

    // エイリアス
    @JsonProperty("codeAlias")
    String codeAlias;

    // 属性1
    String attribute1;

    // 属性2
    String attribute2;

    // 属性3
    String attribute3;

    // 属性4
    String attribute4;

    // 属性5
    String attribute5;

    // 属性6
    String attribute6;

    // 表示順
    @JsonProperty("displayOrder")
    Integer displayOrder;

    // 無効フラグ
    @JsonProperty("isInvalid")
    Boolean isInvalid;
}
