-- -----------------------------------------------------
-- Data for table `brainshare_dev`.`user_status`
-- -----------------------------------------------------
START TRANSACTION;
;
USE `brainshare_dev`;
;
INSERT INTO `brainshare_dev`.`user_status` (`id`, `status_name`, `description`, `low_border`) VALUES (0, 'WAS_REMOVED',
                                                                                                      'Some user was removed, but some of his materials, or comments, or other data need to be still existing',
                                                                                                      0);
;

COMMIT;
;

-- -----------------------------------------------------
-- Data for table `brainshare_dev`.`user`
-- -----------------------------------------------------
START TRANSACTION;
;
USE `brainshare_dev`;
;
INSERT INTO `brainshare_dev`.`user` (`id`, `first_name`, `last_name`, `avatar`, `email`, `login`, `password`, `registration_date`, `social_links_json`, `ratio`, `penaly_count`, `status_id`)
VALUES (0, 'Empty', 'Empty', NULL, 'Empty', 'Empty', 'Empty', '1970-01-01', '{}', 0, 0, 0);
;

COMMIT;
;

-- -----------------------------------------------------
-- Data for table `brainshare_dev`.`material_state`
-- -----------------------------------------------------
START TRANSACTION;
;
USE `brainshare_dev`;
;
INSERT INTO `brainshare_dev`.`material_state` (`id`, `state_name`, `description`, `low_border`)
VALUES (1, 'JUST_CREATED', 'Just created material. In users undone materials section', 0);
;

COMMIT;
;

-- -----------------------------------------------------
-- Data for table `brainshare_dev`.`material_format`
-- -----------------------------------------------------
START TRANSACTION;
;
USE `brainshare_dev`;
;
INSERT INTO `brainshare_dev`.`material_format` (`id`, `format_name`, `description`)
VALUES (1, 'TOPIC', 'Plain old topic');
;

COMMIT;
;

-- -----------------------------------------------------
-- Data for table `brainshare_dev`.`section`
-- -----------------------------------------------------
START TRANSACTION;
;
USE `brainshare_dev`;
;
INSERT INTO `brainshare_dev`.`section` (`id`, `super_section_id`, `section_name`, `description`)
VALUES (1, NULL, 'Brainshare', 'Information, associated with brainshare resource');
;

COMMIT;
;

-- -----------------------------------------------------
-- Data for table `brainshare_dev`.`notification_type`
-- -----------------------------------------------------
START TRANSACTION;
;
USE `brainshare_dev`;
;
INSERT INTO `brainshare_dev`.`notification_type` (`id`, `type_name`, `description`)
VALUES (1, 'SIMPLE_TEXT', 'Just some text message. Without any referred sources');
;

COMMIT;
;