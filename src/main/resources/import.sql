-- Inserindo usuários
INSERT INTO tb_user (name, email, password) VALUES ('João Silva', 'joao.silva@example.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBa0xJ0aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Maria Oliveira', 'maria.oliveira@example.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBa0xJ0aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_role(authority) VALUES ('ROLE_ADMIN');
INSERT INTO tb_role(authority) VALUES ('ROLE_MEMBER');

INSERT INTO tb_user_role(user_id, role_id) VALUES(1,1);
INSERT INTO tb_user_role(user_id, role_id) VALUES(1,2);
INSERT INTO tb_user_role(user_id, role_id) VALUES(2,2)

-- Inserindo gêneros
INSERT INTO tb_genero (nome) VALUES ('Ação');
INSERT INTO tb_genero (nome) VALUES ('Comédia');

-- Inserindo filmes
INSERT INTO tb_filme (titulo, ano, genero_id) VALUES ('Filme de Ação', 2022, 1);
INSERT INTO tb_filme (titulo, ano, genero_id) VALUES ('Filme de Comédia', 2023, 2);

-- Inserindo reviews
INSERT INTO tb_review (texto, user_id, filme_id) VALUES ('Ótimo filme!', 1, 1);
INSERT INTO tb_review (texto, user_id, filme_id) VALUES ('Muito engraçado!', 2, 2);
