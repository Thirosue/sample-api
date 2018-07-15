SELECT
  /*%expand*/*
FROM
  session
WHERE
  deleted_at IS NULL
/*%if session.cookie != null */
  AND cookie = /* session.cookie */'uuid'
/*%end*/
/*%if session.expires != null */
  AND /* session.expires */'18-04-11 05:49:07.000000000' < expires
/*%end*/
LIMIT 1