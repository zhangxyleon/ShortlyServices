-- ----------------------------
-- Table structure for LONG_SEQUENCE_ID
-- ----------------------------
DROP TABLE IF EXISTS url_base62;

CREATE TABLE url_base62(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    sequence_id BIGINT NOT NULL,
    long_url VARCHAR(256) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
