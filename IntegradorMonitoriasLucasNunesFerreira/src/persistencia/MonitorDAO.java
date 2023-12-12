package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Monitor;

public class MonitorDAO {
	private ConexaoMysql conexao;

	public MonitorDAO() {
		conexao = new ConexaoMysql();
	}

	public long adicionar(Monitor monitor) {
		this.conexao.abrirConexao();
		String sql = "INSERT INTO monitor VALUES(null, ?, ?)";
		long idMonitor = 0;
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, monitor.getMatricula());
			st.setString(2, monitor.getNome());
			st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
				idMonitor = rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return idMonitor;
	}

	public boolean editar(Monitor monitor) {
		this.conexao.abrirConexao();
		String sql = "UPDATE monitor SET nome=?, matricula=? WHERE id_monitor=?";
		boolean foi = false;
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, monitor.getNome());
			st.setString(2, monitor.getMatricula());
			st.setLong(3, monitor.getIdMonitor());
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

	public boolean excluir(long idMonitor) {
		this.conexao.abrirConexao();
		String sql = "DELETE FROM monitor WHERE id_monitor=?";
		boolean foi = false;
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			st.setLong(1, idMonitor);
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

	public Monitor buscarPorId(long idMonitor) {
		this.conexao.abrirConexao();
		String sql = "SELECT * FROM monitor WHERE id_monitor=?";
		Monitor monitor = null;
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			st.setLong(1, idMonitor);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				monitor = new Monitor();
				monitor.setIdMonitor(rs.getLong(1));
				monitor.setMatricula(rs.getString(2));
				monitor.setNome(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return monitor;
	}

	public Monitor buscarPorMatricula(String matricula) {
		this.conexao.abrirConexao();
		String sql = "SELECT * FROM monitor WHERE matricula=?";
		Monitor monitor = null;
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			st.setString(1, matricula);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				monitor = new Monitor();
				monitor.setIdMonitor(rs.getLong(1));
				monitor.setMatricula(rs.getString(2));
				monitor.setNome(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return monitor;
	}

	public ArrayList<Monitor> buscarTodos() {
		this.conexao.abrirConexao();
		String sql = "SELECT * FROM monitor";
		Monitor monitor = null;
		ArrayList<Monitor> monitores;
		monitores = new ArrayList<>();
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				monitor = new Monitor();
				monitor.setIdMonitor(rs.getLong(1));
				monitor.setMatricula(rs.getString(2));
				monitor.setNome(rs.getString(3));
				monitores.add(monitor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return monitores;
	}
}
