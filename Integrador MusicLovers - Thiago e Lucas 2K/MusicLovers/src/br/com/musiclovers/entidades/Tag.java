package br.com.musiclovers.entidades;

public class Tag {
	private long idTag;
	private String nome;
	private int duracao;
	private String artista;
	private String compositor;
	private String genero;
	private Musica musica;
	public Tag() {
		super();
		this.idTag = 0;
		this.nome = "";
		this.duracao = 0;
		this.artista = "";
		this.compositor = "";
		this.genero = "";
		this.musica = null;
	}
	public Tag(long idTag, String nome, int duracao, String artista, String compositor, String genero, Musica musica) {
		super();
		this.idTag = idTag;
		this.nome = nome;
		this.duracao = duracao;
		this.artista = artista;
		this.compositor = compositor;
		this.genero = genero;
		this.musica = musica;
	}
	public long getIdTag() {
		return idTag;
	}
	public void setIdTag(long idTag) {
		this.idTag = idTag;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getDuracao() {
		return duracao;
	}
	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}
	public String getArtista() {
		return artista;
	}
	public void setArtista(String artista) {
		this.artista = artista;
	}
	public String getCompositor() {
		return compositor;
	}
	public void setCompositor(String compositor) {
		this.compositor = compositor;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public Musica getMusica() {
		return musica;
	}
	public void setMusica(Musica musica) {
		this.musica = musica;
	}
	@Override
	public String toString() {
		return "Tag [idTag=" + idTag + ", nome=" + nome + ", duracao=" + duracao + ", artista=" + artista
				+ ", compositor=" + compositor + ", genero=" + genero + ", musica=" + musica + "]";
	}
	
	
	
}
