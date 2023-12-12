package apresentacao;

import java.util.Scanner;

import model.Aluno;
import model.Monitor;
import persistencia.AlunoDAO;
import persistencia.MonitorDAO;

public class Tela1 {
    public static void main(String[] args) {
        Scanner scanner;
        String usuario;
        Aluno aluno;
        AlunoDAO alunoDAO;
        Monitor monitor;
        MonitorDAO monitorDAO;
        scanner = new Scanner(System.in);
        System.out.println("Bem-vindo!");
        System.out.print("Digite seu usuário: ");
        usuario = scanner.next();
        System.out.println("Como deseja se indentificar?");
        System.out.println("1 = Aluno");
        System.out.println("2 = Monitor");
        System.out.println("3 = Professor");
        System.out.print("Digite sua opção: ");
        int opcao = scanner.nextInt();
        if (opcao == 1) {
            alunoDAO = new AlunoDAO();
            aluno = alunoDAO.buscarPorMatricula(usuario);
            if (aluno != null) {
                TelaDoAluno.main(aluno, alunoDAO, scanner);
            }
            System.out.println("Usuário não encontrado! Encerrando...");
        } else if (opcao == 2) {
            monitorDAO = new MonitorDAO();
            monitor = monitorDAO.buscarPorMatricula(usuario);
            if (monitor != null) {
                TelaDoMonitor.main(monitor, monitorDAO, scanner);
            }
        }
    }
}

// -- Tela professor ainda não conectada -- //
/* professorDAO = new ProfessorDAO();
professor = professorDAO.buscarPorMatricula(usuario);
if (professor != null) {
    TelaDoProfessor.main(professor, professorDAO, scanner);*/
