INSERT INTO app_user (username, password, role, enabled) VALUES ('user1', 'user1', '', TRUE);

INSERT INTO topic (name, app_user_id) VALUES ('topic1', 1);
INSERT INTO topic (name, app_user_id) VALUES ('topic2', 1);
INSERT INTO topic (name, app_user_id) VALUES ('topic3', 1);

INSERT INTO thread (content, app_user_id, topic_id, like_count) VALUES ('content1', 1, 1, 0);
INSERT INTO thread (content, app_user_id, topic_id, like_count) VALUES ('content2', 1, 1, 0);
INSERT INTO thread (content, app_user_id, topic_id, like_count) VALUES ('content3', 1, 1, 0);