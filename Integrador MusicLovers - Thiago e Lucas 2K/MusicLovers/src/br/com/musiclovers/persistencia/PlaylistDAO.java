package br.com.musiclovers.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.musiclovers.entidades.Playlist;
import br.com.musiclovers.entidades.Usuario;

public class PlaylistDAO {
	
	private ConexaoMySQL conexao;
	
	public PlaylistDAO() {
		this.conexao = new ConexaoMySQL(DefaultBD.IP, DefaultBD.LOGIN,
				DefaultBD.NOME_BD, DefaultBD.PORTA,  DefaultBD.SENHA
				);
	}
	
	public PlaylistDAO(ConexaoMySQL conexao) {
		this.conexao = conexao;
	}
	
	public void adicionar(Playlist playlist) {
		this.conexao.abrirConexao();
		String sql = "INSERT INTO Playlist VALUES(null, ?, ?);";
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			st.setString(1, playlist.getNome());
			st.setLong(2, playlist.getUsuario().getIdUsuario());	
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}
	
	public void editar (Playlist playlist) {
		this.conexao.abrirConexao();
		String sql = "UPDATE Playlist SET nome=? WHERE id_playlist=?;";
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			st.setString(1, playlist.getNome());
			st.setLong(2, playlist.getIdPlaylist());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}
	
	public void excluir (Playlist playlist) {
		this.conexao.abrirConexao();
		String sql = "DELETE FROM Playlist WHERE id_playlist=?;";
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			st.setLong(1, playlist.getIdPlaylist());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}
	
	public void excluirTodasPlaylists (Usuario usuario) {
		this.conexao.abrirConexao();
		String sql = "DELETE FROM Playlist WHERE id_usuario=?;";
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			st.setLong(1, usuario.getIdUsuario());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}
	
	public Playlist buscarPorId (long idPlaylist) {
		this.conexao.abrirConexao();
		String sql = "SELECT * FROM Playlist WHERE id_playlist=?;";
		Playlist playlist = null;
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			st.setLong(1, idPlaylist);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				playlist = new Playlist();
				playlist.setIdPlaylist(rs.getLong("id_playlist"));
				playlist.setNome(rs.getString("nome"));
				playlist.setUsuario(new Usuario (rs.getLong("id_usuario"), null, null, null, null));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return playlist;
	}
	
	public Playlist buscarPorNome (String nomePlaylist) {
		this.conexao.abrirConexao();
		String sql = "SELECT * FROM Playlist WHERE nome=?;";
		Playlist playlist = null;
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			st.setString(1, nomePlaylist);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				playlist = new Playlist();
				playlist.setIdPlaylist(rs.getLong("id_playlist"));
				playlist.setNome(rs.getString("nome"));
				playlist.setUsuario(new Usuario (rs.getLong("id_usuario"), null, null, null, null));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return playlist;
	}
	
	public List<Playlist> buscarTodosDoUsuario(long idUsuario) {
		this.conexao.abrirConexao();
		List<Playlist> listPlaylists = new ArrayList<Playlist>();
		String sql = "SELECT * FROM Playlist WHERE id_usuario=?;";
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			st.setLong(1, idUsuario);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Playlist playlist = new Playlist();
				playlist.setIdPlaylist(rs.getLong("id_playlist"));
				playlist.setNome(rs.getString("nome"));
				listPlaylists.add(playlist);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return listPlaylists;
	}
	
	public List<Playlist> buscarTodos() {
		this.conexao.abrirConexao();
		List<Playlist> listPlaylists = new ArrayList<Playlist>();
		String sql = "SELECT * FROM Playlist;";
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Playlist playlist = new Playlist();
				playlist.setIdPlaylist(rs.getLong("id_playlist"));
				playlist.setNome(rs.getString("nome"));
				listPlaylists.add(playlist);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return listPlaylists;
	}
}
