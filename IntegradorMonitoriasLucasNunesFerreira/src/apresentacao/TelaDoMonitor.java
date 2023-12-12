package apresentacao;

import java.util.ArrayList;
import java.util.Scanner;

import model.Agendamento;
import model.Monitor;
import model.MonitoriaHorario;
import model.StatusAgendamento;
import persistencia.AgendamentoDAO;
import persistencia.MonitorDAO;
import persistencia.MonitoriaHorarioDAO;

public class TelaDoMonitor {
	public static void main(Monitor monitor, MonitorDAO monitorDAO, Scanner scanner) {
		int opcao;
		AgendamentoDAO agendamentoDAO;
		ArrayList<Agendamento> agendamentos;
		Agendamento agendamento;
		ArrayList<MonitoriaHorario> horariosMonitoria;
		MonitoriaHorarioDAO monitoriaHorariosDAO;
		agendamentoDAO = new AgendamentoDAO();
		System.out.println("Bem-vindo(a), " + monitor.getNome() + "!");
		while (true) {
			System.out.println("O que deseja fazer?");
			System.out.println("1 = Editar um atendimento");
			System.out.println("2 = Visualizar meus horários");
			System.out.println("Qualquer outro número = Sair");
			System.out.print("Digite sua opção: ");
			opcao = scanner.nextInt();
			if (opcao == 1) {
				agendamentos = agendamentoDAO.buscarPorIdMonitor(monitor.getIdMonitor());
				System.out.println("Qual atendimento deseja editar?");
				for (int i = 0; i < agendamentos.size(); i++) {
					System.out.println((i + 1) + " = " + agendamentos.get(i).getDia() + ", "
					+ agendamentos.get(i).getMonitor().getNome() + ", "
					+ agendamentos.get(i).getDisciplina().getNome() + ", "
					+ agendamentos.get(i).getStatusAtendimento());
				}
				System.out.println("Qualquer outro número = Voltar para o início");
				System.out.print("Digite sua opção: ");
				opcao = scanner.nextInt();
				if (opcao < 1 || opcao > agendamentos.size()) {
					continue;
				}
				agendamento = agendamentos.get(opcao - 1);
				System.out.print("Qual o novo status do atendimento? ");
				agendamento.setStatusAtendimento(StatusAgendamento.valueOf(scanner.next()));
				System.out.print("Qual o resumo do atendimento? ");
				agendamento.setDescricao(scanner.next());
				System.out.println("Status editado com sucesso!");
			} else if (opcao == 2) {
				monitoriaHorariosDAO = new MonitoriaHorarioDAO();
				horariosMonitoria = monitoriaHorariosDAO.buscarPorIdMonitor(monitor.getIdMonitor());
				System.out.println("Meus horários (dia da semana, horário, disciplina, localização):");
				for (int i = 0; i < horariosMonitoria.size(); i++) {
					System.out.println((i + 1) + " = " + horariosMonitoria.get(i).getDiaSemana() + ", das "
					+ horariosMonitoria.get(i).getHoraInicio() + " às " + horariosMonitoria.get(i).getHoraFim() + ", "
					+ horariosMonitoria.get(i).getDisciplina().getNome() + ", "
					+ horariosMonitoria.get(i).getSala());
				}
			} else {
				break;
			}
		}
	}
}
