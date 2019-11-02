

insert into CUSTOMER (customer_id, name_first, name_middle, name_last, phone, status , create_on , version ) 
values 
(1, 'Peter', null, 'Pan', null, 'C', SYSTIMESTAMP, 0)
, (2, 'Mickey', null, 'Mouse', '123-242-3554-6456', 'C', SYSTIMESTAMP, 0)
,(3, 'Minnie', null, ' Mouse', '123-242-3554-6499' ,'P', SYSTIMESTAMP, 0)
,(4, 'Snow', null, ' White', '123-242-3554-676' ,'P', SYSTIMESTAMP, 0);



insert into CUSTOMER_NOTE (customer_note_id, customer_id, note, create_on , version ) 
values 
  (1, 1,  'test test 1test', SYSTIMESTAMP, 0)
, (2, 1, 'test test 2test', SYSTIMESTAMP, 0)
,(3, 3,  'test test 3test', SYSTIMESTAMP, 0)
,(4, 4, 'test test 4test', SYSTIMESTAMP, 0);