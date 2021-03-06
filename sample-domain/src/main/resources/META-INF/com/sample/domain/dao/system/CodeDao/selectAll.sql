SELECT
    c.code_id
    ,cc.category_key
    ,cc.category_name
    ,c.code_key
    ,c.code_value
    ,c.code_alias
    ,c.attribute1
    ,c.attribute2
    ,c.attribute3
    ,c.attribute4
    ,c.attribute5
    ,c.attribute6
    ,c.display_order
    ,c.is_invalid
    ,c.created_by
    ,c.created_at
    ,c.updated_by
    ,c.updated_at
    ,c.deleted_by
    ,c.deleted_at
    ,c.version
FROM
    code c
INNER JOIN code_category cc
ON c.code_category_id = cc.code_category_id
AND cc.deleted_at IS NULL
WHERE
    c.deleted_at IS NULL
/*%if code.id != null */
AND c.code_id = /* code.id */1
/*%end*/
/*%if code.codeKey != null */
AND c.code_key = /* code.codeKey */'01'
/*%end*/
/*%if code.codeValue != null */
AND c.code_value = /* code.codeValue */'男'
/*%end*/
/*%if code.codeAlias != null */
AND c.code_alias = /* code.codeAlias */'male'
/*%end*/
/*%if code.categoryKey != null */
AND cc.category_key = /* code.categoryKey */'GNR0001'
/*%end*/
/*%if code.isInvalid != null */
  /*%if code.isInvalid */
AND c.is_invalid = 1
  /*%else*/
AND c.is_invalid = 0
  /*%end*/
/*%end*/
/*# orderBy */
