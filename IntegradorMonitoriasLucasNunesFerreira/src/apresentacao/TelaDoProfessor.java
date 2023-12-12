package apresentacao;

public class TelaDoProfessor {

	public static void main(String[] args) {

	}
	
	// -- As disciplinas ainda não estão relacionadas aos professor, logo serão atualizadas -- //
	// -- Abaixo um breve resumo de como vai funcionar. Possuindo a opção de visualizar os agendamento e seus resumos//

	/*public static void main(Professor professor, ProfessorDAO professorDAO, Scanner scanner) {
		int opcao;
		ArrayList<MonitoriaHorario> horariosMonitoria;
		MonitoriaHorarioDAO monitoriaHorariosDAO;
		while (true) {
			System.out.println("Escolha uma opcão: ");
			System.out.println("1 - Visualizar Agendamentos");
			System.out.println("Qualquer outro número - Sair");
			System.out.print("Digite sua opção: ");
			opcao = scanner.nextInt();
			if (opcao == 1) {
				monitoriaHorariosDAO = new MonitoriaHorarioDAO();
				horariosMonitoria = monitoriaHorariosDAO.buscarPorIdProfessor(professor.getIdProfessor());
				System.out.println("Meus horários (dia da semana, horário, disciplina, localização):");
				for (int i = 0; i < horariosMonitoria.size(); i++) {
					System.out.println((i + 1) + " = " + horariosMonitoria.get(i).getDiaSemana() + ", das "
							+ horariosMonitoria.get(i).getHoraInicio() + " às " + horariosMonitoria.get(i).getHoraFim()
							+ ", " + horariosMonitoria.get(i).getDisciplina().getNome() + ", "
							+ horariosMonitoria.get(i).getSala());
				}
			}
		}
	}*/
}