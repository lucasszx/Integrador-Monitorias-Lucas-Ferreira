package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Agendamento;
import model.Aluno;
import model.Disciplina;
import model.Monitor;
import model.StatusAgendamento;

public class AgendamentoDAO {
	private ConexaoMysql conexao;

	public AgendamentoDAO() {
		this.conexao = new ConexaoMysql();
	}

	public long adicionar(Agendamento agendamento) {
		this.conexao.abrirConexao();
		String sql = "INSERT INTO agendamento VALUES(null, ?, ?, ?, ?, ?, ?)";
		long idAgendamento = 0;
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setLong(1, agendamento.getAluno().getIdAluno());
			st.setLong(2, agendamento.getMonitor().getIdMonitor());
			st.setLong(3, agendamento.getDisciplina().getIdDisciplina());
			st.setString(4, agendamento.getDescricao());
			st.setString(5, agendamento.getStatusAtendimento().toString());
			st.setTimestamp(6, java.sql.Timestamp.valueOf(agendamento.getDia()));
			st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
				idAgendamento = rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return idAgendamento;
	}

	public boolean editar(Agendamento agendamento) {
		this.conexao.abrirConexao();
		String sql = "UPDATE agendamento SET id_agendamento=?, id_aluno=?, id_monitor=?, id_disciplina=?, descricao=?, status_agendamento=?, dia=? WHERE id_agendamento=?";
		boolean foi = false;
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			st.setLong(1, agendamento.getIdAtendimento());
			st.setLong(2, agendamento.getAluno().getIdAluno());
			st.setLong(3, agendamento.getMonitor().getIdMonitor());
			st.setLong(4, agendamento.getDisciplina().getIdDisciplina());
			st.setString(5, agendamento.getDescricao());
			st.setString(6, agendamento.getStatusAtendimento().toString());
			st.setTimestamp(7, java.sql.Timestamp.valueOf(agendamento.getDia()));
			st.setLong(8, agendamento.getIdAtendimento());
			st.executeUpdate();
			if (st.getUpdateCount() > 0) {
				foi = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return foi;
	}

	public boolean excluir(long idAgendamento) {
		this.conexao.abrirConexao();
		String sql = "DELETE FROM agendamento WHERE id_agendamento=?";
		boolean foi = false;
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			st.setLong(1, idAgendamento);
			st.executeUpdate();
			if (st.getUpdateCount() > 0) {
				foi = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return foi;
	}

	public Agendamento buscarPorId(long idAgendamento) {
		this.conexao.abrirConexao();
		String sql = "SELECT * FROM agendamento WHERE id_agendamento=? INNER JOIN aluno ON agendamento.id_aluno = aluno.id_aluno INNER JOIN monitor ON agendamento.id_monitor = monitor.id_monito WHERE id_agendamento=?";
		Agendamento agendamento = null;
		Aluno aluno;
		Monitor monitor;
		Disciplina disciplina;
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			st.setLong(1, idAgendamento);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				agendamento = new Agendamento();
				agendamento.setIdAtendimento(rs.getLong("id_atendimento"));
				aluno = new Aluno();
				aluno.setIdAluno(rs.getLong("aluno.id_aluno"));
				aluno.setMatricula(rs.getString("aluno.matricula"));
				aluno.setNome(rs.getString("aluno.nome"));
				agendamento.setAluno(aluno);
				monitor = new Monitor();
				monitor.setIdMonitor(rs.getLong("monitor.id_monitor"));
				monitor.setMatricula(rs.getString("monitor.matricula"));
				monitor.setNome(rs.getString("monitor.nome"));
				agendamento.setMonitor(monitor);
				disciplina = new Disciplina();
				disciplina.setIdDisciplina(rs.getLong("disciplina.id_disciplina"));
				disciplina.setNome(rs.getString("disciplina.nome"));
				agendamento.setDisciplina(disciplina);
				agendamento.setDescricao(rs.getString("descricao"));
				agendamento.setStatusAtendimento(StatusAgendamento.valueOf(rs.getString("status_agendamento")));
				agendamento.setDia(rs.getTimestamp("dia").toLocalDateTime());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return agendamento;
	}

	public ArrayList<Agendamento> buscarPorIdAluno(long idAluno) {
		this.conexao.abrirConexao();
		String sql = "SELECT * FROM agendamento INNER JOIN aluno ON agendamento.id_aluno = aluno.id_aluno INNER JOIN monitor ON agendamento.id_monitor = monitor.id_monitor INNER JOIN disciplina ON agendamento.id_disciplina = disciplina.id_disciplina WHERE agendamento.id_aluno=?";
		Agendamento agendamento = null;
		Aluno aluno;
		Monitor monitor;
		Disciplina disciplina;
		ArrayList<Agendamento> agendamentos;
		agendamentos = new ArrayList<Agendamento>();
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			st.setLong(1, idAluno);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				agendamento = new Agendamento();
				agendamento.setIdAtendimento(rs.getLong("id_agendamento"));
				aluno = new Aluno();
				aluno.setIdAluno(rs.getLong("aluno.id_aluno"));
				aluno.setMatricula(rs.getString("aluno.matricula"));
				aluno.setNome(rs.getString("aluno.nome"));
				agendamento.setAluno(aluno);
				monitor = new Monitor();
				monitor.setIdMonitor(rs.getLong("monitor.id_monitor"));
				monitor.setMatricula(rs.getString("monitor.matricula"));
				monitor.setNome(rs.getString("monitor.nome"));
				agendamento.setMonitor(monitor);
				disciplina = new Disciplina();
				disciplina.setIdDisciplina(rs.getLong("disciplina.id_disciplina"));
				disciplina.setNome(rs.getString("disciplina.nome"));
				agendamento.setDisciplina(disciplina);
				agendamento.setDescricao(rs.getString("descricao"));
				agendamento.setStatusAtendimento(StatusAgendamento.valueOf(rs.getString("status_agendamento")));
				agendamento.setDia(rs.getTimestamp("dia").toLocalDateTime());
				agendamentos.add(agendamento);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return agendamentos;
	}

	public ArrayList<Agendamento> buscarPorIdAluno(long idAluno, StatusAgendamento statusAgendamento) {
		this.conexao.abrirConexao();
		String sql = "SELECT * FROM agendamento INNER JOIN aluno ON agendamento.id_aluno = aluno.id_aluno INNER JOIN monitor ON agendamento.id_monitor = monitor.id_monitor INNER JOIN disciplina ON agendamento.id_disciplina = disciplina.id_disciplina WHERE agendamento.id_aluno=? AND status_agendamento=?";
		Agendamento agendamento = null;
		Aluno aluno;
		Monitor monitor;
		Disciplina disciplina;
		ArrayList<Agendamento> agendamentos;
		agendamentos = new ArrayList<Agendamento>();
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			st.setLong(1, idAluno);
			st.setString(2, statusAgendamento.toString());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				agendamento = new Agendamento();
				agendamento.setIdAtendimento(rs.getLong("id_agendamento"));
				aluno = new Aluno();
				aluno.setIdAluno(rs.getLong("aluno.id_aluno"));
				aluno.setMatricula(rs.getString("aluno.matricula"));
				aluno.setNome(rs.getString("aluno.nome"));
				agendamento.setAluno(aluno);
				monitor = new Monitor();
				monitor.setIdMonitor(rs.getLong("monitor.id_monitor"));
				monitor.setMatricula(rs.getString("monitor.matricula"));
				monitor.setNome(rs.getString("monitor.nome"));
				agendamento.setMonitor(monitor);
				disciplina = new Disciplina();
				disciplina.setIdDisciplina(rs.getLong("disciplina.id_disciplina"));
				disciplina.setNome(rs.getString("disciplina.nome"));
				agendamento.setDisciplina(disciplina);
				agendamento.setDescricao(rs.getString("descricao"));
				agendamento.setStatusAtendimento(StatusAgendamento.valueOf(rs.getString("status_agendamento")));
				agendamento.setDia(rs.getTimestamp("dia").toLocalDateTime());
				agendamentos.add(agendamento);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return agendamentos;
	}

	public ArrayList<Agendamento> buscarPorIdMonitor(long idMonitor) {
		this.conexao.abrirConexao();
		String sql = "SELECT * FROM agendamento INNER JOIN aluno ON agendamento.id_aluno = aluno.id_aluno INNER JOIN monitor ON agendamento.id_monitor = monitor.id_monitor INNER JOIN disciplina ON agendamento.id_disciplina = disciplina.id_disciplina WHERE agendamento.id_monitor=?";
		Agendamento agendamento = null;
		Aluno aluno;
		Monitor monitor;
		Disciplina disciplina;
		ArrayList<Agendamento> agendamentos;
		agendamentos = new ArrayList<Agendamento>();
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			st.setLong(1, idMonitor);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				agendamento = new Agendamento();
				agendamento.setIdAtendimento(rs.getLong("id_agendamento"));
				aluno = new Aluno();
				aluno.setIdAluno(rs.getLong("aluno.id_aluno"));
				aluno.setMatricula(rs.getString("aluno.matricula"));
				aluno.setNome(rs.getString("aluno.nome"));
				agendamento.setAluno(aluno);
				monitor = new Monitor();
				monitor.setIdMonitor(rs.getLong("monitor.id_monitor"));
				monitor.setMatricula(rs.getString("monitor.matricula"));
				monitor.setNome(rs.getString("monitor.nome"));
				agendamento.setMonitor(monitor);
				disciplina = new Disciplina();
				disciplina.setIdDisciplina(rs.getLong("disciplina.id_disciplina"));
				disciplina.setNome(rs.getString("disciplina.nome"));
				agendamento.setDisciplina(disciplina);
				agendamento.setDescricao(rs.getString("descricao"));
				agendamento.setStatusAtendimento(StatusAgendamento.valueOf(rs.getString("status_agendamento")));
				agendamento.setDia(rs.getTimestamp("dia").toLocalDateTime());
				agendamentos.add(agendamento);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return agendamentos;
	}

	public ArrayList<Agendamento> buscarTodos() {
		this.conexao.abrirConexao();
		String sql = "SELECT * FROM agendamento INNER JOIN aluno ON agendamento.id_aluno = aluno.id_aluno INNER JOIN monitor ON agendamento.id_monitor = monitor.id_monitor INNER JOIN disciplina ON agendamento.id_disciplina = disciplina.id_disciplina";
		Agendamento agendamento = null;
		Aluno aluno;
		Monitor monitor;
		Disciplina disciplina;
		ArrayList<Agendamento> agendamentos;
		agendamentos = new ArrayList<Agendamento>();
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				agendamento = new Agendamento();
				agendamento.setIdAtendimento(rs.getLong("id_atendimento"));
				aluno = new Aluno();
				aluno.setIdAluno(rs.getLong("aluno.id_aluno"));
				aluno.setMatricula(rs.getString("aluno.matricula"));
				aluno.setNome(rs.getString("aluno.nome"));
				agendamento.setAluno(aluno);
				monitor = new Monitor();
				monitor.setIdMonitor(rs.getLong("monitor.id_monitor"));
				monitor.setMatricula(rs.getString("monitor.matricula"));
				monitor.setNome(rs.getString("monitor.nome"));
				agendamento.setMonitor(monitor);
				disciplina = new Disciplina();
				disciplina.setIdDisciplina(rs.getLong("disciplina.id_disciplina"));
				disciplina.setNome(rs.getString("disciplina.nome"));
				agendamento.setDisciplina(disciplina);
				agendamento.setDescricao(rs.getString("descricao"));
				agendamento.setStatusAtendimento(StatusAgendamento.valueOf(rs.getString("status_agendamento")));
				agendamento.setDia(rs.getTimestamp("dia").toLocalDateTime());
				agendamentos.add(agendamento);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return agendamentos;
	}
}
