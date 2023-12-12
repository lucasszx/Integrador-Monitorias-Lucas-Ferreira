package br.com.musiclovers.entidades;

import java.util.ArrayList;
import java.util.List;

public class Favorito {
	private long idFavorito;
	private Usuario usuario;
	private List<FavoritoMusica> listFavoritoMusica;
	
	public Favorito() {
		super();
		this.idFavorito = 0;
		this.usuario = null;
		this.listFavoritoMusica = new ArrayList<>();
	}

	public Favorito(long idFavorito, Usuario usuario, List<FavoritoMusica> listFavoritoMusica) {
		super();
		this.idFavorito = idFavorito;
		this.usuario = usuario;
		this.listFavoritoMusica = listFavoritoMusica;
	}

	public long getIdFavorito() {
		return idFavorito;
	}

	public void setIdFavorito(long idFavorito) {
		this.idFavorito = idFavorito;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<FavoritoMusica> getListFavoritoMusica() {
		return listFavoritoMusica;
	}

	public void setListFavoritoMusica(List<FavoritoMusica> listFavoritoMusica) {
		this.listFavoritoMusica = listFavoritoMusica;
	}

	@Override
	public String toString() {
		return "Favorito [idFavorito=" + idFavorito + ", usuario=" + usuario + ", listFavoritoMusica="
				+ listFavoritoMusica + "]";
	}
	
	
}
