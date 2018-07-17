package com.sample.domain.dto.system;

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

    // コード分類キー
    String categoryKey;

    // コード分類名
    String categoryName;

    // コードキー
    String codeKey;

    // コード値
    String codeValue;

    // エイリアス
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
    Integer displayOrder;

    // 無効フラグ
    Boolean isInvalid;
}
