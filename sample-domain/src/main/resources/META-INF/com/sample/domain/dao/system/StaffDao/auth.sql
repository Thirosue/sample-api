SELECT
  /*%expand*/*
FROM
  staffs
WHERE
  deleted_at IS NULL
/*%if email != null */
  AND email = /* email */'aaaa@bbbb.com'
/*%end*/
/*%if password != null */
  AND password = /* password */'passw0rd'
/*%end*/
LIMIT 1