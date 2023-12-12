package model;

public enum StatusAgendamento {
	PENDENTE("Agendado Pendente"), AGENDAMENTO_CONCLUIDO("Agendado Concluido"),
	AGENDAMENTO_NAO_CONCLUIDO("Agendado Não Concluido"), CANCELADO("Agendado Cancelado"), CONCLUIDO("Concluido");

	private String descricao;

	private StatusAgendamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
