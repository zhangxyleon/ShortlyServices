
CREATE TABLE `url` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `long_url` VARCHAR(255) NOT NULL,
  `short_url` VARCHAR(255) NOT NULL,
   PRIMARY KEY (`id`)
 )ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;