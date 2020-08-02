--INSERT INTO role (name) VALUES ('ROLE_USER');
--INSERT INTO role (name) VALUES ('ROLE_ADMIN');

INSERT INTO app_user (username, password, role, enabled) VALUES ('user1', '$2a$10$o.4VZ6Ixb7PZeHuQX5.5Ve86jaySj8E1kkQIC/r0bCdT5bns5xScS', 'ROLE_USER', TRUE);

INSERT INTO topic (name, app_user_id) VALUES ('topic1', 1);
INSERT INTO topic (name, app_user_id) VALUES ('topic2', 1);
INSERT INTO topic (name, app_user_id) VALUES ('topic3', 1);

INSERT INTO thread (content, app_user_id, topic_id, like_count, created_on) VALUES ('topic_1_content1', 1, 1, 0, '2020-01-03');
INSERT INTO thread (content, app_user_id, topic_id, like_count, created_on) VALUES ('topic_1_content2', 1, 1, 0, '2020-01-03');
INSERT INTO thread (content, app_user_id, topic_id, like_count, created_on) VALUES ('topic_1_content3', 1, 1, 0, '2020-01-03');
INSERT INTO thread (content, app_user_id, topic_id, like_count, created_on) VALUES ('topic_1_content4', 1, 1, 0, '2020-01-03');
INSERT INTO thread (content, app_user_id, topic_id, like_count, created_on) VALUES ('topic_1_content5', 1, 1, 0, '2020-01-03');
INSERT INTO thread (content, app_user_id, topic_id, like_count, created_on) VALUES ('topic_1_content6', 1, 1, 0, '2020-01-03');
INSERT INTO thread (content, app_user_id, topic_id, like_count, created_on) VALUES ('topic_1_content7', 1, 1, 0, '2020-01-03');
INSERT INTO thread (content, app_user_id, topic_id, like_count, created_on) VALUES ('topic_1_content8', 1, 1, 0, '2020-01-03');
INSERT INTO thread (content, app_user_id, topic_id, like_count, created_on) VALUES ('topic_1_content9', 1, 1, 0, '2020-01-03');
INSERT INTO thread (content, app_user_id, topic_id, like_count, created_on) VALUES ('topic_1_content10', 1, 1, 0, '2020-01-03');

INSERT INTO thread (content, app_user_id, topic_id, like_count, created_on) VALUES ('content4', 1, 2, 0, '2020-01-03');
INSERT INTO thread (content, app_user_id, topic_id, like_count, created_on) VALUES ('content5', 1, 3, 0, '2020-01-03');
INSERT INTO thread (content, app_user_id, topic_id, like_count, created_on) VALUES ('content6', 1, 3, 0, '2020-01-03');