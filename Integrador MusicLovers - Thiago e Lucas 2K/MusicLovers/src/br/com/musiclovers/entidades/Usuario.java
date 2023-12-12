package br.com.musiclovers.entidades;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	private long idUsuario;
	private String nome;
	private String senha;
	private List<Favorito> listFavorito;
	private List<Playlist> listPlaylist;
	
	public Usuario() {
		super();
		this.idUsuario = 0;
		this.nome = "";
		this.senha = "";
		this.listFavorito = new ArrayList<>();
		this.listPlaylist = new ArrayList<>();
	}

	public Usuario(String nome, String senha) {
		super();
		this.idUsuario = 0;
		this.nome = nome;
		this.senha = senha;
		this.listFavorito = new ArrayList<>();
		this.listPlaylist = new ArrayList<>();
	}
	
	public Usuario(long idUsuario, String nome, String senha, List<Favorito> listFavorito, List<Playlist> listPlaylist) {
		super();
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.senha = senha;
		this.listFavorito = listFavorito;
		this.listPlaylist = listPlaylist;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Favorito> getListFavorito() {
		return listFavorito;
	}

	public void setListFavorito(List<Favorito> listFavorito) {
		this.listFavorito = listFavorito;
	}

	public List<Playlist> getListPlaylist() {
		return listPlaylist;
	}

	public void setListPlaylist(List<Playlist> listPlaylist) {
		this.listPlaylist = listPlaylist;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nome=" + nome + ", senha=" + senha + ", listFavorito="
				+ listFavorito + ", listPlaylist=" + listPlaylist + "]";
	}
	
	
	
	
	
	
	
}
