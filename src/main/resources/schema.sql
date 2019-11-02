
drop  table if exists CUSTOMER ;
drop table  if exists CUSTOMER_NOTE;

create table CUSTOMER (
customer_id int auto_increment primary key,
name_first NVARCHAR2(255 char) not null,
name_middle NVARCHAR2(255 char) default null,
name_last NVARCHAR2(255 char) not null,
phone NVARCHAR2(20 char) default null,
status char(1) not null default 'P',
create_on TIMESTAMP default SYSTIMESTAMP,
version int(11) not null default 0
);

create table CUSTOMER_NOTE (
  customer_note_id int auto_increment primary key,
  customer_id number(64) not null,
  note NVARCHAR2,
  create_on TIMESTAMP  default SYSTIMESTAMP,
  version int not null default 0,
   FOREIGN KEY (customer_id) REFERENCES customer(customer_id)

);