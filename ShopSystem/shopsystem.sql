create table users(
 	uid int primary key auto_increment,	
	username varchar(30),
	password varchar(30),
	name varchar(30),
	email varchar(30),
	phone varchar(30),
	addr varchar(30),
	sex char(1),
	birthday date,
	state int,
	code varchar(64)
);