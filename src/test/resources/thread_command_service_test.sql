INSERT INTO app_user (username, password, role, enabled) VALUES ('user1', '$2a$10$o.4VZ6Ixb7PZeHuQX5.5Ve86jaySj8E1kkQIC/r0bCdT5bns5xScS', 'ROLE_USER', TRUE);
INSERT INTO app_user (username, password, role, enabled) VALUES ('user2', '$2a$10$o.4VZ6Ixb7PZeHuQX5.5Ve86jaySj8E1kkQIC/r0bCdT5bns5xScS', 'ROLE_USER', TRUE);

INSERT INTO topic (name, app_user_id) VALUES ('topic1', 1);
INSERT INTO topic (name, app_user_id) VALUES ('topic2', 1);
INSERT INTO topic (name, app_user_id) VALUES ('topic3', 1);

INSERT INTO thread (content, app_user_id, topic_id, like_count, created_on) VALUES ('topic_1_content1', 1, 1, 0, '2020-01-03');
INSERT INTO thread (content, app_user_id, topic_id, like_count, created_on) VALUES ('topic_1_content2', 2, 1, 0, '2020-01-03');

