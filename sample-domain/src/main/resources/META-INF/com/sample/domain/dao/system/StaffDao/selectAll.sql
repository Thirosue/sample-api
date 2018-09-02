SELECT
  /*%expand*/*
FROM
  staffs
WHERE
  deleted_at IS NULL
/*%if staff.id != null */
  AND staff_id = /* staff.id */1
/*%end*/
/*%if staff.firstName != null */
  AND first_name LIKE /* @infix(staff.firstName) */'john' ESCAPE '$'
/*%end*/
/*%if staff.lastName != null */
  AND last_name LIKE /* @infix(staff.lastName) */'doe' ESCAPE '$'
/*%end*/
/*%if staff.tel != null */
  AND tel LIKE /* @infix(staff.tel) */'09012345678' ESCAPE '$'
/*%end*/
/*%if staff.email != null */
  AND email LIKE /* @infix(staff.email) */'aaaa@bbbb.com' ESCAPE '$'
/*%end*/
/*%if staff.tel != null */
  AND tel LIKE /* @infix(staff.tel) */'09012345678' ESCAPE '$'
/*%end*/
/*# orderBy */
