-- -----------------------------------------------------
-- Table `flick`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flick` (
  `id` INT NOT NULL,
  `hash` VARCHAR(60) NOT NULL UNIQUE,
  `name` VARCHAR(45) NULL,
  `size` INT NULL,
  UNIQUE(`hash`),
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL ,
  `current_version` INT NULL DEFAULT 0 ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_flick`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_flick` (
  `user_id` INT NOT NULL,
  `flick_id` INT NOT NULL,
  `parent` VARCHAR(500) NULL,
  PRIMARY KEY (`user_id`, `flick_id`) ,
  INDEX `asd_idx` (`flick_id` ASC) ,
  CONSTRAINT `user_foreign_key`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;