Insert into chat(is_bubble, name)Values(false, 'Test');

Insert into user_in_chat Values(2, 1, false);


insert into message values (null, 'Message1', 1559829518917, 2, 1),
(null, 'Message2', 1559829518918, 2, 1),
(null, 'Message3', 1559829518919, 2, 1),
(null, 'Message4', 1559829518920, 2, 1),
(null, 'Neuste Nachricht', 1559829518921, 2, 1);

insert into message values (null, 'Neuste Nachricht2', 1559831546258, 2, 1);

delete from message;
