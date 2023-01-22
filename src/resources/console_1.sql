use tfms;

update tea_suppliers
set Name      = 'a',
    ID        = 'asd',
    Address   = 'awe',
    Mobile_No = 123
where Suppliers_ID = 'T';
select *
from tea_suppliers;
insert into other_suppliers (Supplier_ID, Supplier_Type, ID, Name, Contact)
values ('S01', 'Employee', '200030501019', 'kamal', '0775249856');

select *
from other_suppliers;
select *
from other_suppliers;

SELECT *
FROM other_suppliers
ORDER BY Supplier_ID DESC
LIMIT 1;

SELECT *
FROM other_suppliers
ORDER BY CAST(SUBSTRING(Supplier_ID, 2) AS UNSIGNED) DESC
LIMIT 1;

select *
from buyers;
desc buyers;

update employeeDTO
set Type    = 'Office',
    Name    = 'saman',
    ID      = '010246',
    Address = 'matara',
    Contact = '0445879632'
where EmpID = 'E05';


insert into employeeDTO
values ('E05', 'office', 'saman', '010246', 'galle', '0445879632');
SELECT *
FROM employeeDTO
ORDER BY CAST(SUBSTRING(EmpID, 2) AS UNSIGNED);

update employeeDTO
set EmpID   = 'E01',
    Type    = 'dss',
    Name    = 'adsd',
    ID      = '546',
    Contact = 'asasas'
where EmpID = 'E01';;

insert into other_stocks (Stock_ID, Stock_Type, Qty, Price)
values ('s001', 'fertilizer', 100, 1000);
insert into other_stocks (Stock_ID, Stock_Type, Qty, Price)
values ('s002', 'bags', 52, 1000);
insert into other_stocks (Stock_ID, Stock_Type, Qty, Price)
values ('s003', 'fertilizer', 20, 1000);
select *
from other_stocks;

insert into other_suppliers (Supplier_ID, Supplier_Type, Name, ID, Contact)
values ('S001', 'fertilizer', 'Amarasekara', '25486395871v', '1234567852');
insert into other_suppliers (Supplier_ID, Supplier_Type, Name, ID, Contact)
values ('S002', 'fertilizer', 'sekara', '24586395871v', '0774567852');
insert into other_suppliers (Supplier_ID, Supplier_Type, Name, ID, Contact)
values ('S003', 'bag', 'nimal', '24458995871v', '0917567852');
select *
from other_suppliers;

desc other_suppliers_stocks;
insert into other_suppliers_stocks (Supplier_ID, Stock_ID, Date)
values ('S001', 's001', '2022-11-26');
insert into other_suppliers_stocks (Supplier_ID, Stock_ID, Date)
values ('S003', 's002', '2022-11-26');
insert into other_suppliers_stocks (Supplier_ID, Stock_ID, Date)
values ('S002', 's003', '2022-05-14');

insert into other_suppliers_stocks (Supplier_ID, Stock_ID, Date)
values ('S002', 's003', '2000-11-25');
select *
from other_suppliers_stocks;


select *
from other_stocks;
select *
from other_suppliers;
select *
from other_suppliers_stocks;

desc other_suppliers_stocks;

select date, Stock_ID, Supplier_ID
from other_suppliers_stocks;

select Name, Supplier_Type
from other_suppliers;

select Qty, Price
from other_stocks;

select other_suppliers_stocks.date,
       other_suppliers_stocks.Stock_ID,
       other_suppliers_stocks.Supplier_ID,
       Name,
       Supplier_Type,
       Qty,
       Price
from other_suppliers_stocks
         join other_suppliers os on other_suppliers_stocks.Supplier_ID = os.Supplier_ID
         join Other_Stocks O on O.Stock_ID = other_suppliers_stocks.Stock_ID;


Select Name
from other_suppliers
where Supplier_ID = 'S001';
Select Supplier_Type
from other_suppliers
where Supplier_ID = 's001';

insert into other_stocks (Stock_ID, Stock_Type, Qty, Price)
values ('s001', 'fertilizer', 100, 1000);
insert into other_suppliers_stocks (Supplier_ID, Stock_ID, Date)
values ('S001', 's001', '2022-11-26');

update other_stocks
set Stock_ID   = ?,
    Stock_Type = ?,
    Qty        = ?,
    Price      = ?
where Stock_ID = ?;

update other_suppliers_stocks
set Supplier_ID = ?,
    Stock_ID    = ?,
    Date        = ?
where Stock_ID = ?;

select *
from daily_crop;


select *
from tea_suppliers;

insert into tea_suppliers
values ('S002', 'amal', '0124','bbb','0775879696');

insert into daily_crop value ('s001','2022-11-27','50');

select name from tea_suppliers where Suppliers_ID = 'S001';

select *
from paymentDTO;

select *
from buyers;
