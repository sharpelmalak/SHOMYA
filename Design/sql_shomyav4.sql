-- MySQL Workbench Forward Engineering

-- -----------------------------------------------------
-- Schema shomya
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `shomya` DEFAULT CHARACTER SET utf8 ;

USE `shomya` ;
-- -----------------------------------------------------
-- Table `shomya`.`customer`
-- -----------------------------------------------------
	CREATE TABLE IF NOT EXISTS `shomya`.`customer` (
	  `id` INT NOT NULL AUTO_INCREMENT,
	  `birthdate` DATE NOT NULL,
	  `job` VARCHAR(45) NULL DEFAULT NULL,
	  `credit_limit` FLOAT NOT NULL,
	  `address` VARCHAR(100) NOT NULL,
	  PRIMARY KEY (`id`))
	ENGINE = InnoDB
	DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `shomya`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shomya`.`admin` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `hire_date` DATE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `shomya`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shomya`.`category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL unique,
  `image` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `shomya`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shomya`.`product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` FLOAT NOT NULL,
  `quantity` INT NOT NULL,
  `description` VARCHAR(200) NULL,
  `image` VARCHAR(100) NULL,
  `admin_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_product_admin1`
    FOREIGN KEY (`admin_id`)
    REFERENCES `shomya`.`admin` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `shomya`.`category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `shomya`.`cart_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shomya`.`cart_item` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `quantity` INT NOT NULL,
  `current_price` FLOAT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_cart_item_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `shomya`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `shomya`.`cart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shomya`.`cart` (
  `customer_id` INT NOT NULL,
  `cart_item_id` INT NOT NULL,
  PRIMARY KEY (`customer_id`, `cart_item_id`),
  CONSTRAINT `fk_cart_customer`
    FOREIGN KEY (`customer_id`)
    REFERENCES `shomya`.`customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cart_cart_item1`
    FOREIGN KEY (`cart_item_id`)
    REFERENCES `shomya`.`cart_item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shomya`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shomya`.`order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `order_date` DATETIME NOT NULL,
  `customer_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_order_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `shomya`.`customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `shomya`.`order_items`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shomya`.`order_items` (
  `order_id` INT NOT NULL,
  `cart_item_id` INT NOT NULL,
  PRIMARY KEY (`order_id`, `cart_item_id`),
  CONSTRAINT `fk_order_items_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `shomya`.`order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_items_cart_item1`
    FOREIGN KEY (`cart_item_id`)
    REFERENCES `shomya`.`cart_item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `shomya`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shomya`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `shomya`.`customer_has_interests_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shomya`.`customer_has_interests_category` (
  `customer_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`customer_id`, `category_id`),
  CONSTRAINT `fk_customer_has_category_customer`
    FOREIGN KEY (`customer_id`)
    REFERENCES `shomya`.`customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_customer_has_category_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `shomya`.`category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `shomya`.`customer_wishlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shomya`.`customer_wishlist` (
  `customer_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`customer_id`, `product_id`),
  CONSTRAINT `fk_customer_has_product_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `shomya`.`customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_customer_has_product_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `shomya`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

