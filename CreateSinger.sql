-- OOP 2022

DROP DATABASE IF EXISTS `singer_database`;
CREATE DATABASE `singer_database`;
USE `singer_database`;
DROP TABLE IF EXISTS `singer`;
CREATE TABLE `singer` (
                        `SINGER_ID` int(11) NOT NULL AUTO_INCREMENT,
                        `NAME` varchar(50) NOT NULL,
                        `DOB` DATE NOT NULL,
                        `RATE` double(15) NOT NULL,
                        `GENRE` varchar(20) NOT NULL,
                        PRIMARY KEY  (`SINGER_ID`)
);

INSERT INTO singer VALUES (null, "Mark Lanegan", "1954-12-03", 2000.00, "rock"),
                        (null, "Kylie Minogue", "1965-12-03", 3000.00, "pop"),
                        (null, "Declan Nerney", "1975-12-03", 4000.00, "country"),
                        (null, "Alice in Chains", "1993-12-03", 12000.00, "grunge"),
                        (null, "Mattiel", "2000-12-04", 14000.00, "pop"),
                          (null, "King of Leon", "1999-12-05", 5000.00, "rock"),
                          (null, "Royal Blood", "1983-12-03", 6000.00, "metal"),
                          (null, "Last Hope", "1986-12-03", 7000.00, "hard-core"),
                          (null, "Odd Crew", "1993-12-03", 8000.00, "metal"),
                          (null, "PJ Harvey", "1974-12-03", 9000.00, "pop-rock");
