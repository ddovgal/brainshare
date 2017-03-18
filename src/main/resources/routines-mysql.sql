SET SQL_MODE = @OLD_SQL_MODE;
;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
;

-- begin attached script 'add_commentary_mark.sql'

DROP PROCEDURE IF EXISTS add_commentary_mark;
;
CREATE PROCEDURE add_commentary_mark(commentary_id_param BIGINT(20), setter_user_id_param BIGINT(20),
                                     is_up_mark_param    BOOL)
  BEGIN
    #variable declarations
    DECLARE author_user_id_var BIGINT(20);
    DECLARE author_user_current_ratio DOUBLE;
    DECLARE author_user_current_status_id INT;

    INSERT INTO `brainshare_dev`.`commentary_mark` (`commentary_id`, `setter_user_id`, `is_up_mark`)
    VALUES (commentary_id_param, setter_user_id_param, is_up_mark_param);

    #commentary table
    #DECLARE author_user_id_var BIGINT(20);
    SELECT `author_user_id`
    INTO author_user_id_var
    FROM `brainshare_dev`.`commentary`
    WHERE `id` = commentary_id_param;

    #author table
    #DECLARE author_user_current_ratio DOUBLE;
    #DECLARE author_user_current_status_id INT;
    SELECT
      `ratio`,
      `status_id`
    INTO author_user_current_ratio, author_user_current_status_id
    FROM `brainshare_dev`.`user`
    WHERE `id` = author_user_id_var;

    #just ratios of commentary and author tables
    IF is_up_mark_param = TRUE
    THEN
      BEGIN
        UPDATE `brainshare_dev`.`commentary`
        SET `ratio` = `ratio` + 1, `positive_mark_count` = `positive_mark_count` + 1
        WHERE `id` = commentary_id_param;
        UPDATE `brainshare_dev`.`user`
        SET `ratio` = `ratio` + 1 / 100
        WHERE `id` = author_user_id_var;
        SET author_user_current_ratio = author_user_current_ratio + 1 / 100;
      END;
    ELSE
      BEGIN
        UPDATE `brainshare_dev`.`commentary`
        SET `ratio` = `ratio` - 1, `negative_mark_count` = `negative_mark_count` + 1
        WHERE `id` = commentary_id_param;
        UPDATE `brainshare_dev`.`user`
        SET `ratio` = `ratio` - 1 / 100
        WHERE `id` = author_user_id_var;
        SET author_user_current_ratio = author_user_current_ratio - 1 / 100;
      END;
    END IF;

    #returning result tuple
    SELECT
      author_user_id_var,
      author_user_current_status_id,
      user_status_id_for(author_user_current_ratio);
  END;
;

-- end attached script 'add_commentary_mark.sql'

-- begin attached script 'add_material_mark.sql'

DROP PROCEDURE IF EXISTS add_material_mark;
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

    INSERT INTO `brainshare_dev`.`material_mark` (`material_id`, `setter_user_id`, `is_up_mark`)
    VALUES (material_id_param, setter_user_id_param, is_up_mark_param);

    #material table
    #DECLARE author_user_id_var BIGINT(20);
    #DECLARE section_id_param INT;
    #DECLARE current_state_id INT;
    #DECLARE current_material_ratio INT;
    SELECT
      `author_user_id`,
      `section_id`,
      `state_id`,
      `ratio`
    INTO author_user_id_var, section_id_var, current_state_id, current_material_ratio
    FROM `brainshare_dev`.`material`
    WHERE `id` = material_id_param;

    #author table
    #DECLARE author_user_current_ratio DOUBLE;
    #DECLARE author_user_current_status_id INT;
    SELECT
      `ratio`,
      `status_id`
    INTO author_user_current_ratio, author_user_current_status_id
    FROM `brainshare_dev`.`user`
    WHERE `id` = author_user_id_var;

    #just ratios of material, in_section and author tables
    IF is_up_mark_param = TRUE
    THEN
      BEGIN
        UPDATE `brainshare_dev`.`material`
        SET `ratio` = `ratio` + 1, `positive_mark_count` = `positive_mark_count` + 1
        WHERE `id` = material_id_param;
        UPDATE `brainshare_dev`.`user_status_in_section`
        SET `ratio` = `ratio` + 1
        WHERE `user_id` = author_user_id_var AND `section_id` = section_id_var;
        UPDATE `brainshare_dev`.`user`
        SET `ratio` = `ratio` + 1 / 100
        WHERE `id` = author_user_id_var;
        SET current_material_ratio = current_material_ratio + 1;
        SET author_user_current_ratio = author_user_current_ratio + 1 / 100;
      END;
    ELSE
      BEGIN
        UPDATE `brainshare_dev`.`material`
        SET `ratio` = `ratio` - 1, `negative_mark_count` = `negative_mark_count` + 1
        WHERE `id` = material_id_param;
        UPDATE `brainshare_dev`.`user_status_in_section`
        SET `ratio` = `ratio` - 1
        WHERE `user_id` = author_user_id_var AND `section_id` = section_id_var;
        UPDATE `brainshare_dev`.`user`
        SET `ratio` = `ratio` - 1 / 100
        WHERE `id` = author_user_id_var;
        SET current_material_ratio = current_material_ratio - 1;
        SET author_user_current_ratio = author_user_current_ratio - 1 / 100;
      END;
    END IF;

    #approval if in incubator
    CALL recount_approval_value(material_id_param);

    #in_section table
    #DECLARE current_status_value VARCHAR(50);
    #DECLARE current_sectional_ratio INT;
    SELECT
      `status_value`,
      `ratio`
    INTO current_status_value, current_sectional_ratio
    FROM `brainshare_dev`.`user_status_in_section`
    WHERE `user_id` = author_user_id_var AND `section_id` = section_id_var;

    #returning result tuple
    SELECT
      author_user_id_var,
      section_id_var,
      current_state_id,
      material_state_id_for(current_material_ratio),
      current_status_value,
      current_sectional_ratio,
      author_user_current_status_id,
      user_status_id_for(author_user_current_ratio);
  END;
;

-- end attached script 'add_material_mark.sql'

-- begin attached script 'material_state_id_for.sql'

DROP FUNCTION IF EXISTS material_state_id_for;
;
CREATE FUNCTION material_state_id_for(material_ratio INT)
  RETURNS INT DETERMINISTIC
  BEGIN
    DECLARE found_id INT;
    SELECT `id`
    INTO found_id
    FROM `brainshare_dev`.`material_state`
    WHERE material_ratio >= `low_border`
    ORDER BY `low_border` DESC
    LIMIT 1;
    RETURN found_id;
  END;
;

-- end attached script 'material_state_id_for.sql'

-- begin attached script 'recount_approval_value.sql'

DROP PROCEDURE IF EXISTS recount_approval_value;
;
CREATE PROCEDURE recount_approval_value(material_id BIGINT(20))
  BEGIN

    DECLARE positive_mark_count_var MEDIUMINT;
    DECLARE negative_mark_count_var MEDIUMINT;
    DECLARE view_count_var MEDIUMINT;
    DECLARE creation_datetime_var DATETIME;
    DECLARE state_id_var INT;
    SELECT
      `positive_mark_count`,
      `negative_mark_count`,
      `view_count`,
      `creation_datetime`,
      `state_id`
    INTO positive_mark_count_var, negative_mark_count_var, view_count_var, creation_datetime_var, state_id_var
    FROM `brainshare_dev`.`material`
    WHERE `id` = material_id;

    #1 is for 'in incubator'
    IF state_id_var = 1
    THEN
      BEGIN
        DECLARE new_approval_value MEDIUMINT;
        SET new_approval_value = 0; #TODO: make real calculation
        UPDATE `brainshare_dev`.`material`
        SET `approval_value` = new_approval_value
        WHERE `id` = material_id;
      END;
    END IF;
  END;
;

-- end attached script 'recount_approval_value.sql'

-- begin attached script 'user_status_id_for.sql'

DROP FUNCTION IF EXISTS user_status_id_for;
;
CREATE FUNCTION user_status_id_for(user_ratio INT)
  RETURNS INT DETERMINISTIC
  BEGIN
    DECLARE found_id INT;
    SELECT `id`
    INTO found_id
    FROM `brainshare_dev`.`user_status`
    WHERE user_ratio >= `low_border`
    ORDER BY `low_border` DESC
    LIMIT 1;
    RETURN found_id;
  END;
;

-- end attached script 'user_status_id_for.sql'