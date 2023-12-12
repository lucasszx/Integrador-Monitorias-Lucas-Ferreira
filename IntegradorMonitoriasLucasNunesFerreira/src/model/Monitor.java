package model;

public class Monitor {

	private long idMonitor;
	private String matricula;
	private String nome;

	public Monitor() {
		super();
	}

	public Monitor(long idMonitor, String matricula, String nome) {
		super();
		this.idMonitor = idMonitor;
		this.matricula = matricula;
		this.nome = nome;
	}

	public long getIdMonitor() {
		return idMonitor;
	}

	public void setIdMonitor(long idMonitor) {
		this.idMonitor = idMonitor;
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
		return "Monitor [idMonitor=" + idMonitor + ", matricula=" + matricula + ", nome=" + nome + "]";
	}

	
	
}
