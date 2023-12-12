package br.com.musiclovers.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.com.musiclovers.entidades.Usuario;

public class UsuarioDAO {
	
	private ConexaoMySQL conexao;
	
	public UsuarioDAO () {
		this.conexao = new ConexaoMySQL(DefaultBD.IP, DefaultBD.LOGIN,
				DefaultBD.NOME_BD, DefaultBD.PORTA,  DefaultBD.SENHA
				);
	}
	
	public UsuarioDAO (ConexaoMySQL conexao) {
		this.conexao = conexao;
	}
	
	public long adicionar (Usuario usuario) {
		this.conexao.abrirConexao();
		String sql = "INSERT INTO Usuario VALUES(null, ?, ?);";
		long idUsuario = 0;
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, usuario.getNome());
			st.setString(2, usuario.getSenha());
			st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
				idUsuario = rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return idUsuario;
	}

	public void editar (Usuario usuario) {
		this.conexao.abrirConexao();
		String sql = "UPDATE Usuario SET nome=?, senha=? WHERE id_usuario=?;";
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			st.setString(1, usuario.getNome());
			st.setString(2, usuario.getSenha());
			st.setLong(3, usuario.getIdUsuario());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public void excluir (Usuario usuario) {
		this.conexao.abrirConexao();
		String sql = "DELETE FROM Usuario WHERE id_usuario=?;";
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

	public Usuario buscarPorId (long idUsuario) {
		this.conexao.abrirConexao();
		Usuario usuario = null;
		String sql = "SELECT * FROM Usuario WHERE id_usuario=?;";
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			st.setLong(1, idUsuario);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				usuario = new Usuario();
				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSenha(rs.getString("senha"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return usuario;
	}
	
	public Usuario buscarPorNome(String nome) {
		this.conexao.abrirConexao();
		Usuario user = null;
		String sql = "SELECT * FROM Usuario WHERE nome=?;";
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			st.setString(1, nome);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				user = new Usuario();
				user.setIdUsuario(rs.getLong("id_usuario"));
				user.setNome(rs.getString("nome"));
				user.setSenha(rs.getString("senha"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		
		return user;
	}

	public List<Usuario> buscarTodos () {
		this.conexao.abrirConexao();
		List<Usuario> listUsuarios = new ArrayList<Usuario>();
		String sql = "SELECT * FROM Usuario;";
		try {
			PreparedStatement st = conexao.getConexao().prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSenha(rs.getString("senha"));
				listUsuarios.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return listUsuarios;
	}
}
