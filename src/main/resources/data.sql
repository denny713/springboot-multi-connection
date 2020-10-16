SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `username` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `access_code` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('denny', 'Denny', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'MGR');

SET FOREIGN_KEY_CHECKS = 1;

DROP TABLE IF EXISTS "public"."akses";
CREATE TABLE "public"."akses" (
  "access_code" varchar(5) COLLATE "pg_catalog"."default" NOT NULL,
  "menu_code" varchar(5) COLLATE "pg_catalog"."default" NOT NULL,
  "description" varchar(50) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of akses
-- ----------------------------
INSERT INTO "public"."akses" VALUES ('ADM', 'TRS', 'Administrasi');
INSERT INTO "public"."akses" VALUES ('BOF', 'MDT', 'Back Office');
INSERT INTO "public"."akses" VALUES ('MGR', 'TRS', 'Manager');
INSERT INTO "public"."akses" VALUES ('MGR', 'MDT', 'Manager');

-- ----------------------------
-- Primary Key structure for table akses
-- ----------------------------
ALTER TABLE "public"."akses" ADD CONSTRAINT "akses_pkey" PRIMARY KEY ("access_code", "menu_code");

DROP TABLE IF EXISTS "public"."menu";
CREATE TABLE "public"."menu" (
  "menu_code" varchar(5) COLLATE "pg_catalog"."default" NOT NULL,
  "description" varchar(50) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO "public"."menu" VALUES ('MDT', 'Master Data');
INSERT INTO "public"."menu" VALUES ('TRS', 'Transaksi');
INSERT INTO "public"."menu" VALUES ('RPT', 'Report');

-- ----------------------------
-- Primary Key structure for table menu
-- ----------------------------
ALTER TABLE "public"."menu" ADD CONSTRAINT "menu_pkey" PRIMARY KEY ("menu_code");

DROP TABLE IF EXISTS "public"."users";
CREATE TABLE "public"."users" (
  "username" varchar(10) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(50) COLLATE "pg_catalog"."default",
  "password" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "access_code" varchar(5) COLLATE "pg_catalog"."default" NOT NULL
)
;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO "public"."users" VALUES ('denny', 'Denny', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'MGR');
INSERT INTO "public"."users" VALUES ('denny1', 'Denny1', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', 'GDG');
INSERT INTO "public"."users" VALUES ('denny2', 'Denny2', '15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225', 'ACC');

-- ----------------------------
-- Primary Key structure for table users
-- ----------------------------
ALTER TABLE "public"."users" ADD CONSTRAINT "user_pkey" PRIMARY KEY ("username");