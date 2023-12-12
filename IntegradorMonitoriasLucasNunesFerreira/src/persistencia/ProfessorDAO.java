package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Professor;

public class ProfessorDAO {
	private ConexaoMysql conexao;

	public ProfessorDAO() {
		conexao = new ConexaoMysql();
	}

	public long adicionar(Professor professor) {
		this.conexao.abrirConexao();
		String sql = "INSERT INTO professor VALUES(null, ?, ?)";
		long idProfessor = 0;
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, professor.getMatricula());
			st.setString(2, professor.getNome());
			st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
				idProfessor = rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return idProfessor;
	}

	public boolean editar(Professor professor) {
		this.conexao.abrirConexao();
		String sql = "UPDATE professor SET nome=?, matricula=? WHERE id_professor=?";
		boolean foi = false;
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, professor.getNome());
			st.setString(2, professor.getMatricula());
			st.setLong(3, professor.getIdProfessor());
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

	public boolean excluir(long idProfessor) {
		conexao.abrirConexao();
		String sql = "DELETE FROM professor WHERE id_professor=?";
		boolean foi = false;
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			st.setLong(1, idProfessor);
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

	public Professor buscarPorId(long idProfessor) {
		conexao.abrirConexao();
		String sql = "SELECT * FROM professor WHERE id_professor=?";
		Professor professor = null;
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			st.setLong(1, idProfessor);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				professor = new Professor();
				professor.setIdProfessor(rs.getLong(1));
				professor.setMatricula(rs.getString(2));
				professor.setNome(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return professor;
	}

	public Professor buscarPorMatricula(String matricula) {
		this.conexao.abrirConexao();
		String sql = "SELECT * FROM professor WHERE matricula=?";
		Professor professor = null;
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			st.setString(1, matricula);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				professor = new Professor();
				professor.setIdProfessor(rs.getLong(1));
				professor.setMatricula(rs.getString(2));
				professor.setNome(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return professor;
	}

	public ArrayList<Professor> buscarTodos() {
		this.conexao.abrirConexao();
		String sql = "SELECT * FROM professor";
		Professor professor;
		ArrayList<Professor> professores;
		professores = new ArrayList<Professor>();
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				professor = new Professor();
				professor.setIdProfessor(rs.getLong(1));
				professor.setMatricula(rs.getString(2));
				professor.setNome(rs.getString(3));
				professores.add(professor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return professores;
	}
}
