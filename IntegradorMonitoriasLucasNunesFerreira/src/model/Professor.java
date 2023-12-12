package model;

public class Professor {

	private long idProfessor;
	private String matricula;
	private String nome;

	public Professor() {
		super();
	}

	public Professor(long idProfessor, String matricula, String nome) {
		super();
		this.idProfessor = idProfessor;
		this.matricula = matricula;
		this.nome = nome;
	}

	public long getIdProfessor() {
		return idProfessor;
	}

	public void setIdProfessor(long idProfessor) {
		this.idProfessor = idProfessor;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Professor [idProfessor=" + idProfessor + ", matricula=" + matricula + ", nome=" + nome + "]";
	}

}
