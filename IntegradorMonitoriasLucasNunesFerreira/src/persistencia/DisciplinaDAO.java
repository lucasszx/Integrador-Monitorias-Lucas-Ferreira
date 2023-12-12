package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Disciplina;

public class DisciplinaDAO {
	private ConexaoMysql conexao;

	public DisciplinaDAO() {
		conexao = new ConexaoMysql();
	}

	public ArrayList<Disciplina> buscarPorIdAluno(long idAluno) {
		this.conexao.abrirConexao();
		String sql = "SELECT aluno_disciplina.id_disciplina, disciplina.nome FROM aluno_disciplina INNER JOIN disciplina ON aluno_disciplina.id_disciplina=disciplina.id_disciplina WHERE id_aluno=?";
		Disciplina disciplina;
		ArrayList<Disciplina> disciplinas;
		disciplinas = new ArrayList<Disciplina>();
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			st.setLong(1, idAluno);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				disciplina = new Disciplina();
				disciplina.setIdDisciplina(rs.getLong("id_disciplina"));
				disciplina.setNome(rs.getString("nome"));
				disciplinas.add(disciplina);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return disciplinas;
	}

	public Disciplina buscarPorId(long idDisciplina) {
		this.conexao.abrirConexao();
		String sql = "SELECT * FROM disciplina WHERE id_disciplina=?";
		Disciplina disciplina = null;
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			st.setLong(1, idDisciplina);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				disciplina = new Disciplina();
				disciplina.setIdDisciplina(rs.getLong(1));
				disciplina.setNome(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return disciplina;
	}
}
