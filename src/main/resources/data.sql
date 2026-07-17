 -- THIS IS A COMMENT

-- username/password combo for local dev.
-- user user
-- admin admin

INSERT INTO app_user (username, password, role, created_on, version) VALUES ('user', '$2a$10$9eaMGQdwEMMxEaQCef6ELevVvXIoXpXxhUsDuYktgLcVrBV84v/iS', 'ROLE_USER', now(), 0);
INSERT INTO app_user (username, password, role, created_on, version) VALUES ('admin', '$2a$10$BHvRRnFHa4.8CvS8QTAkO.j54y2kdNsVVeXoUsUQvEmM.gSGdX/gq', 'ROLE_ADMIN', now(), 0);
INSERT INTO app_user (username, password, role, created_on, version) VALUES ('testuser', '$2a$10$1cjRCpBnKiByX/SUAWAT8.szqsvPqlRVWBoHzvfh.u/Gf12zyqLEe','ROLE_USER', now(), 0);

INSERT INTO topic (name, app_user_id, created_on, version) VALUES ('topic1', 1, now(), 0);
INSERT INTO topic (name, app_user_id, created_on, version) VALUES ('topic2', 1, now(), 0);
INSERT INTO topic (name, app_user_id, created_on, version) VALUES ('topic3', 1, now(), 0);

INSERT INTO thread (content, app_user_id, topic_id, like_count, created_on, version) VALUES ('topic_1_content1', 1, 1, 0, now(), 0);
INSERT INTO thread (content, app_user_id, topic_id, like_count, created_on, version) VALUES ('topic_1_content2', 1, 1, 0, now(), 0);
INSERT INTO thread (content, app_user_id, topic_id, like_count, created_on, version) VALUES ('topic_1_content3', 1, 1, 0, now(), 0);
INSERT INTO thread (content, app_user_id, topic_id, like_count, created_on, version) VALUES ('topic_1_content4', 1, 1, 0, now(), 0);
INSERT INTO thread (content, app_user_id, topic_id, like_count, created_on, version) VALUES ('topic_1_content5', 1, 1, 0, now(), 0);
INSERT INTO thread (content, app_user_id, topic_id, like_count, created_on, version) VALUES ('topic_1_content6', 1, 1, 0, now(), 0);
INSERT INTO thread (content, app_user_id, topic_id, like_count, created_on, version) VALUES ('topic_1_content7', 1, 1, 0, now(), 0);
INSERT INTO thread (content, app_user_id, topic_id, like_count, created_on, version) VALUES ('topic_1_content8', 1, 1, 0, now(), 0);
INSERT INTO thread (content, app_user_id, topic_id, like_count, created_on, version) VALUES ('topic_1_content9', 1, 1, 0, now(), 0);
INSERT INTO thread (content, app_user_id, topic_id, like_count, created_on, version) VALUES ('topic_1_content10', 1, 1, 0, now(), 0);

INSERT INTO thread (content, app_user_id, topic_id, like_count, created_on, version) VALUES ('content4', 1, 2, 0, now(), 0);
INSERT INTO thread (content, app_user_id, topic_id, like_count, created_on, version) VALUES ('content5', 1, 3, 0, now(), 0);
INSERT INTO thread (content, app_user_id, topic_id, like_count, created_on, version) VALUES ('content6', 1, 3, 0, now(), 0);

