INSERT INTO permissao (codigo, nome) VALUES (1, 'ROLE_CADASTRAR_USUARIO');
INSERT INTO permissao (codigo, nome) VALUES (2, 'ROLE_CADASTRAR_CIDADE');

INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (1, 1);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (1, 2);

INSERT INTO usuario_grupo (codigo_usuario, codigo_grupo) VALUES ((select codigo from usuario where email = 'admin@brewer.com'), 1);