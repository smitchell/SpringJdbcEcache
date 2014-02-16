CREATE TABLE IF NOT EXISTS `COUNTRY` (
  `COUNTRY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `COUNTRY_NAME` VARCHAR(50) NOT NULL,
  `COUNTRY_CODE` CHAR(2),
  `CREATE_DATE` TIMESTAMP NOT NULL,
  `UPDATE_DATE` DATETIME NOT NULL
);
ALTER TABLE `COUNTRY` ADD PRIMARY KEY (`COUNTRY_ID`);
ALTER TABLE `COUNTRY` ALTER COLUMN `COUNTRY_ID` RESTART WITH 400;
CREATE INDEX COUNTRY_idx ON COUNTRY (`COUNTRY_ID`);
ALTER TABLE COUNTRY ADD CONSTRAINT COUNTRY_COUNTRY_CODE_UNQ UNIQUE(`COUNTRY_CODE`);
ALTER TABLE COUNTRY ADD CONSTRAINT COUNTRY_NAME_UNQ UNIQUE(`COUNTRY_NAME`);