-- Inserindo usuários
INSERT INTO tb_user (name, email, password) VALUES ('João Silva', 'joao.silva@example.com', 'senha123');
INSERT INTO tb_user (name, email, password) VALUES ('Maria Oliveira', 'maria.oliveira@example.com', 'senha456');

-- Inserindo gêneros
INSERT INTO tb_genero (nome) VALUES ('Ação');
INSERT INTO tb_genero (nome) VALUES ('Comédia');

-- Inserindo filmes
INSERT INTO tb_filme (titulo, ano, genero_id) VALUES ('Filme de Ação', 2022, 1);
INSERT INTO tb_filme (titulo, ano, genero_id) VALUES ('Filme de Comédia', 2023, 2);

-- Inserindo reviews
INSERT INTO tb_review (texto, user_id, filme_id) VALUES ('Ótimo filme!', 1, 1);
INSERT INTO tb_review (texto, user_id, filme_id) VALUES ('Muito engraçado!', 2, 2);
