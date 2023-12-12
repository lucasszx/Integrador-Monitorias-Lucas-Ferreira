package br.com.musiclovers.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.musiclovers.entidades.Musica;

public class MusicaDAO {
	
	private ConexaoMySQL conexao;
	
	public MusicaDAO () {
		this.conexao = new ConexaoMySQL(DefaultBD.IP, DefaultBD.LOGIN,
				DefaultBD.NOME_BD, DefaultBD.PORTA,  DefaultBD.SENHA
				);
	}
	
	public MusicaDAO (ConexaoMySQL conexao) {
		this.conexao = conexao;
	}
	
	public void adicionar (Musica musica) {
		this.conexao.abrirConexao();
		String sql = "INSERT INTO Musica VALUES (null);";
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}
	
	public void excluir (Musica musica) {
		this.conexao.abrirConexao();
		String sql = "DELETE FROM Musica WHERE id_musica=?;";
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			st.setLong(1, musica.getIdMusica());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}
	
	public List<Musica> buscarPorTodos () {
		this.conexao.abrirConexao();
		List<Musica> listMusicas = new ArrayList<Musica>();
		String sql = "SELECT * FROM Musica;";
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Musica musica = new Musica();
				musica.setIdMusica(rs.getLong("id_musica"));
				listMusicas.add(musica);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return listMusicas;
	}
	
}