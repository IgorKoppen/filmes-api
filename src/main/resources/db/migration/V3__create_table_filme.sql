CREATE TABLE IF NOT EXISTS tb_filme (
    id bigint NOT NULL auto_increment,
    titulo varchar(150),
    ano integer,
    genero_id bigint not null,
   CONSTRAINT PK_tb_filme PRIMARY KEY (id),
   CONSTRAINT FK_tb_filme_genero FOREIGN KEY(genero_id) REFERENCES tb_genero(id)
);