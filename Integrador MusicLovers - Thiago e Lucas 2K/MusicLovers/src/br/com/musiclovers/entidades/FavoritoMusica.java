package br.com.musiclovers.entidades;

public class FavoritoMusica {
	private long idFavoritoMusica;
	private Favorito favorito;
	private Musica musica;
	
	public FavoritoMusica() {
		super();
		this.idFavoritoMusica = 0;
		this.favorito = null;
		this.musica = null;
	}

	public FavoritoMusica(long idFavoritoMusica, Favorito favorito, Musica musica) {
		super();
		this.idFavoritoMusica = idFavoritoMusica;
		this.favorito = favorito;
		this.musica = musica;
	}

	public long getIdFavoritoMusica() {
		return idFavoritoMusica;
	}

	public void setIdFavoritoMusica(long idFavoritoMusica) {
		this.idFavoritoMusica = idFavoritoMusica;
	}

	public Favorito getFavorito() {
		return favorito;
	}

	public void setFavorito(Favorito favorito) {
		this.favorito = favorito;
	}

	public Musica getMusica() {
		return musica;
	}

	public void setMusica(Musica musica) {
		this.musica = musica;
	}

	@Override
	public String toString() {
		return "FavoritoMusica [idFavoritoMusica=" + idFavoritoMusica + ", favorito=" + favorito + ", musica=" + musica
				+ "]";
	}
	
	
	
}
