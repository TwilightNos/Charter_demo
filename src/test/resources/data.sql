DROP TABLE IF EXISTS REWARD;

CREATE TABLE if not exists REWARD (
    TRANSACTION_ID INT PRIMARY KEY AUTO_INCREMENT,
    CUSTOMER_ID INT NOT NULL ,
    TRANSACTION_AMOUNT INT NOT NULL ,
    TRANSACTION_DATE DATE NOT NULL
);



INSERT INTO REWARD (CUSTOMER_ID,TRANSACTION_AMOUNT,TRANSACTION_DATE)
VALUES (1,100,'2023-09-15'),
       (1,50,'2023-09-14'),
       (1,110,'2023-09-10'),
       (1,120,'2023-08-14'),
       (1,120,'2023-08-15'),
       (1,120,'2023-07-14'),
       (1,120,'2023-06-14');
