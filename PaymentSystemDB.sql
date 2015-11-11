# --------------------------------------------------------
# Host:                         127.0.0.1
# Database:                     payments
# Server version:               5.5.29
# Server OS:                    Win32
# HeidiSQL version:             5.0.0.3272
# Date/time:                    2015-06-15 16:39:15
# --------------------------------------------------------

CREATE TABLE `user` (
	`ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`firstname` TEXT NOT NULL,
	`lastname` TEXT NOT NULL,
	`role` ENUM('admin','client') NOT NULL DEFAULT 'client',
	`login` TEXT NOT NULL,
	`password` TEXT NOT NULL,
	PRIMARY KEY (`ID`)
)
CREATE TABLE `client` (
	`ID` INT(10) UNSIGNED NOT NULL,
	`firstname` TEXT NOT NULL,
	`lastname` TEXT NOT NULL,
	PRIMARY KEY (`ID`),
	CONSTRAINT `FK_client_user` FOREIGN KEY (`ID`) REFERENCES `user` (`ID`) ON UPDATE CASCADE ON DELETE CASCADE
)

CREATE TABLE `account` (
	`ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`balance` INT(10) NOT NULL,
	`status` ENUM('open','blocked') NOT NULL DEFAULT 'open',
	PRIMARY KEY (`ID`)
)

CREATE TABLE `card` (
	`accountID` INT(10) UNSIGNED NOT NULL,
	`ownerID` INT(10) UNSIGNED NOT NULL,
	PRIMARY KEY (`accountID`),
	INDEX `FK_card_client` (`ownerID`),
	CONSTRAINT `FK_card_account` FOREIGN KEY (`accountID`) REFERENCES `account` (`ID`) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT `FK_card_client` FOREIGN KEY (`ownerID`) REFERENCES `client` (`ID`) ON UPDATE CASCADE ON DELETE CASCADE
)

CREATE TABLE `transfer` (
	`ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`fromAcc` INT(10) UNSIGNED NOT NULL,
	`toAcc` INT(10) UNSIGNED NOT NULL,
	`amount` INT(10) UNSIGNED NOT NULL,
	`date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`ID`),
	INDEX `FK_transfer_card` (`fromAcc`),
	CONSTRAINT `FK_transfer_card` FOREIGN KEY (`fromAcc`) REFERENCES `card` (`accountID`) ON UPDATE CASCADE ON DELETE CASCADE
)
