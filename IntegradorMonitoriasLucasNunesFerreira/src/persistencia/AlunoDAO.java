package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Aluno;

public class AlunoDAO {
	private ConexaoMysql conexao;

	public AlunoDAO() {
		conexao = new ConexaoMysql();
	}

	public long adicionar(Aluno aluno) {
		this.conexao.abrirConexao();
		String sql = "INSERT INTO aluno VALUES(null, ?, ?)";
		long idAluno = 0;
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, aluno.getMatricula());
			st.setString(2, aluno.getNome());
			st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
				idAluno = rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return idAluno;
	}

	public boolean editar(Aluno aluno) {
		this.conexao.abrirConexao();
		String sql = "UPDATE aluno SET nome=?, matricula=? WHERE id_aluno=?";
		boolean foi = false;
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, aluno.getNome());
			st.setString(2, aluno.getMatricula());
			st.setLong(3, aluno.getIdAluno());
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

	public boolean excluir(long idAluno) {
		this.conexao.abrirConexao();
		String sql = "DELETE FROM aluno WHERE id_aluno=?";
		boolean foi = false;
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			st.setLong(1, idAluno);
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

	public Aluno buscarPorId(long idAluno) {
		this.conexao.abrirConexao();
		String sql = "SELECT * FROM aluno WHERE id_aluno=?";
		Aluno aluno = null;
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			st.setLong(1, idAluno);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				aluno = new Aluno();
				aluno.setIdAluno(rs.getLong(1));
				aluno.setMatricula(rs.getString(2));
				aluno.setNome(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return aluno;
	}

	public Aluno buscarPorMatricula(String matricula) {
		this.conexao.abrirConexao();
		String sql = "SELECT * FROM aluno WHERE matricula=?";
		Aluno aluno = null;
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			st.setString(1, matricula);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				aluno = new Aluno();
				aluno.setIdAluno(rs.getLong(1));
				aluno.setMatricula(rs.getString(2));
				aluno.setNome(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return aluno;
	}

	public ArrayList<Aluno> buscarTodos() {
		this.conexao.abrirConexao();
		String sql = "SELECT * FROM aluno";
		Aluno aluno;
		ArrayList<Aluno> alunos;
		alunos = new ArrayList<Aluno>();
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				aluno = new Aluno();
				aluno.setIdAluno(rs.getLong(1));
				aluno.setMatricula(rs.getString(2));
				aluno.setNome(rs.getString(3));
				alunos.add(aluno);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return alunos;
	}
}
