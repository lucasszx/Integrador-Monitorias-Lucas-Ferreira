package br.com.musiclovers.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.musiclovers.entidades.Favorito;
import br.com.musiclovers.entidades.Usuario;

public class FavoritoDAO {
	
	private ConexaoMySQL conexao;
	
	public FavoritoDAO () {
		this.conexao = new ConexaoMySQL(DefaultBD.IP, DefaultBD.LOGIN,
				DefaultBD.NOME_BD, DefaultBD.PORTA,  DefaultBD.SENHA
				);
	}
	
	public FavoritoDAO (ConexaoMySQL conexao) {
		this.conexao = conexao;
	}
	
	public void adicionar (Favorito favorito) {
		this.conexao.abrirConexao();
		String sql = "INSERT INTO Favorito VALUES(null, ?);";
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			st.setLong(1, favorito.getUsuario().getIdUsuario());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}
	
	public void editar (Favorito favorito) {
		this.conexao.abrirConexao();
		String sql = "UPDATE Favorito SET id_usuario=? WHERE id_favorito=?;";
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			st.setLong(1, favorito.getUsuario().getIdUsuario());
			st.setLong(2, favorito.getIdFavorito());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}
	
	public void excluir (Favorito favorito) {
		this.conexao.abrirConexao();
		String sql = "DELETE FROM Favorito WHERE id_favorito=?;";
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			st.setLong(1, favorito.getIdFavorito());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}
	
	public Favorito buscarPorId (long idFavorito) {
		this.conexao.abrirConexao();
		String sql = "SELECT * FROM Favorito WHERE id_favorito=?;";
		Favorito favorito = null;
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			st.setLong(1, idFavorito);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				favorito = new Favorito();
				favorito.setIdFavorito(rs.getLong("id_favorito"));
				favorito.setUsuario(new Usuario(rs.getLong("id_usuario"), null, null, null, null));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return favorito;
	}
	
	public List<Favorito> buscarTodos () {
		this.conexao.abrirConexao();
		List<Favorito> listFavoritos = new ArrayList<Favorito>();
		String sql = "SELECT * FROM Favorito;";
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Favorito favorito = new Favorito();
				favorito.setIdFavorito(rs.getLong("id_favorito"));
				favorito.setUsuario(new Usuario (rs.getLong("id_usuario"), null, null, null, null));
				listFavoritos.add(favorito);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return listFavoritos;
	}
}
