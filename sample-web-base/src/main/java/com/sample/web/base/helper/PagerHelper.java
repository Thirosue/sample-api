package com.sample.web.base.helper;

import com.sample.domain.dto.common.DefaultPageable;
import com.sample.web.base.WebConst;

public class PagerHelper {

    public static DefaultPageable setPage(int page, int rows) {
        return new DefaultPageable(page, rows);
    }

    public static DefaultPageable setPage(int page) {
        return new DefaultPageable(page, Integer.valueOf(WebConst.defaultRows));
    }
}
