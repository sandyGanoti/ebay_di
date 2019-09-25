DROP DATABASE IF EXISTS `ebay`;

CREATE SCHEMA IF NOT EXISTS`ebay` DEFAULT CHARACTER SET utf8;
USE `ebay`;

CREATE TABLE IF NOT EXISTS `ebay`.`user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `password` VARCHAR(250) NOT NULL,
  `first_name` VARCHAR(30) NOT NULL,
  `last_name` VARCHAR(30) NOT NULL,
  `username` VARCHAR(30) NOT NULL,
  `phone_number` VARCHAR(20) NOT NULL,
  `country` VARCHAR(20) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `email` VARCHAR(30) NOT NULL,
  PRIMARY KEY(`id`),
  UNIQUE KEY (`username`)
);

CREATE TABLE IF NOT EXISTS `ebay`.`auction_item` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(128) NULL,
  `description` TEXT NOT NULL,
  `country` VARCHAR(128) NULL,
  `location` VARCHAR(128) NULL,
  `currently` decimal(10,2) NOT NULL,
  `first_bid` decimal(10,2) NOT NULL,
  `started_at` DATETIME NULL,
  `ends_at` DATETIME NULL,
  `user_id`  BIGINT(20) NOT NULL,
  `active` TINYINT(1) DEFAULT FALSE,
  PRIMARY KEY(`id`),
  KEY fk_auction_item_user (user_id),
  CONSTRAINT fk_auction_item_user FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE IF NOT EXISTS `ebay`.`category` (
  `name` VARCHAR(30) NOT NULL,
  PRIMARY KEY(`name`)
);

CREATE TABLE IF NOT EXISTS `ebay`.`item_category` (
   `auction_item` BIGINT(20) NOT NULL,
   `category_name` VARCHAR(30) NOT NULL,
   PRIMARY KEY(`category_name`, `auction_item`)
);

CREATE TABLE IF NOT EXISTS `ebay`.`bid` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `time` DATETIME NOT NULL,
  `amount` DECIMAL NOT NULL,
  `auction_item_id` BIGINT(20) NOT NULL,
  `user_id`  BIGINT(20) NOT NULL,
  PRIMARY KEY(id),
  KEY fk_bid_auction_item (auction_item_id),
  CONSTRAINT fk_bid_auction_item FOREIGN KEY (auction_item_id) REFERENCES auction_item (id),
  KEY fk_bid_user (user_id),
  CONSTRAINT fk_bid_user FOREIGN KEY (user_id) REFERENCES user (id),
  CONSTRAINT user_auction_amount UNIQUE (user_id, auction_item_id, amount)
);

CREATE TABLE IF NOT EXISTS `ebay`.`rating` (
  `user_id` BIGINT(20) NOT NULL,
  `rated_user_role` VARCHAR(30) NOT NULL,
  `rater_user_id` BIGINT(20) NOT NULL,
  `rating` INTEGER NOT NULL,
  `time` DATETIME NOT NULL,
  PRIMARY KEY (user_id, rated_user_role, rater_user_id),
  KEY fk_rating_user (user_id),
  CONSTRAINT fk_rating_user FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE IF NOT EXISTS `ebay`.`messaging` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `sender` BIGINT(20) NOT NULL,
  `recipient` BIGINT(20) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `message_body` VARCHAR(250) NOT NULL,
  `read_status` TINYINT(1) DEFAULT FALSE,
  PRIMARY KEY (id),
  KEY fk_messaging_sender_user (sender),
  CONSTRAINT fk_messaging_sender_user FOREIGN KEY (sender) REFERENCES user (id),
  KEY fk_messaging_recipient_user (recipient),
  CONSTRAINT fk_messaging_recipient_user FOREIGN KEY (recipient) REFERENCES user (id)
);

INSERT INTO `ebay`.`user`(id, password, first_name, last_name, username, phone_number, country, created_at, email) values(1, "22", "jon", "doe", "jonDoe", "1234", "US", now(), "jon@doe.com");
INSERT INTO `ebay`.`user`(id, password, first_name, last_name, username, phone_number, country, created_at, email) values(2, "222", "sandu", "doe", "sanduDoe", "1234", "US", now(), "sandu@doe.com");
INSERT INTO `ebay`.`user`(id, password, first_name, last_name, username, phone_number, country, created_at, email) values(3, "333", "bourdou", "doe", "bourdouDoe", "1234", "US", now(), "bourdou@doe.com");
INSERT INTO `ebay`.`category`(name) VALUES("BABY");
INSERT INTO `ebay`.`category`(name) VALUES("CLOTHING_SHOES_AND_ACCESSORIES");
INSERT INTO `ebay`.`category`(name) VALUES("CRAFTS");
INSERT INTO `ebay`.`category`(name) VALUES("HEALTH_AND_BEAUTY");
INSERT INTO `ebay`.`category`(name) VALUES("HOME_AND_GARDEN");
INSERT INTO `ebay`.`category`(name) VALUES("JEWELLERY_AND_WATCHES");
INSERT INTO `ebay`.`category`(name) VALUES("PET_SUPPLIES");
INSERT INTO `ebay`.`category`(name) VALUES("SPORTING_GOODS");
insert into messaging(id, sender, recipient, created_at, message_body, read_status) values(1, 1, 2, now(), "hello sandu!", false);
insert into messaging(id, sender, recipient, created_at, message_body, read_status) values(2, 1, 3, now(), "hello bourdou!", false);
insert into messaging(id, sender, recipient, created_at, message_body, read_status) values(3, 1, 3, now(), "hello bourdou again!", false);
insert into messaging(id, sender, recipient, created_at, message_body, read_status) values(4, 1, 3, now(), "yiouhou bourdou!", false);