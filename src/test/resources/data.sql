insert into account (id, amount) values (22, 42);
insert into account (id, amount) values (35, 100);
insert into account (id, amount) values (56, 8000);

insert into operation (id, account_id, amount, date) values (1, 22, 20, '2022-06-25');
insert into operation (id, account_id, amount, date) values (2, 22, 22, '2022-06-26');
insert into operation (id, account_id, amount) values (3, 56, 8000);