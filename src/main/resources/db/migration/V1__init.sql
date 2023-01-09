
-- CREATE TABLE stock (
--                         ID INT NOT NULL AUTO_INCREMENT,
--                         STOCK_ID VARCHAR(255) not null,
--                         primary key (`ID`)
-- );


CREATE TABLE quote (
                       ID INT AUTO_INCREMENT NOT NULL,
                       STOCK_ID VARCHAR(255) not null,
                       DATE TIMESTAMP not null,
                       PRICE DECIMAL not null,
                       primary key (`ID`)
);






