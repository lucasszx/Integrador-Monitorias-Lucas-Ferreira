package apresentacao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import model.Agendamento;
import model.Aluno;
import model.Disciplina;
import model.MonitoriaHorario;
import model.StatusAgendamento;
import persistencia.AgendamentoDAO;
import persistencia.AlunoDAO;
import persistencia.DisciplinaDAO;
import persistencia.MonitoriaHorarioDAO;

public class TelaDoAluno {
	public static void main(Aluno aluno, AlunoDAO alunoDAO, Scanner scanner) {
		int opcao;
		ArrayList<Disciplina> disciplinas;
		Disciplina disciplina;
		DisciplinaDAO disciplinaDAO;
		long idDisciplina;
		ArrayList<MonitoriaHorario> horariosMonitoria;
		MonitoriaHorarioDAO horarioMonitoriaDAO;
		MonitoriaHorario horarioMonitoria;
		Agendamento agendamento;
		AgendamentoDAO agendamentoDAO;
		ArrayList<Agendamento> agendamentos;
		String data;
		int dia;
		int mes;
		disciplinaDAO = new DisciplinaDAO();
		horarioMonitoriaDAO = new MonitoriaHorarioDAO();
		agendamentoDAO = new AgendamentoDAO();
		System.out.println("Bem-vindo(a), " + aluno.getNome() + "!");
		while (true) {
			System.out.println("O que deseja fazer?");
			System.out.println("1 = Marcar um atendimento ou visualizar horários disponíveis");
			System.out.println("2 = Cancelar um atendimento marcado");
			System.out.println("3 = Verificar meus atendimentos");
			System.out.println("Qualquer outro número = Sair");
			System.out.print("Digite sua opção: ");
			opcao = scanner.nextInt();
			if (opcao == 1) {
				disciplinas = disciplinaDAO.buscarPorIdAluno(aluno.getIdAluno());
				System.out.println("De qual disciplina deseja marcar um atendimento?");
				for (int i = 0; i < disciplinas.size(); i++) {
					System.out.println((i + 1) + " = " + disciplinas.get(i).getNome());
				}
				System.out.println("Qualquer outro número = Voltar para o início");
				System.out.print("Digite sua opção: ");
				opcao = scanner.nextInt();
				if (opcao < 1 || opcao > disciplinas.size()) {
					continue;
				}
				disciplina = disciplinas.get(opcao - 1);
				idDisciplina = disciplina.getIdDisciplina();
				horariosMonitoria = horarioMonitoriaDAO.buscarPorIdDisciplina(idDisciplina);
				System.out.println(
						"Esses são os horários disponíveis para agendamento (horário, dia da semana, localização, monitor):");
				for (int i = 0; i < horariosMonitoria.size(); i++) {
					System.out.println((i + 1) + " = " + "Das " + horariosMonitoria.get(i).getHoraInicio() + " às "
							+ horariosMonitoria.get(i).getHoraFim() + ", " + horariosMonitoria.get(i).getDiaSemana()
							+ ", sala " + horariosMonitoria.get(i).getSala() + ", "
							+ horariosMonitoria.get(i).getMonitor().getNome());
				}
				System.out.println("Qualquer outro número = Voltar para o início");
				System.out.print("Digite sua opção: ");
				opcao = scanner.nextInt();
				if (opcao < 1 || opcao > horariosMonitoria.size()) {
					continue;
				}
				horarioMonitoria = horariosMonitoria.get(opcao - 1);
				agendamento = new Agendamento();
				agendamento.setAluno(aluno);
				agendamento.setMonitor(horarioMonitoria.getMonitor());
				agendamento.setDisciplina(disciplina);
				agendamento.setDescricao("");
				agendamento.setStatusAtendimento(StatusAgendamento.PENDENTE);
				System.out.print(
						"Para qual dia você deseja marcar o atendimento? (dd/mm, tem que cair no dia da semana do horário escolhido): ");
				data = scanner.next();
				dia = Integer.parseInt(data.split("/")[0]);
				mes = Integer.parseInt(data.split("/")[1]);
				agendamento.setDia(LocalDateTime.of(LocalDateTime.now().getYear(), mes, dia,
						horarioMonitoria.getHoraInicio().getHour(), horarioMonitoria.getHoraInicio().getMinute()));
				agendamentoDAO.adicionar(agendamento);
				System.out.println("Atendimento marcado com sucesso para " + agendamento.getDia() + "!");
			} else if (opcao == 2) {
				agendamentos = agendamentoDAO.buscarPorIdAluno(aluno.getIdAluno(),
						StatusAgendamento.PENDENTE);
				System.out.println("Qual atendimento deseja cancelar? (dia, monitor, disciplina):");
				for (int i = 0; i < agendamentos.size(); i++) {
					System.out.println((i + 1) + " = " + agendamentos.get(i).getDia() + ", "
							+ agendamentos.get(i).getMonitor().getNome() + ", "
							+ agendamentos.get(i).getDisciplina().getNome());
				}
				System.out.println("Qualquer outro número = Voltar para o início");
				System.out.print("Digite sua opção: ");
				opcao = scanner.nextInt();
				if (opcao < 1 || opcao > agendamentos.size()) {
					continue;
				}
				agendamento = agendamentos.get(opcao - 1);
				agendamento.setStatusAtendimento(StatusAgendamento.CANCELADO);
				agendamentoDAO.editar(agendamento);
				System.out.println("Atendimento cancelado com sucesso!");
			} else if (opcao == 3) {
				agendamentos = agendamentoDAO.buscarPorIdAluno(aluno.getIdAluno());
				System.out.println("Esses são os seus atendimentos (dia, monitor, disciplina, status):");
				for (int i = 0; i < agendamentos.size(); i++) {
					System.out.println((i + 1) + " = " + agendamentos.get(i).getDia() + ", "
							+ agendamentos.get(i).getMonitor().getNome() + ", "
							+ agendamentos.get(i).getDisciplina().getNome() + ", "
							+ agendamentos.get(i).getStatusAtendimento());
				}
			} else {
				break;
			}
		}
	}
}
