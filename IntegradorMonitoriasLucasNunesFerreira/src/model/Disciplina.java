package model;

public class Disciplina {
	long idDisciplina;
	String nome;

	public Disciplina() {
		super();
	}

	public Disciplina(long idDisciplina, String nome) {
		super();
		this.idDisciplina = idDisciplina;
		this.nome = nome;
	}

	public long getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(long idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
