INSERT INTO media (name, site_name, length, status, thumbnail, aws_bucket_whole_media, aws_storage_name_whole_media) VALUES ('24ur novice', "24ur", 60, 0, "http://localhost:8000", "media_thumbnail", "24ur_novice_123");

INSERT INTO media (name, site_name, length, status, thumbnail, aws_bucket_whole_media, aws_storage_name_whole_media) VALUES ('24ur zvecer', "24ur", 90, 1, "http://localhost:8000", "media_thumbnail_zvecer", "24ur_zvecer_123");

INSERT INTO media (name, site_name, length, status, thumbnail, aws_bucket_whole_media, aws_storage_name_whole_media) VALUES ('24ur inspešpektor', "24ur", 30, 0, "http://localhost:8000", "media_thumbnail_ispektor", "24ur_inspektor_123");

INSERT INTO media_keywords (keyword, fk_media_id) VALUES ("24ur", 1)
INSERT INTO media_keywords (keyword, fk_media_id) VALUES ("novice", 1)
INSERT INTO media_keywords (keyword, fk_media_id) VALUES ("virus", 1)
INSERT INTO media_keywords (keyword, fk_media_id) VALUES ("studio", 1)
INSERT INTO media_keywords (keyword, fk_media_id) VALUES ("zvecer", 2)
INSERT INTO media_keywords (keyword, fk_media_id) VALUES ("novice", 2)
INSERT INTO media_keywords (keyword, fk_media_id) VALUES ("inspektor", 3)
INSERT INTO media_keywords (keyword, fk_media_id) VALUES ("novice", 3)
INSERT INTO media_keywords (keyword, fk_media_id) VALUES ("sobota", 3)