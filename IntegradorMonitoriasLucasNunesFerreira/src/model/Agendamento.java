package model;

import java.time.LocalDateTime;

public class Agendamento {
	private long idAgendamento;
	private Aluno aluno;
	private Monitor monitor;
	private Disciplina disciplina;
	private String descricao;
	private StatusAgendamento statusAgendamento;
	private LocalDateTime dia;

	public Agendamento() {
		super();
	}

	public Agendamento(long idAtendimento, Aluno aluno, Monitor monitor, String resumo,
			StatusAgendamento statusAtendimento,
			LocalDateTime dia) {
		super();
		this.idAgendamento = idAtendimento;
		this.aluno = aluno;
		this.monitor = monitor;
		this.descricao = resumo;
		this.statusAgendamento = statusAtendimento;
		this.dia = dia;
	}

	public long getIdAtendimento() {
		return idAgendamento;
	}

	public void setIdAtendimento(long idAtendimento) {
		this.idAgendamento = idAtendimento;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Monitor getMonitor() {
		return monitor;
	}

	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String resumo) {
		this.descricao = resumo;
	}

	public StatusAgendamento getStatusAtendimento() {
		return statusAgendamento;
	}

	public void setStatusAtendimento(StatusAgendamento status) {
		this.statusAgendamento = status;
	}

	public LocalDateTime getDia() {
		return dia;
	}

	public void setDia(LocalDateTime dia) {
		this.dia = dia;
	}
}
