GRANT ALL PRIVILEGES ON *.* TO 'melwan'@'%' IDENTIFIED BY 'melwan123';
FLUSH PRIVILEGES;

CREATE DATABASE IF NOT EXISTS products_db;

use products_db;

CREATE TABLE IF NOT EXISTS products_db.categories (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  description varchar(200) DEFAULT NULL,
  created_at datetime NOT NULL,
  updated_at datetime NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS products_db.products (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  price decimal(10,0) NOT NULL,
  description varchar(200) DEFAULT NULL,
  created_at datetime NOT NULL,
  updated_at datetime NOT NULL,
  category_id int(11) NOT NULL,
  PRIMARY KEY (id),
  KEY fk_products_1_idx (category_id),
  CONSTRAINT fk_products_1 FOREIGN KEY (category_id) REFERENCES categories (id) ON UPDATE NO ACTION
);