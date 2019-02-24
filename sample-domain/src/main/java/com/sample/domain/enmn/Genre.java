package com.sample.domain.enmn;

public enum Genre {

    jpop("Jポップ"),
    edm("EDM"),
    rock("ロック"),
    classic("クラッシック"),
    randb("ソウル・ヒップホップ・R&B"),
    hardrock("ハードロック・メタル"),
    ;

    private final String value;

    public String getValue() {
        return value;
    }

    Genre(String value) {
        this.value = value;
    }
}
