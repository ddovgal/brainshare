-- @formatter:off
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0
;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0
;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES'
;

-- -----------------------------------------------------
-- Schema brainshare_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema brainshare_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `brainshare_db` 
;
USE `brainshare_db` 
;

-- -----------------------------------------------------
-- Table `brainshare_db`.`object_storage_file`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `brainshare_db`.`object_storage_file` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `bucket` VARCHAR(255) NOT NULL,
  `in_bucket_file_id` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
;


-- -----------------------------------------------------
-- Table `brainshare_db`.`user_status`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `brainshare_db`.`user_status` (
  `id` INT UNSIGNED NOT NULL,
  `status_name` VARCHAR(50) NOT NULL,
  `description` TEXT NOT NULL,
  `low_border` DOUBLE NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `status_name_UNIQUE` (`status_name` ASC))
ENGINE = InnoDB
;


-- -----------------------------------------------------
-- Table `brainshare_db`.`user`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `brainshare_db`.`user` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(50) NULL,
  `last_name` VARCHAR(50) NULL,
  `avatar` BIGINT(20) UNSIGNED NULL,
  `email` VARCHAR(255) NOT NULL,
  `login` VARCHAR(50) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `registration_date` DATE NOT NULL,
  `social_links_json` JSON NOT NULL,
  `ratio` DOUBLE NOT NULL DEFAULT -2147483647,
  `penaly_count` BIT(3) NOT NULL DEFAULT 0,
  `status_id` INT UNSIGNED NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC),
  INDEX `fk_avatar_idx` (`avatar` ASC),
  INDEX `fk_status_idx` (`status_id` ASC),
  INDEX `fk_login_idx` (`login` ASC),
  UNIQUE INDEX `avatar_UNIQUE` (`avatar` ASC),
  CONSTRAINT `fk_user_avatar_id`
    FOREIGN KEY (`avatar`)
    REFERENCES `brainshare_db`.`object_storage_file` (`id`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_status_id`
    FOREIGN KEY (`status_id`)
    REFERENCES `brainshare_db`.`user_status` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB
;


-- -----------------------------------------------------
-- Table `brainshare_db`.`material_state`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `brainshare_db`.`material_state` (
  `id` INT UNSIGNED NOT NULL,
  `state_name` VARCHAR(50) NOT NULL,
  `description` TEXT NOT NULL,
  `low_border` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `state_UNIQUE` (`state_name` ASC))
ENGINE = InnoDB
;


-- -----------------------------------------------------
-- Table `brainshare_db`.`material_format`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `brainshare_db`.`material_format` (
  `id` INT UNSIGNED NOT NULL,
  `format_name` VARCHAR(45) NOT NULL,
  `description` TEXT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `format_name_UNIQUE` (`format_name` ASC))
ENGINE = InnoDB
;


-- -----------------------------------------------------
-- Table `brainshare_db`.`section`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `brainshare_db`.`section` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `super_section_id` INT UNSIGNED NULL,
  `section_name` VARCHAR(255) NOT NULL,
  `description` TEXT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_possible_super_section_id_idx` (`super_section_id` ASC),
  CONSTRAINT `fk_section_possible_super_section_id`
    FOREIGN KEY (`super_section_id`)
    REFERENCES `brainshare_db`.`section` (`id`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB
;


-- -----------------------------------------------------
-- Table `brainshare_db`.`course`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `brainshare_db`.`course` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `creator_user_id` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0,
  `materials_inside_count` SMALLINT UNSIGNED NOT NULL DEFAULT 0,
  `description` TEXT NULL,
  `section_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_creator_user_id_idx` (`creator_user_id` ASC),
  INDEX `fk_section_id_idx` (`section_id` ASC),
  CONSTRAINT `fk_course_creator_user_id`
    FOREIGN KEY (`creator_user_id`)
    REFERENCES `brainshare_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_course_section_id`
    FOREIGN KEY (`section_id`)
    REFERENCES `brainshare_db`.`section` (`id`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB
;


-- -----------------------------------------------------
-- Table `brainshare_db`.`content_data`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `brainshare_db`.`content_data` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `data_json` JSON NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
;


-- -----------------------------------------------------
-- Table `brainshare_db`.`material`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `brainshare_db`.`material` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `author_user_id` BIGINT(20) UNSIGNED NOT NULL,
  `ratio` INT NOT NULL DEFAULT 0,
  `has_low_priority` TINYINT(1) NOT NULL,
  `positive_mark_count` MEDIUMINT UNSIGNED NOT NULL DEFAULT 0,
  `negative_mark_count` MEDIUMINT UNSIGNED NOT NULL DEFAULT 0,
  `state_id` INT UNSIGNED NOT NULL DEFAULT 1,
  `approval_value` MEDIUMINT UNSIGNED NOT NULL,
  `creation_datetime` DATETIME NOT NULL,
  `format_id` INT UNSIGNED NOT NULL,
  `course_id` BIGINT(20) UNSIGNED NULL,
  `section_id` INT UNSIGNED NOT NULL,
  `tags_separated_by_comma` TEXT NOT NULL,
  `preview_content_data_json` JSON NOT NULL,
  `content_data_id` BIGINT(20) UNSIGNED NOT NULL,
  `view_count` MEDIUMINT UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_author_user_id_idx` (`author_user_id` ASC),
  INDEX `fk_state_id_idx` (`state_id` ASC),
  INDEX `fk_format_id_idx` (`format_id` ASC),
  INDEX `fk_possible_course_id_idx` (`course_id` ASC),
  FULLTEXT INDEX `ftx_tags_separated_by_comma` (`tags_separated_by_comma` ASC),
  UNIQUE INDEX `content_data_UNIQUE` (`content_data_id` ASC),
  INDEX `fk_material_material_section_id_idx` (`section_id` ASC),
  CONSTRAINT `fk_material_author_user_id`
    FOREIGN KEY (`author_user_id`)
    REFERENCES `brainshare_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_material_state_id`
    FOREIGN KEY (`state_id`)
    REFERENCES `brainshare_db`.`material_state` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_material_format_id`
    FOREIGN KEY (`format_id`)
    REFERENCES `brainshare_db`.`material_format` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_material_possible_course_id`
    FOREIGN KEY (`course_id`)
    REFERENCES `brainshare_db`.`course` (`id`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_material_material_content_data_id`
    FOREIGN KEY (`content_data_id`)
    REFERENCES `brainshare_db`.`content_data` (`id`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_material_material_section_id`
    FOREIGN KEY (`section_id`)
    REFERENCES `brainshare_db`.`section` (`id`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB
;


-- -----------------------------------------------------
-- Table `brainshare_db`.`commentary`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `brainshare_db`.`commentary` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `author_user_id` BIGINT(20) UNSIGNED NOT NULL,
  `material_id` BIGINT(20) UNSIGNED NOT NULL,
  `parent_commentary_id` BIGINT(20) UNSIGNED NULL,
  `ratio` INT NOT NULL DEFAULT 0,
  `positive_mark_count` MEDIUMINT UNSIGNED NOT NULL DEFAULT 0,
  `negative_mark_count` MEDIUMINT UNSIGNED NOT NULL DEFAULT 0,
  `creation_datetime` DATETIME NOT NULL,
  `content_data_id` BIGINT(20) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_author_user_id_idx` (`author_user_id` ASC),
  INDEX `fk_material_id_idx` (`material_id` ASC),
  INDEX `fk_possible_parent_commentary_id_idx` (`parent_commentary_id` ASC),
  UNIQUE INDEX `content_data_UNIQUE` (`content_data_id` ASC),
  CONSTRAINT `fk_commentary_author_user_id`
    FOREIGN KEY (`author_user_id`)
    REFERENCES `brainshare_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_commentary_material_id`
    FOREIGN KEY (`material_id`)
    REFERENCES `brainshare_db`.`material` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_commentary_possible_parent_commentary_id`
    FOREIGN KEY (`parent_commentary_id`)
    REFERENCES `brainshare_db`.`commentary` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_commentary_commentary_content_data`
    FOREIGN KEY (`content_data_id`)
    REFERENCES `brainshare_db`.`content_data` (`id`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB
;


-- -----------------------------------------------------
-- Table `brainshare_db`.`subscription`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `brainshare_db`.`subscription` (
  `subscriber_id` BIGINT(20) UNSIGNED NOT NULL,
  `authority_id` BIGINT(20) UNSIGNED NOT NULL,
  INDEX `fk_subscriber_id_idx` (`subscriber_id` ASC),
  UNIQUE INDEX `all_column_UNIQUE` (`subscriber_id` ASC, `authority_id` ASC),
  CONSTRAINT `fk_subscription_subscriber_id`
    FOREIGN KEY (`subscriber_id`)
    REFERENCES `brainshare_db`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_subscription_authority_id`
    FOREIGN KEY (`authority_id`)
    REFERENCES `brainshare_db`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
;


-- -----------------------------------------------------
-- Table `brainshare_db`.`user_favourite_material`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `brainshare_db`.`user_favourite_material` (
  `user_id` BIGINT(20) UNSIGNED NOT NULL,
  `material_id` BIGINT(20) UNSIGNED NOT NULL,
  `possible_course_id` BIGINT(20) UNSIGNED NULL,
  INDEX `fk_possible_course_id_idx` (`possible_course_id` ASC),
  INDEX `fk_material_id_idx` (`material_id` ASC),
  PRIMARY KEY (`user_id`, `material_id`),
  CONSTRAINT `fk_user_favourite_material_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `brainshare_db`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_favourite_material_possible_course_id`
    FOREIGN KEY (`possible_course_id`)
    REFERENCES `brainshare_db`.`course` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_favourite_material_material_id`
    FOREIGN KEY (`material_id`)
    REFERENCES `brainshare_db`.`material` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
;


-- -----------------------------------------------------
-- Table `brainshare_db`.`material_attachment`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `brainshare_db`.`material_attachment` (
  `material_id` BIGINT(20) UNSIGNED NOT NULL,
  `object_storage_file_id` BIGINT(20) UNSIGNED NOT NULL,
  INDEX `fk_material_id_idx` (`material_id` ASC),
  INDEX `fk_object_storage_file_id_idx` (`object_storage_file_id` ASC),
  PRIMARY KEY (`object_storage_file_id`),
  CONSTRAINT `fk_material_attachment_material_id`
    FOREIGN KEY (`material_id`)
    REFERENCES `brainshare_db`.`material` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_material_attachment_object_storage_file_id`
    FOREIGN KEY (`object_storage_file_id`)
    REFERENCES `brainshare_db`.`object_storage_file` (`id`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB
;


-- -----------------------------------------------------
-- Table `brainshare_db`.`user_saved_attachment`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `brainshare_db`.`user_saved_attachment` (
  `user_id` BIGINT(20) UNSIGNED NOT NULL,
  `attachment_object_storage_file_id` BIGINT(20) UNSIGNED NOT NULL,
  INDEX `fk_user_id_idx` (`user_id` ASC),
  INDEX `fk_attachment_id_idx` (`attachment_object_storage_file_id` ASC),
  PRIMARY KEY (`user_id`, `attachment_object_storage_file_id`),
  CONSTRAINT `fk_user_saved_attachment_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `brainshare_db`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_saved_attachment_attachment_id`
    FOREIGN KEY (`attachment_object_storage_file_id`)
    REFERENCES `brainshare_db`.`material_attachment` (`object_storage_file_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
;


-- -----------------------------------------------------
-- Table `brainshare_db`.`user_desirable_section`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `brainshare_db`.`user_desirable_section` (
  `user_id` BIGINT(20) UNSIGNED NOT NULL,
  `section_id` INT UNSIGNED NOT NULL,
  INDEX `fk_section_id_idx` (`section_id` ASC),
  PRIMARY KEY (`user_id`, `section_id`),
  CONSTRAINT `fk_user_desirable_section_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `brainshare_db`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_desirable_section_section_id`
    FOREIGN KEY (`section_id`)
    REFERENCES `brainshare_db`.`section` (`id`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB
;


-- -----------------------------------------------------
-- Table `brainshare_db`.`user_deferred_material`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `brainshare_db`.`user_deferred_material` (
  `user_id` BIGINT(20) UNSIGNED NOT NULL,
  `material_id` BIGINT(20) UNSIGNED NOT NULL,
  `possible_course_id` BIGINT(20) UNSIGNED NULL,
  INDEX `fk_material_id_idx` (`material_id` ASC),
  INDEX `fk_possible_course_id_idx` (`possible_course_id` ASC),
  PRIMARY KEY (`user_id`, `material_id`),
  CONSTRAINT `fk_user_deferred_material_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `brainshare_db`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_deferred_material_material_id`
    FOREIGN KEY (`material_id`)
    REFERENCES `brainshare_db`.`material` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_deferred_material_possible_course_id`
    FOREIGN KEY (`possible_course_id`)
    REFERENCES `brainshare_db`.`course` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
;


-- -----------------------------------------------------
-- Table `brainshare_db`.`user_bookmark`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `brainshare_db`.`user_bookmark` (
  `user_id` BIGINT(20) UNSIGNED NOT NULL,
  `material_id` BIGINT(20) UNSIGNED NOT NULL,
  `bookmark_name` VARCHAR(255) NOT NULL,
  `description` TEXT NULL,
  `material_inner_position_data` JSON NULL,
  `possible_course_id` BIGINT(20) UNSIGNED NULL,
  PRIMARY KEY (`user_id`, `material_id`),
  INDEX `fk_material_id_idx` (`material_id` ASC),
  INDEX `fk_possible_course_id_idx` (`possible_course_id` ASC),
  CONSTRAINT `fk_user_bookmark_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `brainshare_db`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_bookmark_material_id`
    FOREIGN KEY (`material_id`)
    REFERENCES `brainshare_db`.`material` (`id`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_bookmark_possible_course_id`
    FOREIGN KEY (`possible_course_id`)
    REFERENCES `brainshare_db`.`course` (`id`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION)
ENGINE = InnoDB
;


-- -----------------------------------------------------
-- Table `brainshare_db`.`material_mark`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `brainshare_db`.`material_mark` (
  `material_id` BIGINT(20) UNSIGNED NOT NULL,
  `setter_user_id` BIGINT(20) UNSIGNED NOT NULL,
  `is_up_mark` TINYINT(1) NOT NULL,
  PRIMARY KEY (`material_id`, `setter_user_id`),
  INDEX `fk_setter_user_id_idx` (`setter_user_id` ASC),
  CONSTRAINT `fk_material_mark_material_id`
    FOREIGN KEY (`material_id`)
    REFERENCES `brainshare_db`.`material` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_material_mark_setter_user_id`
    FOREIGN KEY (`setter_user_id`)
    REFERENCES `brainshare_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
;


-- -----------------------------------------------------
-- Table `brainshare_db`.`commentary_mark`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `brainshare_db`.`commentary_mark` (
  `commentary_id` BIGINT(20) UNSIGNED NOT NULL,
  `setter_user_id` BIGINT(20) UNSIGNED NOT NULL,
  `is_up_mark` TINYINT(1) NOT NULL,
  PRIMARY KEY (`commentary_id`, `setter_user_id`),
  INDEX `fk_setter_user_id_idx` (`setter_user_id` ASC),
  CONSTRAINT `fk_commentary_mark_commentary_id`
    FOREIGN KEY (`commentary_id`)
    REFERENCES `brainshare_db`.`commentary` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_commentary_mark_setter_user_id`
    FOREIGN KEY (`setter_user_id`)
    REFERENCES `brainshare_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
;


-- -----------------------------------------------------
-- Table `brainshare_db`.`material_view`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `brainshare_db`.`material_view` (
  `viewer_user_id` BIGINT(20) UNSIGNED NOT NULL,
  `material_id` BIGINT(20) UNSIGNED NOT NULL,
  PRIMARY KEY (`viewer_user_id`, `material_id`),
  INDEX `fk_material_id_idx` (`material_id` ASC),
  CONSTRAINT `fk_material_view_viewer_user_id`
    FOREIGN KEY (`viewer_user_id`)
    REFERENCES `brainshare_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_material_view_material_id`
    FOREIGN KEY (`material_id`)
    REFERENCES `brainshare_db`.`material` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
;


-- -----------------------------------------------------
-- Table `brainshare_db`.`user_status_in_section`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `brainshare_db`.`user_status_in_section` (
  `user_id` BIGINT(20) UNSIGNED NOT NULL,
  `section_id` INT UNSIGNED NOT NULL,
  `ratio` DOUBLE NOT NULL DEFAULT 0,
  `status_value` VARCHAR(50) NOT NULL DEFAULT 'NORMAL',
  PRIMARY KEY (`user_id`, `section_id`),
  INDEX `fk_section_id_idx` (`section_id` ASC),
  CONSTRAINT `fk_user_status_in_section_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `brainshare_db`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_status_in_section_section_id`
    FOREIGN KEY (`section_id`)
    REFERENCES `brainshare_db`.`section` (`id`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB
;


-- -----------------------------------------------------
-- Table `brainshare_db`.`in_commentary_moderator_request`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `brainshare_db`.`in_commentary_moderator_request` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `conmmentary_id` BIGINT(20) UNSIGNED NOT NULL,
  `material_id` BIGINT(20) UNSIGNED NOT NULL,
  `section_of_material_id` INT UNSIGNED NOT NULL,
  `is_concidered_by_someone` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_commentary_id_idx` (`conmmentary_id` ASC),
  INDEX `fk_material_id_idx` (`material_id` ASC),
  INDEX `fk_section_of_material_id_idx` (`section_of_material_id` ASC),
  CONSTRAINT `fk_in_commentary_moderator_request_commentary_id`
    FOREIGN KEY (`conmmentary_id`)
    REFERENCES `brainshare_db`.`commentary` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_in_commentary_moderator_request_material_id`
    FOREIGN KEY (`material_id`)
    REFERENCES `brainshare_db`.`material` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_in_commentary_moderator_request_section_of_material_id`
    FOREIGN KEY (`section_of_material_id`)
    REFERENCES `brainshare_db`.`section` (`id`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB
;


-- -----------------------------------------------------
-- Table `brainshare_db`.`material_recommendation`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `brainshare_db`.`material_recommendation` (
  `source_material_id` BIGINT(20) UNSIGNED NOT NULL,
  `recommended_material_id` BIGINT(20) UNSIGNED NOT NULL,
  `recommendation_type` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`source_material_id`, `recommended_material_id`),
  INDEX `fk_recommended_material_id_idx` (`recommended_material_id` ASC),
  CONSTRAINT `fk_material_recommendation_source_material_id`
    FOREIGN KEY (`source_material_id`)
    REFERENCES `brainshare_db`.`material` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_material_recommendation_recommended_material_id`
    FOREIGN KEY (`recommended_material_id`)
    REFERENCES `brainshare_db`.`material` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
;


-- -----------------------------------------------------
-- Table `brainshare_db`.`modification`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `brainshare_db`.`modification` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `source_material_id` BIGINT(20) UNSIGNED NOT NULL,
  `requester_user_id` BIGINT(20) UNSIGNED NOT NULL,
  `modification_data_json` JSON NOT NULL,
  `is_accepted` TINYINT(1) NOT NULL DEFAULT 0,
  `creation_datetime` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_source_material_id_idx` (`source_material_id` ASC),
  INDEX `fk_requester_user_id_idx` (`requester_user_id` ASC),
  CONSTRAINT `fk_modification_source_material_id`
    FOREIGN KEY (`source_material_id`)
    REFERENCES `brainshare_db`.`material` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_modification_requester_user_id`
    FOREIGN KEY (`requester_user_id`)
    REFERENCES `brainshare_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
;


-- -----------------------------------------------------
-- Table `brainshare_db`.`notification_type`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `brainshare_db`.`notification_type` (
  `id` INT UNSIGNED NOT NULL,
  `type_name` VARCHAR(50) NOT NULL,
  `description` TEXT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
;


-- -----------------------------------------------------
-- Table `brainshare_db`.`generic_notification`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `brainshare_db`.`generic_notification` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `receiver_user_id` BIGINT(20) UNSIGNED NOT NULL,
  `creation_datetime` DATETIME NOT NULL,
  `type_id` INT UNSIGNED NOT NULL,
  `is_checked_by_receiver` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_receiver_user_id_idx` (`receiver_user_id` ASC),
  INDEX `fk_type_id_idx` (`type_id` ASC),
  CONSTRAINT `fk_generic_notification_receiver_user_id`
    FOREIGN KEY (`receiver_user_id`)
    REFERENCES `brainshare_db`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_generic_notification_type_id`
    FOREIGN KEY (`type_id`)
    REFERENCES `brainshare_db`.`notification_type` (`id`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB
;


-- -----------------------------------------------------
-- Table `brainshare_db`.`notification_without_reason_reference`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `brainshare_db`.`notification_without_reason_reference` (
  `notification_id` BIGINT(20) UNSIGNED NOT NULL,
  `reason_data_json` JSON NOT NULL,
  PRIMARY KEY (`notification_id`),
  CONSTRAINT `fk_notification_without_reason_reference_notification_id`
    FOREIGN KEY (`notification_id`)
    REFERENCES `brainshare_db`.`generic_notification` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
;


-- -----------------------------------------------------
-- Table `brainshare_db`.`notification_about_new_modification`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `brainshare_db`.`notification_about_new_modification` (
  `notification_id` BIGINT(20) UNSIGNED NOT NULL,
  `reason_id` BIGINT(20) UNSIGNED NOT NULL,
  PRIMARY KEY (`notification_id`),
  INDEX `fk_reason_id_idx` (`reason_id` ASC),
  CONSTRAINT `fk_notification_about_new_modification_notification_id`
    FOREIGN KEY (`notification_id`)
    REFERENCES `brainshare_db`.`generic_notification` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_notification_about_new_modification_reason_id`
    FOREIGN KEY (`reason_id`)
    REFERENCES `brainshare_db`.`modification` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
;


-- -----------------------------------------------------
-- Table `brainshare_db`.`notification_about_material_achievement`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `brainshare_db`.`notification_about_material_achievement` (
  `notification_id` BIGINT(20) UNSIGNED NOT NULL,
  `reason_id` BIGINT(20) UNSIGNED NOT NULL,
  PRIMARY KEY (`notification_id`),
  INDEX `fk_reason_id_idx` (`reason_id` ASC),
  CONSTRAINT `fk_notification_about_material_achievement_notification_id`
    FOREIGN KEY (`notification_id`)
    REFERENCES `brainshare_db`.`generic_notification` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_notification_about_material_achievement_reason_id`
    FOREIGN KEY (`reason_id`)
    REFERENCES `brainshare_db`.`material` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
;


-- -----------------------------------------------------
-- Table `brainshare_db`.`notification_about_new_material`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `brainshare_db`.`notification_about_new_material` (
  `notification_id` BIGINT(20) UNSIGNED NOT NULL,
  `reason_id` BIGINT(20) UNSIGNED NOT NULL,
  PRIMARY KEY (`notification_id`),
  INDEX `fk_reason_id_idx` (`reason_id` ASC),
  CONSTRAINT `fk_notification_about_new_material_notification_id`
    FOREIGN KEY (`notification_id`)
    REFERENCES `brainshare_db`.`generic_notification` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_notification_about_new_material_reason_id`
    FOREIGN KEY (`reason_id`)
    REFERENCES `brainshare_db`.`material` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
;


-- -----------------------------------------------------
-- Table `brainshare_db`.`notification_about_subcomment`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `brainshare_db`.`notification_about_subcomment` (
  `notification_id` BIGINT(20) UNSIGNED NOT NULL,
  `reason_id` BIGINT(20) UNSIGNED NOT NULL,
  PRIMARY KEY (`notification_id`),
  INDEX `fk_reason_id_idx` (`reason_id` ASC),
  CONSTRAINT `fk_notification_about_subcomment_notification_id`
    FOREIGN KEY (`notification_id`)
    REFERENCES `brainshare_db`.`generic_notification` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_notification_about_subcomment_reason_id`
    FOREIGN KEY (`reason_id`)
    REFERENCES `brainshare_db`.`commentary` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
;

USE `brainshare_db` 
;

-- -----------------------------------------------------
-- function user_status_id_for
-- -----------------------------------------------------

USE `brainshare_db`
;
DROP function IF EXISTS `brainshare_db`.`user_status_id_for`
;

USE `brainshare_db`
;
CREATE FUNCTION user_status_id_for(user_ratio INT) RETURNS INT DETERMINISTIC
BEGIN
	DECLARE found_id INT;
	SELECT `id` INTO found_id FROM `brainshare_db`.`user_status` WHERE user_ratio >= `low_border` ORDER BY `low_border` DESC LIMIT 1;
    RETURN found_id;
END
;


-- -----------------------------------------------------
-- function material_state_id_for
-- -----------------------------------------------------

USE `brainshare_db`
;
DROP function IF EXISTS `brainshare_db`.`material_state_id_for`
;

USE `brainshare_db`
;
CREATE FUNCTION material_state_id_for(material_ratio INT) RETURNS INT DETERMINISTIC
BEGIN
	DECLARE found_id INT;
	SELECT `id` INTO found_id FROM `brainshare_db`.`material_state` WHERE material_ratio >= `low_border` ORDER BY `low_border` DESC LIMIT 1;
    RETURN found_id;
END
;


-- -----------------------------------------------------
-- procedure recount_approval_value
-- -----------------------------------------------------

USE `brainshare_db`
;
DROP procedure IF EXISTS `brainshare_db`.`recount_approval_value`
;

USE `brainshare_db`
;
CREATE PROCEDURE recount_approval_value(material_id BIGINT(20))
BEGIN

    DECLARE positive_mark_count_var MEDIUMINT;
    DECLARE negative_mark_count_var MEDIUMINT;
    DECLARE view_count_var MEDIUMINT;
    DECLARE creation_datetime_var DATETIME;
    DECLARE state_id_var INT;
    DECLARE IN_INCUBATOR_STATE_ID BIT;
	SET IN_INCUBATOR_STATE_ID = 2;
    SELECT `positive_mark_count`, `negative_mark_count`, `view_count`, `creation_datetime`, `state_id` INTO positive_mark_count_var, negative_mark_count_var, view_count_var, creation_datetime_var, state_id_var FROM `brainshare_db`.`material` WHERE `id` = material_id;

    IF state_id_var = IN_INCUBATOR_STATE_ID THEN
		BEGIN
			DECLARE new_approval_value MEDIUMINT;
			SET new_approval_value = 0; 
			UPDATE `brainshare_db`.`material` SET `approval_value` = new_approval_value WHERE `id` = material_id;
        END;
	END IF;

END
;


-- -----------------------------------------------------
-- procedure add_commentary_mark
-- -----------------------------------------------------

USE `brainshare_db`
;
DROP procedure IF EXISTS `brainshare_db`.`add_commentary_mark`
;

USE `brainshare_db`
;
CREATE PROCEDURE add_commentary_mark(commentary_id_param BIGINT(20), setter_user_id_param BIGINT(20), is_up_mark_param BOOL)
BEGIN
	#variable declarations
	DECLARE author_user_id_var BIGINT(20);
	DECLARE author_user_current_ratio DOUBLE;
	DECLARE author_user_current_status_id INT;

	INSERT INTO `brainshare_db`.`commentary_mark`(`commentary_id`, `setter_user_id`, `is_up_mark`) VALUES (commentary_id_param, setter_user_id_param, is_up_mark_param);

	#commentary table
    #DECLARE author_user_id_var BIGINT(20);
    SELECT `author_user_id` INTO author_user_id_var FROM `brainshare_db`.`commentary` WHERE `id` = commentary_id_param;

	#author table
	#DECLARE author_user_current_ratio DOUBLE;
	#DECLARE author_user_current_status_id INT;
	SELECT `ratio`, `status_id` INTO author_user_current_ratio, author_user_current_status_id FROM `brainshare_db`.`user` WHERE `id` = author_user_id_var;

	#just ratios of commentary and author tables
    IF is_up_mark_param = TRUE THEN
		BEGIN
			UPDATE `brainshare_db`.`commentary` SET `ratio` = `ratio` + 1, `positive_mark_count` = `positive_mark_count` + 1 WHERE `id` = commentary_id_param;
			UPDATE `brainshare_db`.`user` SET `ratio` = `ratio` + 1/100 WHERE `id` = author_user_id_var;
			SET author_user_current_ratio = author_user_current_ratio + 1/100;
		END;
    ELSE
		BEGIN
			UPDATE `brainshare_db`.`commentary` SET `ratio` = `ratio` - 1, `negative_mark_count` = `negative_mark_count` + 1 WHERE `id` = commentary_id_param;
			UPDATE `brainshare_db`.`user` SET `ratio` = `ratio` - 1/100 WHERE `id` = author_user_id_var;
			SET author_user_current_ratio = author_user_current_ratio - 1/100;
		END;
    END IF;

	#returning result tuple
    SELECT author_user_id_var, author_user_current_status_id, user_status_id_for(author_user_current_ratio);
END
;


-- -----------------------------------------------------
-- procedure add_material_mark
-- -----------------------------------------------------

USE `brainshare_db`
;
DROP procedure IF EXISTS `brainshare_db`.`add_material_mark`
;

USE `brainshare_db`
;
CREATE PROCEDURE add_material_mark(material_id_param BIGINT(20), setter_user_id_param BIGINT(20), is_up_mark_param BOOL)
BEGIN
	#variable declarations
	DECLARE author_user_id_var BIGINT(20);
    DECLARE section_id_var INT;
    DECLARE current_state_id INT;
    DECLARE current_material_ratio INT;
	DECLARE author_user_current_ratio DOUBLE;
	DECLARE author_user_current_status_id INT;
	DECLARE current_status_value VARCHAR(50);
    DECLARE current_sectional_ratio INT;

	INSERT INTO `brainshare_db`.`material_mark`(`material_id`, `setter_user_id`, `is_up_mark`) VALUES (material_id_param, setter_user_id_param, is_up_mark_param);

	#material table
    #DECLARE author_user_id_var BIGINT(20);
    #DECLARE section_id_param INT;
    #DECLARE current_state_id INT;
    #DECLARE current_material_ratio INT;
    SELECT `author_user_id`, `section_id`, `state_id`, `ratio` INTO author_user_id_var, section_id_var, current_state_id, current_material_ratio FROM `brainshare_db`.`material` WHERE `id` = material_id_param;

	#author table
	#DECLARE author_user_current_ratio DOUBLE;
	#DECLARE author_user_current_status_id INT;
	SELECT `ratio`, `status_id` INTO author_user_current_ratio, author_user_current_status_id FROM `brainshare_db`.`user` WHERE `id` = author_user_id_var;

	#just ratios of material, in_section and author tables
    IF is_up_mark_param = TRUE THEN
		BEGIN
			UPDATE `brainshare_db`.`material` SET `ratio` = `ratio` + 1, `positive_mark_count` = `positive_mark_count` + 1 WHERE `id` = material_id_param;
			UPDATE `brainshare_db`.`user_status_in_section` SET `ratio` = `ratio` + 1 WHERE `user_id` = author_user_id_var AND `section_id` = section_id_var;
			UPDATE `brainshare_db`.`user` SET `ratio` = `ratio` + 1/100 WHERE `id` = author_user_id_var;
            SET current_material_ratio = current_material_ratio + 1;
			SET author_user_current_ratio = author_user_current_ratio + 1/100;
		END;
    ELSE
		BEGIN
			UPDATE `brainshare_db`.`material` SET `ratio` = `ratio` - 1, `negative_mark_count` = `negative_mark_count` + 1 WHERE `id` = material_id_param;
			UPDATE `brainshare_db`.`user_status_in_section` SET `ratio` = `ratio` - 1 WHERE `user_id` = author_user_id_var AND `section_id` = section_id_var;
			UPDATE `brainshare_db`.`user` SET `ratio` = `ratio` - 1/100 WHERE `id` = author_user_id_var;
            SET current_material_ratio = current_material_ratio - 1;
			SET author_user_current_ratio = author_user_current_ratio - 1/100;
		END;
    END IF;

	#approval if in incubator
	CALL recount_approval_value(material_id_param);

	#in_section table
    #DECLARE current_status_value VARCHAR(50);
    #DECLARE current_sectional_ratio INT;
    SELECT `status_value`, `ratio` INTO current_status_value, current_sectional_ratio FROM `brainshare_db`.`user_status_in_section` WHERE `user_id` = author_user_id_var AND `section_id` = section_id_var;

	#returning result tuple
    SELECT author_user_id_var, section_id_var, current_state_id, material_state_id_for(current_material_ratio), current_status_value, current_sectional_ratio, author_user_current_status_id, user_status_id_for(author_user_current_ratio);

END
;

USE `brainshare_db`
;


USE `brainshare_db`
;
DROP TRIGGER IF EXISTS `brainshare_db`.`user_update_referred_tables_user_id_to_zero` 
;
USE `brainshare_db`
;
CREATE DEFINER = CURRENT_USER TRIGGER `brainshare_db`.`user_update_referred_tables_user_id_to_zero` BEFORE DELETE ON `user` FOR EACH ROW
BEGIN
	DECLARE REMOVED_USER_ID BIT;
	SET REMOVED_USER_ID = 0;
	UPDATE `brainshare_db`.`course` SET `brainshare_db`.`course`.`creator_user_id` = @REMOVED_USER_ID WHERE `brainshare_db`.`course`.`creator_user_id` = OLD.`id`;
	UPDATE `brainshare_db`.`material_view` SET `brainshare_db`.`material_view`.`viewer_user_id` = @REMOVED_USER_ID WHERE `brainshare_db`.`material_view`.`viewer_user_id` = OLD.`id`;
	UPDATE `brainshare_db`.`material` SET `brainshare_db`.`material`.`author_user_id` = @REMOVED_USER_ID WHERE `brainshare_db`.`material`.`author_user_id` = OLD.`id`;
	UPDATE `brainshare_db`.`material_mark` SET `brainshare_db`.`material_mark`.`setter_user_id` = @REMOVED_USER_ID WHERE `brainshare_db`.`material_mark`.`setter_user_id` = OLD.`id`;
	UPDATE `brainshare_db`.`commentary_mark` SET `brainshare_db`.`commentary_mark`.`setter_user_id` = @REMOVED_USER_ID WHERE `brainshare_db`.`commentary_mark`.`setter_user_id` = OLD.`id`;
	UPDATE `brainshare_db`.`commentary` SET `brainshare_db`.`commentary`.`author_user_id` = @REMOVED_USER_ID WHERE `brainshare_db`.`commentary`.`author_user_id` = OLD.`id`;
END
;


USE `brainshare_db`
;
DROP TRIGGER IF EXISTS `brainshare_db`.`user_cleanup_avatar_object_storage_file` 
;
USE `brainshare_db`
;
CREATE DEFINER = CURRENT_USER TRIGGER `brainshare_db`.`user_cleanup_avatar_object_storage_file` AFTER DELETE ON `user` FOR EACH ROW
BEGIN
	DELETE FROM `brainshare_db`.`object_storage_file` WHERE `brainshare_db`.`object_storage_file`.`id` = OLD.`avatar`;
END
;


USE `brainshare_db`
;
DROP TRIGGER IF EXISTS `brainshare_db`.`material_create_status_in_section` 
;
USE `brainshare_db`
;
CREATE DEFINER = CURRENT_USER TRIGGER `brainshare_db`.`material_create_status_in_section` AFTER INSERT ON `material` FOR EACH ROW
BEGIN
	INSERT IGNORE INTO `brainshare_db`.`user_status_in_section` (`user_id`, `section_id`) VALUES (NEW.`author_user_id`, NEW.`section_id`);
END
;


USE `brainshare_db`
;
DROP TRIGGER IF EXISTS `brainshare_db`.`material_cleanup_content_data` 
;
USE `brainshare_db`
;
CREATE DEFINER = CURRENT_USER TRIGGER `brainshare_db`.`material_cleanup_content_data` AFTER DELETE ON `material` FOR EACH ROW
BEGIN
	DELETE FROM `brainshare_db`.`content_data` WHERE `brainshare_db`.`content_data`.`id` = OLD.`content_data_id`;
END
;


USE `brainshare_db`
;
DROP TRIGGER IF EXISTS `brainshare_db`.`commentary_cleanup_content_data` 
;
USE `brainshare_db`
;
CREATE DEFINER = CURRENT_USER TRIGGER `brainshare_db`.`commentary_cleanup_content_data` AFTER DELETE ON `commentary` FOR EACH ROW
BEGIN
	DELETE FROM `brainshare_db`.`content_data` WHERE `brainshare_db`.`content_data`.`id` = OLD.`content_data_id`;
END
;


USE `brainshare_db`
;
DROP TRIGGER IF EXISTS `brainshare_db`.`material_attachment_cleanup_object_storage_file` 
;
USE `brainshare_db`
;
CREATE DEFINER = CURRENT_USER TRIGGER `brainshare_db`.`material_attachment_cleanup_object_storage_file` AFTER DELETE ON `material_attachment` FOR EACH ROW
BEGIN
	DELETE FROM `brainshare_db`.`object_storage_file` WHERE `brainshare_db`.`object_storage_file`.`id` = OLD.`object_storage_file_id`;
END
;



SET SQL_MODE=@OLD_SQL_MODE
;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS
;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS
;

-- -----------------------------------------------------
-- Data for table `brainshare_db`.`user_status`
-- -----------------------------------------------------
START TRANSACTION
;
USE `brainshare_db`
;
INSERT IGNORE INTO `brainshare_db`.`user_status` (`id`, `status_name`, `description`, `low_border`) VALUES (0, 'WAS_REMOVED', 'Some user was removed, but some of his materials, or comments, or other data need to be still existing', 	-2147483648)
;
INSERT IGNORE INTO `brainshare_db`.`user_status` (`id`, `status_name`, `description`, `low_border`) VALUES (1, 'UNCHECKED', 'User just registered. Need to confirm accaunt', -2147483647)
;
INSERT IGNORE INTO `brainshare_db`.`user_status` (`id`, `status_name`, `description`, `low_border`) VALUES (2, 'NORMAL', 'Normal default common user status', 0)
;

COMMIT
;


-- -----------------------------------------------------
-- Data for table `brainshare_db`.`user`
-- -----------------------------------------------------
START TRANSACTION
;
USE `brainshare_db`
;
INSERT IGNORE INTO `brainshare_db`.`user` (`id`, `first_name`, `last_name`, `avatar`, `email`, `login`, `password`, `registration_date`, `social_links_json`, `ratio`, `penaly_count`, `status_id`) VALUES (0, 'Empty', 'Empty', NULL, 'Empty', 'Empty', 'Empty', '1970-01-01', '{}', 0, 0, 0)
;

COMMIT
;


-- -----------------------------------------------------
-- Data for table `brainshare_db`.`material_state`
-- -----------------------------------------------------
START TRANSACTION
;
USE `brainshare_db`
;
INSERT IGNORE INTO `brainshare_db`.`material_state` (`id`, `state_name`, `description`, `low_border`) VALUES (1, 'JUST_CREATED', 'Just created material. In users undone materials section', 0)
;

COMMIT
;


-- -----------------------------------------------------
-- Data for table `brainshare_db`.`material_format`
-- -----------------------------------------------------
START TRANSACTION
;
USE `brainshare_db`
;
INSERT IGNORE INTO `brainshare_db`.`material_format` (`id`, `format_name`, `description`) VALUES (1, 'TOPIC', 'Plain old topic')
;

COMMIT
;


-- -----------------------------------------------------
-- Data for table `brainshare_db`.`section`
-- -----------------------------------------------------
START TRANSACTION
;
USE `brainshare_db`
;
INSERT IGNORE INTO `brainshare_db`.`section` (`id`, `super_section_id`, `section_name`, `description`) VALUES (1, NULL, 'Brainshare', 'Information, associated with brainshare resource')
;

COMMIT
;


-- -----------------------------------------------------
-- Data for table `brainshare_db`.`notification_type`
-- -----------------------------------------------------
START TRANSACTION
;
USE `brainshare_db`
;
INSERT IGNORE INTO `brainshare_db`.`notification_type` (`id`, `type_name`, `description`) VALUES (1, 'SIMPLE_TEXT', 'Just some text message. Without any referred sources')
;

COMMIT
;
