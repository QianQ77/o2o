use o2o;

DROP TABLE IF EXISTS `tb_area`;
create table `tb_area`(
	`area_id` int NOT NULL AUTO_INCREMENT,
	`area_name` varchar(200) NOT NULL,
	`create_time` datetime DEFAULT NULL,
	`last_edit_time` datetime DEFAULT NULL,
	primary key (`area_id`),
	unique key `UK_AREA`(`area_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_person_info`;
create table `tb_person_info` (
	`user_id` int NOT NULL AUTO_INCREMENT,
	`name` varchar(32) DEFAULT NULL,
	`profile_img` varchar(1024) DEFAULT NULL,
	`email` varchar(1024) DEFAULT NULL,
	`gender` varchar(2) DEFAULT NULL,
	`enable_status` int NOT NULL DEFAULT '0' COMMENT '0:disabled; 1:enabled',
	`user_type` int NOT NULL DEFAULT '1' COMMENT '1: customer; 2: merchant; 3: admin',
	`create_time` DATETIME DEFAULT NULL,
	`last_edit_time` DATETIME DEFAULT NULL,
	PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_wechat_auth`;
create table `tb_wechat_auth` (
	`wechat_auth_id` int NOT NULL AUTO_INCREMENT,
	`open_id` varchar(1024) NOT NULL,
	`create_time` DATETIME DEFAULT NULL,
	`user_id` int NOT NULL,
	PRIMARY KEY (`wechat_auth_id`),
	CONSTRAINT `fk_wechatauth_profile` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info`(`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

alter table `tb_wechat_auth` ADD UNIQUE INDEX(open_id);

DROP TABLE IF EXISTS `tb_local_auth`;
create table `tb_local_auth` (
	`local_auth_id` int NOT NULL AUTO_INCREMENT,
	`username` varchar(128) NOT NULL,
	`password` varchar(128) NOT NULL,
	`create_time` DATETIME DEFAULT NULL,
	`last_edit_time` DATETIME DEFAULT NULL,
	`user_id` int NOT NULL,
	PRIMARY KEY (`local_auth_id`),
	UNIQUE KEY `uk_local_profile` (`username`),
	CONSTRAINT `fk_localauth_profile` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info`(`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_head_line`;
create table `tb_head_line` (
	`line_id` int NOT NULL AUTO_INCREMENT,
	`line_name` varchar(1000) NOT NULL,
	`line_link` varchar(2000) NOT NULL,
	`line_img` varchar(2000) NOT NULL,
	`priority` int NOT NULL DEFAULT '0',
	`enable_status` int NOT NULL DEFAULT '0' COMMENT '0:disabled; 1:enabled',
	`create_time` DATETIME DEFAULT NULL,
	`last_edit_time` DATETIME DEFAULT NULL,
	PRIMARY KEY (`line_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_shop_category`;
create table `tb_shop_category` (
	`shop_category_id` int NOT NULL AUTO_INCREMENT,
	`shop_category_name` varchar(100) NOT NULL,
	`shop_category_desc` varchar(1000) NOT NULL,
	`shop_category_img` varchar(2000) NOT NULL,
	`priority` int NOT NULL DEFAULT '0',
	`create_time` DATETIME DEFAULT NULL,
	`last_edit_time` DATETIME DEFAULT NULL,
	`parent_id` int DEFAULT NULL,
	PRIMARY KEY (`shop_category_id`),
	CONSTRAINT `fk_shop_category_self` FOREIGN KEY (`parent_id`) REFERENCES `tb_shop_category` (`shop_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_shop`;
create table `tb_shop` (
	`shop_id` int NOT NULL AUTO_INCREMENT,
	`owner_id` int NOT NULL COMMENT 'Owner',
	`area_id` int DEFAULT NULL,
	`shop_category_id` int DEFAULT NULL,
	`shop_name` varchar(1000) NOT NULL,
	`shop_addr` varchar(200) DEFAULT NULL,
	`shop_desc` varchar(2000) DEFAULT NULL,
	`phone` varchar(128) DEFAULT NULL ,
	`shop_img` varchar(2000) DEFAULT NULL,
	`priority` int NOT NULL DEFAULT '0',
	`enable_status` int NOT NULL DEFAULT '0' COMMENT '-1: disabled; 0: under reviewing; 1: enabled',
	`create_time` DATETIME DEFAULT NULL,
	`last_edit_time` DATETIME DEFAULT NULL,
	`advice` varchar(255) DEFAULT NULL,
	PRIMARY KEY (`shop_id`),
	CONSTRAINT `fk_shop_area` FOREIGN KEY (`area_id`) REFERENCES `tb_area` (`area_id`),
	CONSTRAINT `fk_shop_profile` FOREIGN KEY (`owner_id`) REFERENCES `tb_person_info` (`user_id`),
	CONSTRAINT `fk_shop_shopcate` FOREIGN KEY (`shop_category_id`) REFERENCES `tb_shop_category` (`shop_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_product_category`;
create table `tb_product_category` (
	`product_category_id` int NOT NULL AUTO_INCREMENT,
	`product_category_name` varchar(100) NOT NULL,
	`priority` int NOT NULL DEFAULT '0',
	`create_time` DATETIME DEFAULT NULL,
	`shop_id` int NOT NULL DEFAULT '0',
	PRIMARY KEY (`product_category_id`),
	CONSTRAINT `fk_product_category_self` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_product`;
create table `tb_product` (
	`product_id` int NOT NULL AUTO_INCREMENT,
	`product_name` varchar(100) NOT NULL,
	`img_addr` varchar(2000) DEFAULT NULL,
	`product_desc` varchar(2000) DEFAULT NULL,
	`normal_price` varchar(100) DEFAULT NULL,
	`promotion_price` varchar(100) DEFAULT NULL,
	`priority` int NOT NULL DEFAULT '0',
	`create_time` DATETIME DEFAULT NULL,
	`last_edit_time` DATETIME DEFAULT NULL,
	`enable_status` int NOT NULL DEFAULT '0' COMMENT '0: out of stock; 1: enabled',
	`shop_id` int NOT NULL DEFAULT '0',
	`product_category_id` int DEFAULT NULL,
	PRIMARY KEY (`product_id`),
	CONSTRAINT `fk_product_prodcate` FOREIGN KEY (`product_category_id`) REFERENCES `tb_product_category` (`product_category_id`),
	CONSTRAINT `fk_product_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_product_img`;
create table `tb_product_img` (
	`product_img_id` int NOT NULL AUTO_INCREMENT,
	`img_addr` varchar(2000) NOT NULL,
	`img_desc` varchar(2000) DEFAULT NULL,
	`priority` int NOT NULL DEFAULT '0',
	`create_time` DATETIME DEFAULT NULL,
	`product_id` int NOT NULL,
	PRIMARY KEY (`product_img_id`),
	CONSTRAINT `fk_proimg_product` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;