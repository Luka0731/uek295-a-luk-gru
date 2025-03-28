
INSERT INTO author (author_id, name, country, birthday)
VALUES ('398245e0-8b9e-42f1-8603-feaa8703fc97', 'Stephen King', 'USA', '1947-09-21'),
       ('d4e45670-e89b-12d3-a456-426614174003', 'Agatha Christie', 'UK', '1890-09-15'),
       ('e5e45670-e89b-12d3-a456-426614174004', 'George R.R. Martin', 'USA', '1948-09-20');


INSERT INTO book (book_id, title, author_id, price,  language)
VALUES ('a40220c0-5e09-479d-8542-c1b5277b452f', 'Der Herr der Ringe', '398245e0-8b9e-42f1-8603-feaa8703fc97', 24.99, 'Deutsch'),
       ('201e4567-e89b-12d3-a456-426614174008', 'Mord im Orient Express', 'd4e45670-e89b-12d3-a456-426614174003', 11.99, 'Deutsch'),
       ('202e4567-e89b-12d3-a456-426614174009', 'Das unheimliche Tal', 'd4e45670-e89b-12d3-a456-426614174003', 10.50, 'Deutsch'),
       ('203e4567-e89b-12d3-a456-426614174010', 'A Game of Thrones', 'e5e45670-e89b-12d3-a456-426614174004', 22.99, 'Englisch'),
       ('204e4567-e89b-12d3-a456-426614174011', 'Feuer und Blut', 'e5e45670-e89b-12d3-a456-426614174004', 19.99, 'Deutsch');
