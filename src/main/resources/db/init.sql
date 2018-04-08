DROP SCHEMA IF EXISTS `books-jdbc-jsp-servlets`;

CREATE SCHEMA `books-jdbc-jsp-servlets`;

USE `books-jdbc-jsp-servlets`;


DROP TABLE IF EXISTS `author`;

CREATE TABLE `author` (
  `id`         INT(11)     NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(64) NOT NULL,
  `last_name`  VARCHAR(64) NOT NULL,

  PRIMARY KEY (`id`)
);


DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `id`        INT(11)      NOT NULL AUTO_INCREMENT,
  `title`     VARCHAR(128) NOT NULL,
  `author_id` INT(11)      NOT NULL,

  PRIMARY KEY (`id`),

  FOREIGN KEY (`author_id`) REFERENCES `author` (`id`)

    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);