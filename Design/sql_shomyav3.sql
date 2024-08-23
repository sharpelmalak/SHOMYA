-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema shomya
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema shomya
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `shomya` DEFAULT CHARACTER SET utf8 ;
USE `shomya` ;

-- -----------------------------------------------------
-- Table `shomya`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shomya`.`user` ;

CREATE TABLE IF NOT EXISTS `shomya`.`user` (
  `id` INT NOT NULL auto_increment,
  `name` VARCHAR(45) NOT NULL,
  `birthdate` DATE NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `job` VARCHAR(45) NULL,
  `password` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `credit_limit` FLOAT NOT NULL,
  `address` VARCHAR(100) NOT NULL,
  `user_role` VARCHAR(10) NOT NULL DEFAULT 'user',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shomya`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shomya`.`category` ;

CREATE TABLE IF NOT EXISTS `shomya`.`category` (
  `id` INT NOT NULL auto_increment,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shomya`.`product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shomya`.`product` ;

CREATE TABLE IF NOT EXISTS `shomya`.`product` (
  `id` INT NOT NULL auto_increment,
  `name` VARCHAR(45) NOT NULL,
  `price` FLOAT NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shomya`.`order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shomya`.`order` ;

CREATE TABLE IF NOT EXISTS `shomya`.`order` (
  `id` INT NOT NULL auto_increment,
  `order_date` DATETIME NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`, `user_id`),
  INDEX `fk_order_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `shomya`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shomya`.`category_has_product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shomya`.`category_has_product` ;

CREATE TABLE IF NOT EXISTS `shomya`.`category_has_product` (
  `category_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`category_id`, `product_id`),
  INDEX `fk_category_has_product_product1_idx` (`product_id` ASC) VISIBLE,
  INDEX `fk_category_has_product_category1_idx` (`category_id` ASC) VISIBLE,
  CONSTRAINT `fk_category_has_product_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `shomya`.`category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_category_has_product_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `shomya`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shomya`.`user_intersts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shomya`.`user_intersts` ;

CREATE TABLE IF NOT EXISTS `shomya`.`user_intersts` (
  `user_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `category_id`),
  INDEX `fk_user_has_category_category1_idx` (`category_id` ASC) VISIBLE,
  INDEX `fk_user_has_category_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_category_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `shomya`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_category_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `shomya`.`category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shomya`.`cart`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shomya`.`cart` ;

CREATE TABLE IF NOT EXISTS `shomya`.`cart` (
  `user_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `quantity` INT NOT NULL,
  `current_price` FLOAT NOT NULL,
  PRIMARY KEY (`user_id`, `product_id`),
  INDEX `fk_user_has_product_product1_idx` (`product_id` ASC) VISIBLE,
  INDEX `fk_user_has_product_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_product_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `shomya`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_product_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `shomya`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shomya`.`order_has_products`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shomya`.`order_has_products` ;

CREATE TABLE IF NOT EXISTS `shomya`.`order_has_products` (
  `order_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `quantity` INT NOT NULL,
  `current_price` FLOAT NOT NULL,
  PRIMARY KEY (`order_id`, `product_id`),
  INDEX `fk_order_has_product_product1_idx` (`product_id` ASC) VISIBLE,
  INDEX `fk_order_has_product_order1_idx` (`order_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_has_product_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `shomya`.`order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_has_product_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `shomya`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
