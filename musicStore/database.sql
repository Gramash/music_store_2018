CREATE DATABASE IF NOT EXISTS musicshop;
USE musicshop;

DROP TABLE IF EXISTS order_has_product;
DROP TABLE IF EXISTS `order`;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS product_type;
DROP TABLE IF EXISTS product_brand;
DROP VIEW IF EXISTS all_products;
DROP VIEW IF EXISTS `order_view`;

CREATE TABLE `users` (
`email` varchar(50) primary key,
`name` varchar (50) NOT NULL,
`lastName` varchar(50) NOT NULL,
`password` varchar (20) NOT NULL,
`avatar` varchar(50)
);

insert into `users` values ('email@email.com', 'Name', 'LastName' ,'Password1', 'email@email.com.jpg');
insert into `users` values ('email@email2.com', 'Name', 'LastName' ,'paw12dlaw', 'email@email2.com.png');


CREATE TABLE `product_type` (
`type_id` INTEGER,
`type` VARCHAR (50) NOT NULL,
PRIMARY KEY (`type_id`)
);

INSERT INTO `product_type` VALUES(1, 'electric guitars');
INSERT INTO `product_type` VALUES(2, 'acoustic guitars');
INSERT INTO `product_type` VALUES(3, 'bases');

CREATE TABLE `product_brand` (
`brand_id` INTEGER,
`brand` VARCHAR(20) NOT NULL,
PRIMARY KEY (`brand_id`)
);

INSERT INTO `product_brand` VALUES (1, 'gibson');
INSERT INTO `product_brand` VALUES (2, 'martin');
INSERT INTO `product_brand` VALUES (3, 'taylor');
INSERT INTO `product_brand` VALUES (4, 'gretsch');
INSERT INTO `product_brand` VALUES (5, 'rickenbacker');

CREATE TABLE `products` (
	`id`			INTEGER AUTO_INCREMENT,
	`name`			VARCHAR(50) NOT NULL,
	`brand` 		INTEGER NOT NULL,
	`type`			INTEGER NOT NULL,
	`price`			DOUBLE NOT NULL,
	`description`	TEXT,
	`image_src` 	VARCHAR(50),
PRIMARY KEY (`id`),
FOREIGN KEY (`type`) REFERENCES `product_type` (`type_id`),
FOREIGN KEY (`brand`) REFERENCES `product_brand` (`brand_id`)
);

insert into `products` VALUES (1,	'Gibson sg',	1, 1, 850.0, "description", "gibsonsg1.jpg");
insert into `products` VALUES (2,	'Gibson Les Paul',		1, 1, 1000.0, "description", "gibson_lp.jpg");
insert into `products` VALUES (3,	'Martin John Mayer signature',2, 2, 2200.0, "description", "martin_042sc.jpg");
insert into `products` VALUES (4,	'Martin OM-21', 	3, 2, 2000.0, "description", "martin_OM_21.jpg");
insert into `products` VALUES (5,	'Gretsch Hot Rod', 4, 1, 1300.0, "description", "gretsch_hot_rod.jpg");
insert into `products` VALUES (6,	'Rickenbacker 4003S', 5, 3, 1200.0, "description", "Rickenbacker 4003S.jpg");


CREATE VIEW `all_products` AS
SELECT `products`.`id`,`products`.`name`, `product_brand`.`brand`, `product_type`.`type`, `products`.`price`, `products`.`description`, `products`.`image_src`
from `products`
LEFT JOIN `product_brand` ON `products`.`brand` = `product_brand`.`brand_id`
LEFT JOIN `product_type` ON `products`.`type` = `product_type`.`type_id`;

CREATE TABLE `order` (
	`user_id`	VARCHAR(50),
	`order_id`	INT NOT NULL AUTO_INCREMENT,
	`address`	TEXT,
	`status`	ENUM ('completed','cancelled','pending') DEFAULT 'pending',
	`timestamp` TIMESTAMP,
PRIMARY KEY (`order_id`)
);

insert into `order` values ('email', default, 'address',  default, default);
insert into `order` values ('email2', default, 'address', default, default);

CREATE TABLE `order_has_product` (
	`order_id`		INT NOT NULL,
	`product_id`	INTEGER,
	`product_count` INTEGER,
	`current_price` DOUBLE,
FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`)
);

insert into `order_has_product` values (1, 1, 2, 100.0);
insert into `order_has_product` values (1, 1, 3, 500.0);
insert into `order_has_product` values (2, 2, 5, 100.0);

DROP VIEW IF EXISTS `order_view`;
CREATE VIEW `order_view` AS
SELECT o.`user_id`, o.`order_id`, ohp.`product_id`, o.`status`,
`all_products`.`name`, ohp.`product_count`, ohp.`current_price`, o.`timestamp`
FROM `order` o
JOIN `order_has_product` ohp ON o.`order_id` = ohp.`order_id`
JOIN `all_products` on ohp.`product_id` = `all_products`.`id`;

select last_insert_id()
