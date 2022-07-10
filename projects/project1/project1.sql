drop type ticket_type;
drop type user_type;
drop type ticket_status;

create type ticket_type as enum ('LODGING', 'TRAVEL', 'FOOD', 'OTHER');
create type user_type as enum ('EMPLOYEE', 'MANAGER');
create type ticket_status as enum ('APPROVED', 'PENDING', 'DENIED');


drop table if exists users;
create table if not exists users(
user_id serial primary key,
user_name varchar(30),
username varchar(30) unique not null,
user_pass varchar(30) not null,
user_role user_type
);



--populate users table
insert into users(user_name,username,user_pass,user_role) values ('Olivia','Olivia89','password','EMPLOYEE');
insert into users(user_name,username,user_pass,user_role) values ('Matthew','Mat673','password','EMPLOYEE');
insert into users(user_name,username,user_pass,user_role) values ('Ashley','Ashley99','password','MANAGER');
insert into users(user_name,username,user_pass,user_role) values ('Lucas','Lucas32','password','MANAGER');



drop table if exists tickets;
create table if not exists tickets(
ticket_id serial primary key,
ticket_ty ticket_type,
ticket_desc varchar(150),
ticket_amount float,
employee_id integer references users(user_id),
manager_id integer references users(user_id),
ticket_stat ticket_status
);


--populate tickets table
insert into tickets(ticket_ty,ticket_desc,ticket_amount,employee_id, manager_id, ticket_stat) values ('FOOD','Bought McDonalds', '10.67', '1', '3', 'APPROVED');
insert into tickets(ticket_ty,ticket_desc,ticket_amount,employee_id, ticket_stat) values ('TRAVEL','Drove 100 Miles', '500', '1','PENDING');
insert into tickets(ticket_ty,ticket_desc,ticket_amount,employee_id, manager_id, ticket_stat) values ('OTHER','Bought a smartwatch', '50', '2', '4', 'DENIED');