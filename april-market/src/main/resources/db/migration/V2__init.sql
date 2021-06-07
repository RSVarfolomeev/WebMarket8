insert into roles (name)
values
('ROLE_USER'),
('ROLE_ADMIN');

insert into users (username, password, email)
values
('bob1', '$2y$12$yr/ojWL6JQCpku.5vlcGuuIAkROTMOkIupVUzzRMqQvBqpUgn4xA2', 'bob1@gmail.com'),
('bob2', '$2y$12$yr/ojWL6JQCpku.5vlcGuuIAkROTMOkIupVUzzRMqQvBqpUgn4xA2', 'bob2@gmail.com');

insert into users_roles (user_id, role_id)
values
(1, 1),
(2, 2);

insert into products (title, price)
values
('Banana', 50.00),
('Something', 99999.99),
('Bread', 39.99),
('Apple', 129.99),
('Cherry', 349.20),
('Milk', 89.90),
('Potato', 90.00),
('Tomato', 169.70),
('Melon', 120.00),
('Cookie', 80.00),
('Notebook', 180000.00),
('Phone', 50000.00),
('Monitor', 25000.00),
('GeekBrains course', 40000.00),
('Video game', 1599.00),
('Fish', 320.00),
('TV', 40000.00),
('Shower', 200.00),
('Meat', 500);


insert into categories
(title, description) values
('food', 'some food products'),
('fruits', 'some fruit'),
('vegetables', 'some vegetables'),
('electronics', 'some electronics'),
('non-food', 'all non-food products');
