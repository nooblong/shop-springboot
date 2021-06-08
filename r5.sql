# Host: localhost  (Version: 5.5.22)
# Date: 2021-05-26 12:43:35
# Generator: MySQL-Front 5.3  (Build 4.9)

/*!40101 SET NAMES utf8 */;

#
# Source for table "order"
#

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `submit_datetime` datetime NOT NULL,
  `address` varchar(100) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `state` int(11) NOT NULL,
  `tele` varchar(30) NOT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `name` varchar(20) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=gbk;

#
# Data for table "order"
#

INSERT INTO `order` VALUES (6,'2021-05-11 09:08:23','1',7,2,'1','1','1',0.00),(7,'2010-12-31 18:00:00','123123',0,2,'123',NULL,'123',123.00),(8,'2021-05-11 09:13:36','531',7,0,'1818181818118','fuck','lyl',0.00),(9,'2021-05-11 09:20:37','1',7,2,'1','1','1',0.00),(10,'2021-05-11 09:26:04','1',7,0,'1','1','1',0.00),(13,'2021-05-11 10:56:28','1',7,2,'1','1','jjj',300.00),(14,'2021-05-11 13:14:51','111',7,0,'111','gggggg','ggg',300.00),(15,'2021-05-11 13:56:11','531',1,0,'1111111','n','lyl',640.00),(16,'2021-05-11 14:02:11','555',1,0,'13697750460','nhb','noob',1000.00),(17,'2021-05-13 08:24:28','123',7,0,'123','123','1123',300.00),(18,'2021-05-13 10:04:48','1',7,0,'1','1','1',200.00),(19,'2021-05-22 08:51:56','1',7,0,'1','1','1',600.00),(20,'2021-05-22 11:27:24','room',7,0,'tele','remark','name',0.00),(21,'2021-05-22 11:27:45','room',7,0,'tele','remark','name',500.00),(22,'2021-05-22 11:38:16','room',7,0,'tele','remark','name',500.00);

#
# Source for table "order_extend"
#

DROP TABLE IF EXISTS `order_extend`;
CREATE TABLE `order_extend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `count` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `order_extend_order_id_fk` (`order_id`),
  CONSTRAINT `order_extend_order_id_fk` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=gbk;

#
# Data for table "order_extend"
#

INSERT INTO `order_extend` VALUES (7,1,10,6),(8,2,4,6),(9,3,1,6),(10,1,10,7),(11,2,4,7),(12,3,1,7),(13,1,2,8),(14,2,1,8),(15,3,1,8),(16,4,3,8),(17,5,2,8),(18,6,1,8),(19,5,3,9),(20,1,5,10),(23,1,3,13),(24,2,3,14),(25,2,2,15),(26,4,4,15),(27,6,4,15),(28,1,10,16),(29,1,3,17),(30,1,2,18),(31,1,1,19),(32,2,2,19),(33,3,3,19),(34,1,5,21),(35,1,5,22);

#
# Source for table "product"
#

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `img` varchar(100) DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  `stock` int(11) NOT NULL DEFAULT '0',
  `show` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=gbk;

#
# Data for table "product"
#

INSERT INTO `product` VALUES (1,'hsnrm','https://ftp.bmp.ovh/imgs/2021/05/efa9916a841159cc.jpg',100.01,53,1),(2,'hsnrm2','https://ftp.bmp.ovh/imgs/2021/05/efa9916a841159cc.jpg',100.01,92,1),(3,'hsnrm3','https://ftp.bmp.ovh/imgs/2021/05/efa9916a841159cc.jpg',100.01,97,1),(5,'testEdit','sa',12.00,1200,1),(6,'hsnrm6','https://ftp.bmp.ovh/imgs/2021/05/efa9916a841159cc.jpg',100.00,0,1),(7,'hsnrm7','https://ftp.bmp.ovh/imgs/2021/05/efa9916a841159cc.jpg',10000.01,3,1),(8,'add','add',5556.00,555,1);

#
# Source for table "category"
#

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product` int(11) NOT NULL,
  `category_name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `category_product_id_fk` (`product`),
  CONSTRAINT `category_product_id_fk` FOREIGN KEY (`product`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

#
# Data for table "category"
#


#
# Source for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `isAdmin` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=gbk;

#
# Data for table "user"
#

INSERT INTO `user` VALUES (1,'lyl','123',0),(2,'lyl2','123',1),(7,'123','123',0),(9,'fuck','fuck',0);
