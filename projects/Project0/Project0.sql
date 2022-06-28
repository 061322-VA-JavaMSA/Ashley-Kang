
/*
 * Customer Table
 * id
 * email
 * username
 * password
 * name
 * card number 
 */
drop table if exists customers;
create table if not exists customers(
id serial primary key,
username varchar(30) unique not null,
user_pass varchar(30) not null,
email varchar(30) not null,
cus_name varchar(30),
card_num varchar(16));


/*
 * Employee Table
 * id
 * email
 * username
 * password
 * name
 * card number 
 */

drop table if exists employees;
create table if not exists employees(
id serial primary key,
username varchar(30) unique not null,
user_pass varchar(30) not null,
email varchar(30) not null,
emp_name varchar(30),
auth_num varchar(16));



--Managers
drop table if exists managers;
create table if not exists managers(
id serial primary key,
username varchar(30) unique not null,
user_pass varchar(30) not null,
email varchar(30) not null,
man_name varchar(30),
auth_key varchar(16));

--test customer
insert into customers (username,user_pass,email,cus_name,card_num) values ('firstTest','p4ssword','firstTest@customer.com','firstCustomer','123456789');

--test employee
insert into employees (username,user_pass,email,emp_name,auth_num) values ('firstTest','p4ssword','firstTest@employee.com','firstEmployee','123456789');


/*
 * Inventory
 * item name
 * item description
 * id num
 * ownersid
 * cost
 */
drop table if exists inventory;
create table if not exists inventory(
item_id serial primary key,
item_name varchar(30),
item_desc varchar(100),
item_cost float,
owners_id integer references customers(id));

--test item
insert into inventory (item_name,item_desc,item_cost) values ('Bouquet of Roses','A small arrangement of roses.','20');
insert into inventory (item_name,item_desc,item_cost) values ('Bouquet of Lillies','A small arrangement of Lillies.','20');

drop table if exists bids;
create table if not exists bids(
bcus_id integer references customers(id),
bitem_id integer references inventory(item_id),
offered float,
payments float);

--test bid
insert into bids (bcus_id,bitem_id,offered, payments) values ('1','1','20','20');
insert into bids (bcus_id,bitem_id,offered, payments) values ('2','2','6', '6');

insert into inventory (item_name,item_desc,item_cost) values ('1','1','1');

select * from bids join customers on cus_id = id;

select * from bids where item_id=2;




--SQL Statements
select * from bids join inventory on cus_id = owners_id;
update customers set user_pass = 'p4ssword' where id = 2;
update inventory set owners_id ='2' where item_id = 1;
select * from bids join inventory on cus_id = owners_id;
update inventory set owners_id = '2' where item_id = 2;
select * from inventory where owners_id = 2;
update inventory set owners_id = 1 where item_id = 1;
update inventory set owners_id = 1 where item_id = 2;


--Statements for demo
insert into customers (username,user_pass,email,cus_name,card_num) values ('Hannah','password','Hannaht@customer.com','Hannah','123456789');
insert into customers (username,user_pass,email,cus_name,card_num) values ('Ashley','password','Ashley@customer.com','Ashley','123456789');
insert into employees (username,user_pass,email,emp_name) values ('Employee','password','employee@employee.com','Employee');
insert into inventory (item_name,item_desc,item_cost) values ('Bouquet of Roses','A small arrangement of roses.','10');
insert into inventory (item_name,item_desc,item_cost) values ('Bouquet of Lillies','A small arrangement of Lillies.','15');
insert into inventory (item_name,item_desc,item_cost) values ('Bouquet of Azaleas','A small arrangement of Azaleas.','18');
insert into inventory (item_name,item_desc,item_cost) values ('Mixed Flower Bouquet','A small arrangement of Mixed Flowers.','24');
insert into managers (username,user_pass,email,man_name) values ('testManager','password','manager@manager.com','Manager');
insert into bids (bcus_id,bitem_id,offered) values ('9','22','10');
insert into bids (bcus_id,bitem_id,offered, payments) values ('9','25','24','0');
insert into bids (bcus_id,bitem_id,offered) values ('10','22','10');
insert into bids (bcus_id,bitem_id,offered) values ('10','24','18');
insert into bids (bcus_id,bitem_id,offered, payments) values ('10','23','15', '0');
