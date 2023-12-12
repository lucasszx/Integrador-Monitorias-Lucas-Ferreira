package br.com.musiclovers.entidades;

import java.util.ArrayList;
import java.util.List;

public class Musica {
	private long idMusica;
	private Tag tag;
	private List<FavoritoMusica> listFavoritoMusica;
	private List<PlaylistMusica> listPlaylistMusica;
	
	public Musica() {
		super();
		this.idMusica = 0;
		this.tag = null;
		this.listFavoritoMusica = new ArrayList<>();
		this.listPlaylistMusica = new ArrayList<>();
	}

	public Musica(long idMusica, Tag tag, List<FavoritoMusica> listFavoritoMusica, List<PlaylistMusica> listPlaylistMusica) {
		super();
		this.idMusica = idMusica;
		this.tag = tag;
		this.listFavoritoMusica = listFavoritoMusica;
		this.listPlaylistMusica = listPlaylistMusica;
	}

	public long getIdMusica() {
		return idMusica;
	}

	public void setIdMusica(long idMusica) {
		this.idMusica = idMusica;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public List<FavoritoMusica> getListFavoritoMusica() {
		return listFavoritoMusica;
	}

	public void setListFavoritoMusica(List<FavoritoMusica> listFavoritoMusica) {
		this.listFavoritoMusica = listFavoritoMusica;
	}

	public List<PlaylistMusica> getListPlaylistMusica() {
		return listPlaylistMusica;
	}

	public void setListPlaylistMusica(List<PlaylistMusica> listPlaylistMusica) {
		this.listPlaylistMusica = listPlaylistMusica;
	}

	@Override
	public String toString() {
		return "Musica [idMusica=" + idMusica + ", tag=" + tag + ", listFavoritoMusica=" + listFavoritoMusica
				+ ", listPlaylistMusica=" + listPlaylistMusica + "]";
	}
	
	
	
}
