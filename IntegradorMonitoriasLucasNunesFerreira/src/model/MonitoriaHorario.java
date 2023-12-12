package model;

import java.time.LocalTime;

public class MonitoriaHorario {
	long idMonitoriaHorario;
	Monitor monitor;
	Disciplina disciplina;
	String sala;
	LocalTime horaInicio;
	LocalTime horaFim;
	String diaSemana;

	public MonitoriaHorario() {
		super();
	}

	public MonitoriaHorario(long idMonitoriaHorario, Monitor monitor, Disciplina disciplina, String sala,
			LocalTime horaInicio, LocalTime horaFim, String diaSemana) {
		super();
		this.idMonitoriaHorario = idMonitoriaHorario;
		this.monitor = monitor;
		this.disciplina = disciplina;
		this.sala = sala;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.diaSemana = diaSemana;
	}

	public long getIdMonitoriaHorario() {
		return idMonitoriaHorario;
	}

	public void setIdMonitoriaHorario(long idMonitoriaHorario) {
		this.idMonitoriaHorario = idMonitoriaHorario;
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

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(LocalTime horaFim) {
		this.horaFim = horaFim;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	@Override
	public String toString() {
		return "MonitoriaHorario [idMonitoriaHorario=" + idMonitoriaHorario + ", monitor=" + monitor + ", disciplina="
				+ disciplina + ", sala=" + sala + ", horaInicio=" + horaInicio + ", horaFim=" + horaFim + ", diaSemana="
				+ diaSemana + "]";
	}

}
