drop table if exists user;
CREATE TABLE IF NOT EXISTS `user`(
`id` INT UNSIGNED AUTO_INCREMENT,
`username` VARCHAR(20) NOT NULL,
`name` VARCHAR(20) NOT NULL,
`age` INT(3),
`balance` decimal(10,2),
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into user (id, username, name, age, balance) values (1, 'account1', '张三', 20, 100.00);
insert into user (id, username, name, age, balance) values (2, 'account2', '李四', 28, 180.00);
insert into user (id, username, name, age, balance) values (3, 'account3', '王五', 32, 280.00);