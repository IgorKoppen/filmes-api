CREATE TABLE IF NOT EXISTS tb_user (
    id bigint NOT NULL auto_increment,
    email varchar(50),
    name varchar(150),
    password varchar(15),
    CONSTRAINT PK_tb_user PRIMARY KEY (id)
);
