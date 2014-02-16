CREATE TABLE IF NOT EXISTS `COUNTRY` (
  `COUNTRY_ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `COUNTRY_NAME` varchar(50) NOT NULL,
  `COUNTRY_CODE` char(2) DEFAULT NULL,
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATE_DATE` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`COUNTRY_ID`),
  UNIQUE KEY `name` (`COUNTRY_NAME`),
  KEY `COUNTRY_NAME` (`COUNTRY_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=350