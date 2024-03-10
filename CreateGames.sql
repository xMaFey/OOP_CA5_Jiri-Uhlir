CREATE DATABASE IF NOT EXISTS CA3;
Use CA3

DROP TABLE IF EXISTS games;

CREATE TABLE games(id int NOT NULL AUTO_INCREMENT,
                   gameTitle VARCHAR(40),
                   developer VARCHAR(20),
                   price int(6),
                   gbOfSpace float(4),
                   releaseDate date,
                   PRIMARY KEY (id));

INSERT INTO games VALUES (101, 'Super Mario Bros. Wonder', 'Nintendo', 50, 4.5,'2023-11-20' );
INSERT INTO games VALUES (107, 'Resident Evil 4 Remake', 'Capcom', 40, 67, '2023-3-24');
INSERT INTO games VALUES (105, 'Grand Theft Auto V', 'Rockstar', 30, 110,'2013-9-17');
INSERT INTO games VALUES (106, 'Portal 2', 'Valve', 10, 8,'2011-4-19');
INSERT INTO games VALUES (108, 'Minecraft', 'Mojang', 15, 2,'2011-11-18');
INSERT INTO games VALUES (109, 'God of war', 'Santa Monica Studio', 30, 70,'2018-4-20');
INSERT INTO games VALUES (110, 'The Legend of Zelda: Breath of the Wild', 'Nintendo',60, 14.4, '2017-3-3');
INSERT INTO games VALUES (111, 'The Elder Scrolls V: Skyrim', 'Bethesda', 55, 12, '2011-11-11');
INSERT INTO games VALUES (112, 'Undertale', 'Toby Fox', 10, 0.2, '2015-9-15');
INSERT INTO games VALUES (113, 'Shadow of the Colossus', 'Team Ico', 30, 14,'2005-10-18');