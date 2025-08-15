CREATE DATABASE lostfound;

USE lostfound;

CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  email VARCHAR(100) UNIQUE,
  password VARCHAR(100)
);

CREATE TABLE items (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT,
  type ENUM('lost', 'found'),
  category VARCHAR(100),
  description TEXT,
  location VARCHAR(100),
  date DATE,
  FOREIGN KEY (user_id) REFERENCES users(id)
);
select * from users;
select * from items;
desc  users;
desc items;