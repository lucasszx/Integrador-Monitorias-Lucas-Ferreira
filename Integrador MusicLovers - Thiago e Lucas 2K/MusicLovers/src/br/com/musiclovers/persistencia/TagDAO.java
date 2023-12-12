package br.com.musiclovers.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.musiclovers.entidades.Tag;

public class TagDAO {
	private ConexaoMySQL conexao;
	
	public TagDAO () {
		this.conexao = new ConexaoMySQL(DefaultBD.IP, DefaultBD.LOGIN,
				DefaultBD.NOME_BD, DefaultBD.PORTA,  DefaultBD.SENHA
				);
	}
	
	public TagDAO (ConexaoMySQL conexao) {
		this.conexao = conexao;
	}
	
	public long adicionar (Tag tag) {
		this.conexao.abrirConexao();
		long idTag = 0;
		String sql = "INSERT INTO Tag VALUES(null, ?, ?, ?, ?, ?, ?);";
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, tag.getNome());
			st.setInt(2, tag.getDuracao());
			st.setString(3, tag.getArtista());
			st.setString(4, tag.getCompositor());
			st.setString(5, tag.getGenero());
			st.setLong(6, tag.getMusica().getIdMusica());
			st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
				idTag = rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return idTag;
	}
	
	public void editar (Tag tag) {
		this.conexao.abrirConexao();
		String sql = "UPDATE Tag SET nome=?, duracao=?, artista=?, compositor=?, genero=?, id_musica=? WHERE id_tag=?;";
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			st.setString(1, tag.getNome());
			st.setInt(2, tag.getDuracao());
			st.setString(3, tag.getArtista());
			st.setString(4, tag.getCompositor());
			st.setString(5, tag.getGenero());
			st.setLong(6, tag.getMusica().getIdMusica());
			st.setLong(7, tag.getIdTag());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}
	
	public void excluir (Tag tag) {
		this.conexao.abrirConexao();
		String sql = "DELETE FROM Tag WHERE id_tag=?";
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			st.setLong(1, tag.getIdTag());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}
}

