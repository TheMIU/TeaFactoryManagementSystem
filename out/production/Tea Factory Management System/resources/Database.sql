drop database tfms;
create database tfms;
USE tfms;

CREATE TABLE IF NOT EXISTS tea_suppliers
(
    Suppliers_ID VARCHAR(10) NOT NULL UNIQUE,
    Name         VARCHAR(45) NOT NULL,
    ID           VARCHAR(12) NOT NULL UNIQUE,
    Address      VARCHAR(45) NOT NULL,
    Mobile_No    VARCHAR(10) NULL DEFAULT NULL,
    PRIMARY KEY (Suppliers_ID)
);

insert into tea_suppliers (Suppliers_ID, Name, ID, Address, Mobile_No)
values ('TS01', 'Kamal', '738060938v','Halakare', '0773572070');
insert into tea_suppliers (Suppliers_ID, Name, ID, Address, Mobile_No)
values ('TS02', 'Amal', '738080938v','Nawngala', '0774562070');
insert into tea_suppliers (Suppliers_ID, Name, ID, Address, Mobile_No)
values  ('TS03', 'Saman', '738120938v','Kottawagama', '0774545070');

CREATE TABLE IF NOT EXISTS daily_crop
(
    Suppliers_ID VARCHAR(10)    NOT NULL,
    Date         DATE           NOT NULL,
    NetWeight    DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (Suppliers_ID, Date),
    CONSTRAINT FOREIGN KEY (Suppliers_ID)
        REFERENCES tea_suppliers (Suppliers_ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

insert into daily_crop (Suppliers_ID, Date, NetWeight)
values ('TS01','2012-12-12',50);
insert into daily_crop (Suppliers_ID,Date, NetWeight)
values ('TS02','2012-12-12',48);


CREATE TABLE IF NOT EXISTS tea_stock
(
    Stock_ID       VARCHAR(10)    NOT NULL,
    Type           VARCHAR(10)    NOT NULL,
    Input_Date     DATE           NOT NULL,
    One_bag_Weight DECIMAL(10, 2) NOT NULL,
    Qty            INT            NOT NULL,
    AvailableQty   INT            NOT NULL,
    PRIMARY KEY (Stock_ID,Type)
);

insert into tea_stock (Stock_ID, Type, Input_Date, One_bag_Weight, Qty,AvailableQty)
values ('TI01','BOP','2012-01-01',500,50,20);
insert into tea_stock (Stock_ID, Type, Input_Date, One_bag_Weight, Qty,AvailableQty)
values ('TI03','FBOP','2012-05-01',400,10,4);
insert into tea_stock (Stock_ID, Type, Input_Date, One_bag_Weight, Qty,AvailableQty)
values ('TI03','P','2012-05-01',250,20,8);
insert into tea_stock (Stock_ID, Type, Input_Date, One_bag_Weight, Qty,AvailableQty)
values ('TI03','OP','2012-05-01',250,15,5);
insert into tea_stock (Stock_ID, Type, Input_Date, One_bag_Weight, Qty,AvailableQty)
values ('TI03','FOP','2012-05-01',400,10,6);
insert into tea_stock (Stock_ID, Type, Input_Date, One_bag_Weight, Qty,AvailableQty)
values ('TI03','GFOP','2012-05-01',400,50,2);
insert into tea_stock (Stock_ID, Type, Input_Date, One_bag_Weight, Qty,AvailableQty)
values ('TI03','FTGFOP','2012-05-01',400,11,7);
insert into tea_stock (Stock_ID, Type, Input_Date, One_bag_Weight, Qty,AvailableQty)
values ('TI03','TGFOP','2012-05-01',400,10,9);

CREATE TABLE IF NOT EXISTS buyers
(
    Buyer_ID VARCHAR(10) NOT NULL,
    Name     VARCHAR(45) NOT NULL,
    Address  VARCHAR(45) NULL DEFAULT 'unknown',
    Contact  VARCHAR(10) NOT NULL,
    PRIMARY KEY (Buyer_ID)
);

insert into buyers (Buyer_ID, Name, Address, Contact)
values ('B01','Abdul','colombo','0779874563');
insert into buyers (Buyer_ID, Name, Address, Contact)
values ('B02','Nanayakkar','colombo','0779874563');

CREATE TABLE IF NOT EXISTS tea_buyers
(
    Stock_ID VARCHAR(10) NOT NULL,
    Qty      INT         NOT NULL,
    Buyer_ID VARCHAR(10) NOT NULL,
    Date     DATE        NULL DEFAULT NULL,
    PRIMARY KEY (Stock_ID, Buyer_ID),
    CONSTRAINT FOREIGN KEY (Buyer_ID)
        REFERENCES buyers (Buyer_ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (Stock_ID)
        REFERENCES tea_stock (Stock_ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

insert into tea_buyers (Stock_ID, Qty, Buyer_ID, Date)
values ('TI01',50,'B01','2019-11-05');
insert into tea_buyers (Stock_ID, Qty, Buyer_ID, Date)
values ('TI02',60,'B02','2019-11-05');

/*CREATE TABLE IF NOT EXISTS vehicals
(
    Vehical_ID INT         NOT NULL,
    type       VARCHAR(45) NULL DEFAULT NULL,
    Brand      VARCHAR(45) NULL DEFAULT NULL,
    PRIMARY KEY (Vehical_ID)
);
*/
CREATE TABLE IF NOT EXISTS employeeDTO
(
    EmpID   VARCHAR(10) NOT NULL,
    Type    VARCHAR(45) NOT NULL,
    Name    VARCHAR(45) NOT NULL,
    ID      VARCHAR(12) NOT NULL,
    Address VARCHAR(45) NULL DEFAULT NULL,
    Contact VARCHAR(10) NULL DEFAULT NULL,
    PRIMARY KEY (EmpID)
);

insert into employeeDTO (EmpID, Type, Name, ID, Address, Contact)
values ('E01','office','Kamal','200035789563','galle','0773698521');
insert into employeeDTO (EmpID, Type, Name, ID, Address, Contact)
values ('E02','director','Nimal','200035784563','galle','0774598521');

CREATE TABLE IF NOT EXISTS Other_Stocks
(
    Stock_ID   VARCHAR(10)   NOT NULL,
    Stock_Type VARCHAR(45),
    Qty        INT           NOT NULL,
    Price      DOUBLE(10, 2) NOT NULL,
    PRIMARY KEY (Stock_ID)
);

insert into other_stocks (Stock_ID, Stock_Type, Qty, Price)
values ('I01','fertilizer',150,6200);
insert into other_stocks (Stock_ID, Stock_Type, Qty, Price)
values ('I02','bags',25,1000);

CREATE TABLE IF NOT EXISTS other_suppliers
(
    Supplier_ID   varchar(10) NOT NULL,
    Supplier_Type VARCHAR(45) NOT NULL,
    Name          VARCHAR(45) NOT NULL,
    ID            VARCHAR(12) NOT NULL UNIQUE,
    Contact       varchar(10) NOT NULL,
    PRIMARY KEY (Supplier_ID)
);

insert into other_suppliers (Supplier_ID, Supplier_Type, Name, ID, Contact)
values ('S01','fertilizer','Kumarasiri','356987412v','0785469321');
insert into other_suppliers (Supplier_ID, Supplier_Type, Name, ID, Contact)
values ('S02','firewood','Nandasena','354567412v','0784896321');
insert into other_suppliers (Supplier_ID, Supplier_Type, Name, ID, Contact)
values ('S03','bags','Jinadasa','357867412v','0784636321');


CREATE TABLE IF NOT EXISTS other_suppliers_stocks
(
    Date        VARCHAR(10) Not NULL,
    Stock_ID    VARCHAR(10) NOT NULL,
    Supplier_ID VARCHAR(10) NOT NULL,
    CONSTRAINT PRIMARY KEY (Supplier_ID, Stock_ID),
    CONSTRAINT FOREIGN KEY (Stock_ID)
        REFERENCES Other_Stocks (Stock_ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (Supplier_ID)
        REFERENCES other_suppliers (Supplier_ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

insert into other_suppliers_stocks (Date, Stock_ID, Supplier_ID)
values ('2015-5-5','I01','S01');
insert into other_suppliers_stocks (Date, Stock_ID, Supplier_ID)
values ('2015-7-7','I02','S01');
insert into other_suppliers_stocks (Date, Stock_ID, Supplier_ID)
values ('2015-7-7','I01','S02');

CREATE TABLE IF NOT EXISTS paymentDTO
(
    Payment_ID  VARCHAR(10)    NOT NULL,
    Date        DATE           NOT NULL,
    Reason      VARCHAR(45)    NOT NULL,
    Amount      DECIMAL(10, 2) NOT NULL,
    Method      VARCHAR(45)    NOT NULL,
    Type        VARCHAR(10)    NOT NULL,
    Buyer_ID    VARCHAR(10) DEFAULT NULL,
    EmpID       VARCHAR(10) DEFAULT NULL,
    Supplier_ID VARCHAR(10) DEFAULT NULL,
    PRIMARY KEY (Payment_ID),
    CONSTRAINT FOREIGN KEY (Supplier_ID)
        REFERENCES other_suppliers (Supplier_ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (EmpID)
        REFERENCES employeeDTO (EmpID)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (Buyer_ID)
        REFERENCES buyers (Buyer_ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

insert into paymentDTO (Payment_ID, Date,Reason, Amount, Method, Type, Buyer_ID, EmpID, Supplier_ID)
values ('P01','2010-7-8','tea sell','25000','cash','credit','B01',null,null);
insert into paymentDTO (Payment_ID, Date,Reason, Amount, Method, Type, Buyer_ID, EmpID, Supplier_ID)
values ('P02','2010-8-10','paymentDTO','50000','cash','debit',null,null,'S02');
insert into paymentDTO (Payment_ID, Date,Reason, Amount, Method, Type, Buyer_ID, EmpID, Supplier_ID)
values ('P03','2010-8-10','paymentDTO','20000','cash','debit',null,'E01',null);

CREATE TABLE IF NOT EXISTS users(
    type varchar(10),
    name varchar(10),
    password varchar(8)
);

