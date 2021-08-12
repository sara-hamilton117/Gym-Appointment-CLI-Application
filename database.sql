--DROP DATABASE gym;

CREATE DATABASE gym;

USE gym;

FLUSH PRIVILEGES;

DROP USER 'admin'@'localhost';

CREATE USER 'admin'@'localhost' IDENTIFIED BY 'root';

GRANT ALL PRIVILEGES ON * . * TO 'admin'@'localhost';

FLUSH PRIVILEGES;

CREATE TABLE client(
client_id VARCHAR(5) NOT NULL,
client_name CHAR (50),
client_sex ENUM('M','F'),
client_dob DATE,
client_email VARCHAR(50),
client_no VARCHAR(20),
client_address1 VARCHAR(50),
client_address2 VARCHAR(50),
PRIMARY KEY (client_id)
);

INSERT INTO client VALUES 
('es001','Emma Smith', 'F', '1997-04-12','emmasmith@mail.com','078 1264 8948','55  New Dover Rd','Walkington'),
('lj001','Liam Johnson','M','1992-08-29','liamjohnson@mail.com','070 1976 5031','92  Nenthead Road','Higham'),
('ow001','Olivia Williams','F','2000-07-12','oliviawilliams@mail.com','070 6565 9282','71  Stone Cellar Road','Kingston'),
('nj001','Noah Jones','M','1987-04-05','noahjones@mail.com','079 7744 2355','106  Station Rd','Jameston'),
('ab001','Ava Brown','F','1982-08-23','avabrown@mail.com','077 3080 3854','42  Neville Street','Imachar'),
('wd001','William Davis','M','1994-09-15','williamdavis@mail.com','078 4843 4354','108 Thames Street','Boothtown');

CREATE TABLE personal_trainer(
pt_id VARCHAR(5) NOT NULL,
pt_name CHAR (50),
pt_sex ENUM('M','F'),
pt_dob DATE,
pt_email VARCHAR(50),
pt_no VARCHAR(20),
pt_address1 VARCHAR(50),
pt_address2 VARCHAR(50),
PRIMARY KEY (pt_id)
);

INSERT INTO personal_trainer VALUES
('001sm','Sophia Miller','F','1990-01-02','sophiamiller@gymail.com','077 7601 1544','94  Overton Circle','Liverton'),
('001jw','James Wilsen','M','1982-05-29','jameswilsen@gymail.com','078 0551 4407','86  Layburn Court','Onecote'),
('001ot','Oliver Thomas','M','1993-01-09','oliverthomas@gymail.com','070 2384 6126','133  Ivy Lane','Walwick');


CREATE TABLE specialty(
specialty_id VARCHAR(5) NOT NULL,
specialty_name VARCHAR(25),
specialty_duration INT,
PRIMARY KEY (specialty_id)
);

INSERT INTO specialty VALUES
('sp01','Weight Loss',60),
('sp02','Flexibility',45),
('sp03','Muscle Gain',90);

CREATE TABLE trainer_specialty(
pt_id VARCHAR(5) NOT NULL,
specialty_id VARCHAR(5) NOT NULL,
PRIMARY KEY (pt_id, specialty_id),
FOREIGN KEY (pt_id) REFERENCES personal_trainer (pt_id),
FOREIGN KEY (specialty_id) REFERENCES specialty (specialty_id)
);

INSERT INTO trainer_specialty VALUES
('001ot','sp01'),
('001ot','sp02'),
('001ot','sp03'),
('001sm','sp01'),
('001sm','sp03'),
('001jw','sp02');

CREATE TABLE booking(
booking_id INT NOT NULL AUTO_INCREMENT,
client_id VARCHAR(5) NOT NULL,
pt_id VARCHAR(5) NOT NULL,
booking_date DATE NOT NULL,
booking_time TIME NOT NULL,
booking_duration INT,
specialty_id VARCHAR(5) NOT NULL,
PRIMARY KEY (booking_id),
FOREIGN KEY (pt_id) REFERENCES trainer_specialty (pt_id),
FOREIGN KEY (specialty_id) REFERENCES trainer_specialty (specialty_id)
);

INSERT INTO booking VALUES
(DEFAULT, 'es001', '001ot', '2019-12-20', '12:15:00', 60, 'sp01'),
(DEFAULT, 'lj001', '001ot', '2019-12-21', '09:45:00', 45, 'sp02'),
(DEFAULT, 'ow001', '001sm', '2019-12-22', '10:00:00', 60, 'sp01'),
(DEFAULT, 'nj001', '001sm', '2019-12-23', '11:30:00', 90, 'sp03'),
(DEFAULT, 'ab001', '001jw', '2019-12-24', '12:45:00', 45, 'sp02'),
(DEFAULT, 'wd001', '001jw', '2019-12-25', '13:00:00', 45, 'sp02'),
(DEFAULT, 'es001', '001sm', '2019-12-26', '16:30:00', 90, 'sp03'),
(DEFAULT, 'lj001', '001jw', '2019-12-27', '18:00:00', 45, 'sp02');


