CREATE TABLE IF NOT EXISTS tb_review (
   id bigint NOT NULL auto_increment,
  texto LONGTEXT,
  filme_id bigint not null,
  user_id bigint not null,
  CONSTRAINT PK_tb_review primary key (id),
  CONSTRAINT FK_tb_review_filme FOREIGN KEY(filme_id)  REFERENCES tb_filme(id),
  CONSTRAINT FK_tb_review_user FOREIGN KEY(user_id)  REFERENCES tb_user(id)
);