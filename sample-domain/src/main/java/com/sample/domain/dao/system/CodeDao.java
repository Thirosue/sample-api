package com.sample.domain.dao.system;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;

import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

import com.sample.domain.dto.system.Code;

@ConfigAutowireable
@Dao
public interface CodeDao {

    /**
     * コードを取得します。
     * 
     * @param code
     * @param options
     * @return
     */
    @Select(strategy = SelectType.COLLECT)
    <R> R selectAll(final Code code, final SelectOptions options, final Collector<Code, ?, R> collector);

    /**
     * コードを1件取得します。
     *
     * @param id
     * @return
     */
    @Select
    Optional<Code> selectById(Long id);

    /**
     * コードを1件取得します。
     *
     * @param codeKey
     * @return
     */
    @Select
    Optional<Code> selectByKey(String codeKey);

    /**
     * コードを1件取得します。
     *
     * @param code
     * @return
     */
    @Select
    Optional<Code> select(Code code);

    /**
     * コードを登録します。
     *
     * @param Code
     * @return
     */
    @Insert
    int insert(Code Code);

    /**
     * コードを更新します。
     *
     * @param code
     * @return
     */
    @Update(exclude = { "categoryKey", "categoryName", "codeKey", "codeAlias", "attribute1", "attribute2", "attribute3",
            "attribute4", "attribute5", "attribute6", "isInvalid" })
    int update(Code code);

    /**
     * コードを論理削除します。
     *
     * @param code
     * @return
     */
    @Update(excludeNull = true) // NULLの項目は更新対象にしない
    int delete(Code code);

    /**
     * コードを一括登録します。
     *
     * @param codes
     * @return
     */
    @BatchInsert
    int[] insert(List<Code> codes);
}
