Insert into chat(is_bubble, name)Values(false, 'Test');

Insert into user_in_chat Values(2, 3, false);

insert into message values (null, 'Message1', 1559829518917, 2, 1),
(null, 'Message2', 1559829518918, 2, 1),
(null, 'Message3', 1559829518919, 2, 1),
(null, 'Message4', 1559829518920, 2, 1),
(null, 'Neuste Nachricht', 1559829518921, 2, 1);

insert into message values (null, 'NeusteNachrichtTest2', 1559829618917, 2, 3),
(null, 'Nachricht', 1559829519918, 2, 3);

delete from message where message.sent_in_chat = 3;

delete from message;
delete from conversation_access_key;
delete from invite;
delete from chat;
delete from user;
delete from user_in_chat;
delete from user_read_message;

