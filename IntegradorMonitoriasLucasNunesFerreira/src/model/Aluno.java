package model;

public class Aluno {

	private long idAluno;
	private String matricula;
	private String nome;

	public Aluno() {
		super();
	}

	public Aluno(long idAluno, String matricula, String nome) {
		super();
		this.idAluno = idAluno;
		this.matricula = matricula;
		this.nome = nome;
	}

	public long getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(long idAluno) {
		this.idAluno = idAluno;
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
		return "Aluno [idAluno=" + idAluno + ", matricula=" + matricula + ", nome=" + nome + "]";
	}

	
	
}