insert into user(email, password, nickname, role, created) values ('admin@mail.com', '$2a$10$TECvjGYX.A0VQVnGfXFGnupV9Wld8bvrZxWgbTZJsu6Oh3UqkPLrC', 'admin', 'ADMIN', now());
insert into user(email, password, nickname, role, created) values ('manager@mail.com', '$2a$10$TECvjGYX.A0VQVnGfXFGnupV9Wld8bvrZxWgbTZJsu6Oh3UqkPLrC', 'manager', 'MANAGER', now());
insert into user(email, password, nickname, role, created) values ('user1@mail.com', '$2a$10$TECvjGYX.A0VQVnGfXFGnupV9Wld8bvrZxWgbTZJsu6Oh3UqkPLrC', 'user1', 'USER', now());
insert into user(email, password, nickname, role, created) values ('user2@mail.com', '$2a$10$TECvjGYX.A0VQVnGfXFGnupV9Wld8bvrZxWgbTZJsu6Oh3UqkPLrC', 'user2', 'USER', now());
insert into user(email, password, nickname, role, created) values ('user3@mail.com', '$2a$10$TECvjGYX.A0VQVnGfXFGnupV9Wld8bvrZxWgbTZJsu6Oh3UqkPLrC', 'user3', 'USER', now());
insert into user(email, password, nickname, role, created) values ('user4@mail.com', '$2a$10$TECvjGYX.A0VQVnGfXFGnupV9Wld8bvrZxWgbTZJsu6Oh3UqkPLrC', 'user4', 'USER', now());
insert into user(email, password, nickname, role, created) values ('user5@mail.com', '$2a$10$TECvjGYX.A0VQVnGfXFGnupV9Wld8bvrZxWgbTZJsu6Oh3UqkPLrC', 'user5', 'USER', now());
insert into user(email, password, nickname, role, created) values ('user6@mail.com', '$2a$10$TECvjGYX.A0VQVnGfXFGnupV9Wld8bvrZxWgbTZJsu6Oh3UqkPLrC', 'user6', 'USER', now());
insert into user(email, password, nickname, role, created) values ('user7@mail.com', '$2a$10$TECvjGYX.A0VQVnGfXFGnupV9Wld8bvrZxWgbTZJsu6Oh3UqkPLrC', 'user7', 'USER', now());
insert into user(email, password, nickname, role, created) values ('user8@mail.com', '$2a$10$TECvjGYX.A0VQVnGfXFGnupV9Wld8bvrZxWgbTZJsu6Oh3UqkPLrC', 'user8', 'USER', now());
insert into user(email, password, nickname, role, created) values ('user9@mail.com', '$2a$10$TECvjGYX.A0VQVnGfXFGnupV9Wld8bvrZxWgbTZJsu6Oh3UqkPLrC', 'user9', 'USER', now());
insert into user(email, password, nickname, role, created) values ('user10@mail.com', '$2a$10$TECvjGYX.A0VQVnGfXFGnupV9Wld8bvrZxWgbTZJsu6Oh3UqkPLrC', 'user10', 'USER', now());
insert into board_category(id, name, created, created_by) values (1, '경제', now(), 1);
insert into board_category(id, name, created, created_by) values (2, '문화', now(), 1);
insert into board_category(id, name, created, created_by) values (3, '스포츠', now(), 1);


insert into comment(id, content, created, user_id, board_id) values(1, 'test1', now(), 3, 3);
insert into comment(id, content, created, user_id, board_id) values(2, 'test2', now(), 4, 3);
insert into comment(id, content, created, user_id, board_id) values(3, 'test3', now(), 5, 3);
insert into comment_like(comment_id, user_id, likeability, created, created_by, updated, updated_by) values(1, 4, now(), 4, now(), 4);
insert into comment_like(comment_id, user_id, likeability, created, created_by, updated, updated_by) values(1, 5, now(), 5, now(), 5);
insert into comment_like(comment_id, user_id, likeability, created, created_by, updated, updated_by) values(-1, 6, now(), 6, now(), 6);