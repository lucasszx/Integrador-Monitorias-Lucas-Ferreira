package br.com.musiclovers.entidades;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
	private long idPlaylist;
	private String nome;
	private Usuario usuario;
	private List<PlaylistMusica> listPlaylistMusica;
	
	public Playlist() {
		super();
		this.idPlaylist = 0;
		this.nome = "";
		this.usuario = null;
		this.listPlaylistMusica = new ArrayList<>();
	}

	public Playlist(long idPlaylist, String nome, Usuario usuario, List<PlaylistMusica> listPlaylistMusica) {
		super();
		this.idPlaylist = idPlaylist;
		this.nome = nome;
		this.usuario = usuario;
		this.listPlaylistMusica = listPlaylistMusica;
	}

	public long getIdPlaylist() {
		return idPlaylist;
	}

	public void setIdPlaylist(long idPlaylist) {
		this.idPlaylist = idPlaylist;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<PlaylistMusica> getListPlaylistMusica() {
		return listPlaylistMusica;
	}

	public void setListPlaylistMusica(List<PlaylistMusica> listPlaylistMusica) {
		this.listPlaylistMusica = listPlaylistMusica;
	}

	@Override
	public String toString() {
		return "Playlist [idPlaylist=" + idPlaylist + ", nome=" + nome + ", usuario=" + usuario
				+ ", listPlaylistMusica=" + listPlaylistMusica + "]";
	}
	
	
}
