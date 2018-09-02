package com.sample.domain.util;

import com.google.common.base.CaseFormat;
import com.sample.domain.dto.common.DomaDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.seasar.doma.Column;
import org.seasar.doma.jdbc.SelectOptions;
import lombok.val;

import com.sample.domain.dto.common.Pageable;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Doma関連ユーティリティ
 */
public class DomaUtils {

    /**
     * SearchOptionsを作成して返します。
     *
     * @return
     */
    public static SelectOptions createSelectOptions() {
        return SelectOptions.get();
    }

    /**
     * SearchOptionsを作成して返します。
     *
     * @param pageable
     * @return
     */
    public static SelectOptions createSelectOptions(Pageable pageable) {
        int page = pageable.getPage();
        int perpage = pageable.getPerpage();
        return createSelectOptions(page, perpage);
    }

    /**
     * SearchOptionsを作成して返します。
     *
     * @param page
     * @param perpage
     * @return
     */
    public static SelectOptions createSelectOptions(int page, int perpage) {
        int offset = (page - 1) * perpage;
        return SelectOptions.get().offset(offset).limit(perpage);
    }

    /**
     * Sort文字列を作成して返します。
     *
     * @param domaDto
     * @return
     */
    public static String createSortString(DomaDto domaDto) {
        if(StringUtils.isEmpty(domaDto.getSort())) {
            return StringUtils.EMPTY;
        }

        val sortParam = Arrays.asList(domaDto.getSort().split(","));
        val columnList = sortParam.stream().map(str->getColumnName(str.trim().split(" ")[0], domaDto.getClass())).collect(Collectors.toList());
        val orderList = sortParam.stream().map(str->str.trim().split(" ")[1]).collect(Collectors.toList());

        val sortClause = new StringJoiner(",");
        IntStream.range(0, orderList.size()).forEach(i->{
            val orderClause = new StringJoiner(" ");
            orderClause.add(columnList.get(i));
            orderClause.add(orderList.get(i));
            sortClause.add(orderClause.toString());
        });

        return "ORDER BY " + sortClause.toString();
    }

    private static String getColumnName(String sortParam, Class<?> dtoClass) {
        val field = FieldUtils.getField(dtoClass, sortParam,true);
        val column = field.getAnnotation(Column.class);
        return column != null ? column.name() : CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName());
    }
}
