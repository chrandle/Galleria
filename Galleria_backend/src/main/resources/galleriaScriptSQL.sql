/************************************
 * Galleria DB Set UP
 * Script Project_1_DB_BuildScript.sql
 * Description: Creates a simple art gallery site
 * DB Server: Local PostGreSQL - GalleriaDB
 * Author: Christian Randle
************************************/
DROP SCHEMA public CASCADE;
CREATE SCHEMA public;
--drop table if exists Requests;
--drop table if exists Accounts;
--drop table if exists Users;


create table users
(
	userID bigserial unique not null, 
	userName varchar(180) unique not null,
	email varchar (180) unique not null,
	"password" varchar(180) not null,
	constraint Users_PK primary key (UserID,userName, "password"),
);

create table images(
	imageID  bigserial unique not null, 
	title varchar(180) not null,
	creationDate date not null,
	src varchar(360) not null,
	author varchar(180) not null,
	userid bigserial  references Users (UserID) on delete cascade,
	constraint images_PK primary key (ImageID)
);



create or replace function user_by_id(uid users.userid %type) returns setof Users as $$
	select * from users u2 where u2.userid = uid
$$ language sql;




create or replace function 
create_user(
	fn users.firstName %type,ln users.lastName %type,
	ue users.email %type, un users.username %type, pw users."password" %type
) returns bigint  as $$
	insert into users (firstName ,lastName, email,username ,"password" )
	values (fn,ln,un,ue,pw)
	returning userid
$$ language sql;




create or replace function delete_user(ui users.userid %type) returns void as $$
	delete from images a2 where a2.userid=ui;
	delete from users where userid = ui;
$$ language sql;


/* -----------------------------------------------------
*			Set Up test data
-----------------------------------------------------*/ 


select create_user('Bobby', 'brown','beebee','bb@google.com', 'yuppers');
select create_user('Billy', 'brown','tubular','radical@google.com', 'yuppers');
select create_user('Ted', 'brown','radical','tubular@google.com', 'yuppers');



