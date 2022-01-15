/***********************************************************
* Create the database named hw4, its tables, and a database user
************************************************************/

DROP DATABASE IF EXISTS hw4;

CREATE DATABASE hw4;

USE hw4;

CREATE TABLE User (
  UserID INT NOT NULL AUTO_INCREMENT,
  Email VARCHAR(50),
  FirstName VARCHAR(50),
  LastName VARCHAR(50),
  Password VARCHAR(50),
  
  PRIMARY KEY(UserID) 
);

INSERT INTO User 
  (Email, FirstName, LastName, Password)
VALUES 
  ('bat@gmail.com', 'Bat', 'Man', 'bat'),
  ('spider@gmail.com', 'Spider', 'Man', 'spider'), 
  ('super@gmail.com', 'Super', 'Man', 'super');

 CREATE TABLE Book (
  BookID INT NOT NULL AUTO_INCREMENT,
  Cover VARCHAR(100),
  Title VARCHAR(50),
  Price VARCHAR(50),
  
  PRIMARY KEY(BookID) 
);

INSERT INTO Book 
  (Cover, Title, Price)
VALUES 
  ('https://cdn2.mhpbooks.com/2011/11/lotf11-320x484.png', 'Lord of the Flies', '7.35'),
  ('https://images-na.ssl-images-amazon.com/images/I/81af+MCATTL.jpg', 'The Great Gatsby', '8.74'), 
  ('https://m.media-amazon.com/images/I/516IbJ592JL.jpg', 'To Kill a Mockingbird', '7.19'),
  ('https://cdn2.penguin.com.au/covers/400/9780141036144.jpg', 'George Orwell\s 1984', '8.60');
  
 -- Create student and grant privileges

DELIMITER //
CREATE PROCEDURE drop_user_if_exists()
BEGIN
    DECLARE userCount BIGINT DEFAULT 0 ;

    SELECT COUNT(*) INTO userCount FROM mysql.user
    WHERE User = 'student' and  Host = 'localhost';

    IF userCount > 0 THEN
        DROP USER student@localhost;
    END IF;
END ; //
DELIMITER ;

CALL drop_user_if_exists() ;

CREATE USER student@localhost IDENTIFIED BY 'sesame';

GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP
ON hw4.*
TO student@localhost;

USE hw4;