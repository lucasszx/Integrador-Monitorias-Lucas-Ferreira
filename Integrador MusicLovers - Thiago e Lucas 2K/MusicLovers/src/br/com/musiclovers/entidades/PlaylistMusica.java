package br.com.musiclovers.entidades;

public class PlaylistMusica {
	private long idPlaylistMusica;
	private String nome;
	private Playlist playlist;
	private Musica musica;
	
	public PlaylistMusica() {
		super();
		this.idPlaylistMusica = 0;
		this.nome = "";
		this.playlist = null;
		this.musica = null;
	}

	public PlaylistMusica(long idPlaylistMusica, String nome, Playlist playlist, Musica musica) {
		super();
		this.idPlaylistMusica = idPlaylistMusica;
		this.nome = nome;
		this.playlist = playlist;
		this.musica = musica;
	}

	public long getIdPlaylistMusica() {
		return idPlaylistMusica;
	}

	public void setIdPlaylistMusica(long idPlaylistMusica) {
		this.idPlaylistMusica = idPlaylistMusica;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Playlist getPlaylist() {
		return playlist;
	}

	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}

	public Musica getMusica() {
		return musica;
	}

	public void setMusica(Musica musica) {
		this.musica = musica;
	}

	@Override
	public String toString() {
		return "PlaylistMusica [idPlaylistMusica=" + idPlaylistMusica + ", nome=" + nome + ", playlist=" + playlist
				+ ", musica=" + musica + "]";
	}
	
	
	
}
