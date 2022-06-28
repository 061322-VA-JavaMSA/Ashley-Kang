
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
item_cost float, --minimum purchase amount
owners_id integer references customers(id));

--test item
insert into inventory (item_name,item_desc,item_cost) values ('Bouquet of Roses','A small arrangement of roses.','20');

drop table if exists bids;
create table if not exists bids(
cus_id integer references customers(id),
item_id integer references inventory(item_id),
offered float,
payments float);

--test bid
insert into bids (cus_id,item_id,offered,payments) values ('1','1','20','20');
insert into bids (cus_id,item_id,offered, payments) values ('1','2','6','6');

select * from bids join inventory on cus_id = owners_id;

update customers set user_pass = 'p4ssword' where id = 2;

update inventory set owners_id ='2' where item_id = 1;

select * from bids join inventory on cus_id = owners_id;

update inventory set owners_id = '2' where item_id = 2;

select * from inventory where owners_id = 2;


