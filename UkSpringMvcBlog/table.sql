CREATE DATABASE  IF NOT EXISTS nov2017;
USE nov2017;

  CREATE TABLE `nov2017`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `active` INT NULL,
  PRIMARY KEY (`user_id`));
  
  CREATE TABLE `nov2017`.`role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
);

  CREATE TABLE `nov2017`.`user_role` (
  user_id INT NOT NULL ,
  role_id INT NOT NULL ,
   FOREIGN KEY (user_id) REFERENCES user (user_id) ,
   FOREIGN KEY (role_id) REFERENCES role (role_id));
   
CREATE TABLE `nov2017`.`article` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `description` VARCHAR(10000) NOT NULL,
  `reply_count` INT NULL,
  `reply_flg` TINYINT NULL,
  `del_flg` TINYINT NULL,
  `verify_flg` TINYINT NULL,
  `created_by` VARCHAR(45) NULL,
  `created_date` DATE NULL,
  `last_update_by` VARCHAR(45) NULL,
  `Last_update_date` DATE NULL,
  PRIMARY KEY (`id`));
  
  CREATE TABLE `reply` (
  `id` INT NOT NULL,
  `reply_id` INT NOT NULL AUTO_INCREMENT,
  `description` varchar(10000) DEFAULT NULL,
   `verify_flg` TINYINT NULL,
   `del_flg` TINYINT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_date` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`reply_id`),
  CONSTRAINT `reply_ibfk_1` FOREIGN KEY (`id`) REFERENCES `article` (`id`)
) ;
INSERT INTO `user` (`user_id`,`email`,`password`,`name`,`last_name`,`active`) VALUES (1,'yamini@gmail.com','yamini','Yamini','Venkat',1);
INSERT INTO `user` (`user_id`,`email`,`password`,`name`,`last_name`,`active`) VALUES (2,'admin@gmail.com','admin','admin','owner',1);

INSERT INTO `role` (`role_id`,`role`) VALUES (1,'ADMIN');
INSERT INTO `role` (`role_id`,`role`) VALUES (2,'USER');

INSERT INTO `user_role` (`user_id`,`role_id`) VALUES (1,2);
INSERT INTO `user_role` (`user_id`,`role_id`) VALUES (2,1);