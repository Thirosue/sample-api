DELETE FROM session;
INSERT INTO session(id, cookie, info, expires, created_by, created_at) VALUES (1, 'uuid_1', 'info', (NOW() + INTERVAL 1 HOUR) , 'none', NOW());
INSERT INTO session(id, cookie, info, expires, created_by, created_at) VALUES (2, 'uuid_2', 'info', (NOW() - INTERVAL 1 SECOND) , 'none', NOW());