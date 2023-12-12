CREATE DATABASE bd_musics;
USE bd_musics;
DROP DATABASE bd_musics;

SELECT * FROM Usuario;


CREATE TABLE Usuario (
	id_usuario BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(80) NOT NULL UNIQUE,
	senha VARCHAR(20) NOT NULL
);

CREATE TABLE Playlist (
	id_playlist BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(80) NOT NULL UNIQUE,
	id_usuario BIGINT NOT NULL,
	FOREIGN KEY(id_usuario) REFERENCES Usuario(id_usuario)
);

CREATE TABLE Musica (
	id_musica BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT
);

CREATE TABLE Tag (
	id_tag BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(60),
	duracao BIGINT,
	artista VARCHAR(80),
	compositor VARCHAR(80),
	genero VARCHAR(30),
	id_musica BIGINT NOT NULL,
	FOREIGN KEY(id_musica) REFERENCES Musica(id_musica)
);

CREATE TABLE Favorito (
	id_favorito BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	id_usuario BIGINT NOT NULL,
    FOREIGN KEY(id_usuario) REFERENCES Usuario(id_usuario)
);

CREATE TABLE Favorito_musica (
	id_favorito_musica BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	id_favorito BIGINT NOT NULL,
	id_musica BIGINT NOT NULL,
    FOREIGN KEY(id_favorito) REFERENCES Favorito(id_favorito),
    FOREIGN KEY(id_musica) REFERENCES Musica(id_musica)
);

CREATE TABLE Playlist_musica (
	id_playlist_musica BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	playlist_nome VARCHAR(100) NOT NULL UNIQUE,
	id_playlist BIGINT NOT NULL,
	id_musica BIGINT NOT NULL,
    FOREIGN KEY(id_playlist) REFERENCES Playlist(id_playlist),
    FOREIGN KEY(id_musica) REFERENCES Musica(id_musica)
);