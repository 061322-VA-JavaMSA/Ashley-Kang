drop table if exists employee;
drop table if exists office_location;
drop table if exists customer;

create table if not exists employee (
employee_id integer primary key,
first_name varchar(20),
last_name varchar(20),
employee_dob integer
);

create table if not exists office_location (
employee_id integer references employee(employee_id),
office_street varchar(30),
office_city varchar(30),
office_state varchar(30),
office_zip varchar(30)
);

create table if not exists customer (
employee_id integer references employee(employee_id),
customer_first varchar(30),
customer_last varchar(30)
);